package jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.creator.postCreate;


import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.SortedSet;

import jp.ac.titech.cs.sa.tklab.faultlocalize.StatementData;
import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.DataDependencyFactory;
import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.creator.NameCreator;
import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.model.DataDependency;
import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.model.DataDependencySet;
import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.model.execution.ExecutionModel;
import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.model.execution.Statement;

public class Propagator {
	
	public static void propagate(ExecutionModel em,int hopNum){
		for(int i=0; i < hopNum; i++){
			propagate(em);
		}
	}
	private static void propagate(ExecutionModel em){
		List<Statement> statements = em.getStatements();
		DataDependencyFactory ddFactory = DataDependencyFactory.getInstance();
		
		//全ステートメントに対して、今回伝搬されるデータ依存を追加
		for(Statement st :statements){
			for(DataDependencySet propagatable: st.getPropagatables()){
				if(propagatable.isLabeled()){
					continue;
				}
				for(DataDependency dependency :propagatable.getSet()){
					StatementData targetSd = dependency.getStatementData();
					String targetVarName = dependency.getVarName();
					if(em.getStatement(targetSd) == null) continue;
					SortedSet<DataDependencySet> originals = em.getStatement(targetSd).getOriginals();
					try{
						originals = originals.tailSet(propagatable);		//現在のイベント以前に起きたものだけをとる
						if(NameCreator.isParam(targetVarName)){
							DataDependencySet dummy = new DataDependencySet(null, null,null,originals.first().getEventNumber()-1,null,false);	//イベントナンバーが１だけ違うものを作る
							originals = originals.headSet(dummy);	//最新のイベントナンバーのものだけを取る。引数の場合は実行毎に使われる変数が異なるのでこうしている
						}
					}catch(NoSuchElementException e){
						continue;
					}
					if(originals.isEmpty()) continue;
					
					Set<DataDependency> newDDset = new HashSet<DataDependency>();
					Set<VarName> registerdVars = new HashSet<VarName>();
					for(DataDependencySet dds:originals){
						for(DataDependency dd: dds.getSet()){
							VarName currentVar = new VarName(dd.getVarName(),dd.getTreeNode());
							if(registerdVars.contains(currentVar)) continue;	//同じ変数名の依存はひとつだけ。セットが新しいイベント順で並んでいるので、これで最新の依存のみ取れる
							newDDset.add(ddFactory.genDataDependency(dd.getVarName(),dd.getTreeNode(), dd.getStatementData()));
							registerdVars.add(currentVar);
						}
					}
					if(newDDset != null){
						DataDependencySet newDds = new DataDependencySet(targetSd, targetVarName,dependency.getTreeNode(),propagatable.getEventNumber(),newDDset,false);
						st.addNextPropagation(newDds);
					}
				}
			}
		}
		//伝搬を行う
		for(Statement st:statements){
			st.propagate();
		}
	}
}
