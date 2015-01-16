package jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.creator.postCreate;


import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;

import jp.ac.titech.cs.sa.tklab.faultlocalize.StatementData;
import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.DataDependencyFactory;
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
					SortedSet<DataDependencySet> originals = em.getStatement(targetSd).getOriginals();
					originals = originals.tailSet(propagatable);		//現在のイベント以前に起きたものだけをとる
					if(originals.isEmpty()) continue;
					
					Set<DataDependency> newDDset = new HashSet<DataDependency>();
					Set<String> registerdVars = new HashSet<String>();
					for(DataDependencySet dds:originals){
						for(DataDependency dd: dds.getSet()){
							if(registerdVars.contains(dd.getVarName())) continue;	//同じ変数名の依存はひとつだけ。セットが新しいイベント順で並んでいるので、これで最新の依存のみ取れる
							newDDset.add(ddFactory.genDataDependency(dd.getVarName(), dd.getStatementData()));
							registerdVars.add(dd.getVarName());
						}
					}
					if(newDDset != null){
						DataDependencySet newDds = new DataDependencySet(targetSd, targetVarName,propagatable.getEventNumber(),newDDset,false);
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
