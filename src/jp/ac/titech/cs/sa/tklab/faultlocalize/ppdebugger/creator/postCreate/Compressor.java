package jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.creator.postCreate;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.creator.NameCreator;
import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.model.DataDependency;
import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.model.DataDependencySet;
import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.model.execution.ExecutionModel;
import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.model.execution.Statement;

public class Compressor {
	public static void compress(ExecutionModel em){
		List<Statement> statements = em.getStatements();
		for(Statement st : statements){
			List<DataDependencySet> ddsList = new ArrayList<DataDependencySet>();
			for(DataDependencySet dds :st.getDataDependencySets()){
				Set<DataDependency> ddSet = new HashSet<DataDependency>();
				for(DataDependency dd : dds.getSet()){
					dd.setVarName(NameCreator.getOriginalName(dd.getVarName()));
					ddSet.add(dd);
				}
				dds.setVarName(NameCreator.getOriginalName(dds.getVarName()));
				dds.setDDSet(ddSet);
				ddsList.add(dds);
			}
			st.setDataDependencySets(ddsList);
		}
	}
}
