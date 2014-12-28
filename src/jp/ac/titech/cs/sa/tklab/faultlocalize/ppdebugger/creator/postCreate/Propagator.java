package jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.creator.postCreate;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
			if(st.getPropagatables().isEmpty()) continue;
			for(DataDependencySet propagatables: st.getPropagatables()){
				if(propagatables.isLabeled()){
					continue;
				}
				for(DataDependency dependency :propagatables.getSet()){
					for(DataDependencySet originals: em.getStatement(dependency.getStatementData()).getOriginals()){
						Set<DataDependency> tmp= new HashSet<DataDependency>();
						for(DataDependency dd: originals.getSet()){
							tmp.add(dd);
						}
						if(!tmp.isEmpty()){
							st.addNextPropagation(new DataDependencySet(dependency.getStatementData(),dependency.getVarName(),tmp,originals.isLabeled()));
						}
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
