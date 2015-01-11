package jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.creator.postCreate;


import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;

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
		
		//全ステートメントに対して、今回伝搬されるデータ依存を追加
		for(Statement st :statements){
			for(DataDependencySet propagatable: st.getPropagatables()){
				if(propagatable.isLabeled()){
					continue;
				}
				for(DataDependency dependency :propagatable.getSet()){
					//eventNumberが最新のものをとる
					SortedSet<DataDependencySet> originals = em.getStatement(dependency.getStatementData()).getOriginals();
					originals = originals.headSet(propagatable);
					if(originals.isEmpty()) continue;
					DataDependencySet original = originals.last();
					
					Set<DataDependency> set = new HashSet<DataDependency>();
					for(DataDependency dd: original.getSet()){
						set.add(dd);
					}
					if(!set.isEmpty()){
						st.addNextPropagation(new DataDependencySet(dependency.getStatementData(),dependency.getVarName(),original.getEventNumber(),set,original.isLabeled()));
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
