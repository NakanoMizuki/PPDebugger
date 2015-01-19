package jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.creator.postCreate;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.DataDependencyFactory;
import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.creator.NameCreator;
import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.model.DataDependency;
import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.model.DataDependencySet;
import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.model.execution.ExecutionModel;
import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.model.execution.Statement;

public class Compressor {
	
	public static void compress(ExecutionModel em){
		List<Statement> statements = em.getStatements();
		DataDependencyFactory ddFactory = DataDependencyFactory.getInstance();
		for(Statement st : statements){
			//名前の圧縮（スコープをなくす）
			List<DataDependencySet> ddsList = new ArrayList<DataDependencySet>();
			for(DataDependencySet dds :st.getDataDependencySets()){
				Set<DataDependency> ddSet = new HashSet<DataDependency>();
				for(DataDependency dd : dds.getSet()){
					DataDependency newdd = ddFactory.genDataDependency(NameCreator.removeSuffix(dd.getVarName()), dd.getStatementData());
					ddSet.add(newdd);
				}
				dds.setVarName(NameCreator.removeSuffix(dds.getVarName()));
				dds.setDDSet(ddSet);
				ddsList.add(dds);
			}
			int target = 0;
			while(target < ddsList.size()){
				int index = getDuplicateIndex(ddsList.get(target), ddsList, target +1);
				if(index != -1){
					ddsList.remove(index);
				}else{
					target++;
				}
			}
			st.setDataDependencySets(ddsList);
		}
	}
	
	/**
	 * リスト内の重複する要素のインデックスを返す
	 * @param target
	 * @param ddsList
	 * @param start
	 * @return 重複があればインデックス、なければ-1
	 */
	private static int getDuplicateIndex(DataDependencySet target,List<DataDependencySet> ddsList,int start){
		if(start >= ddsList.size()) return -1;
		for(int i=start; i < ddsList.size(); i++){
			DataDependencySet dds = ddsList.get(i);
			if(dds == target) continue;	//参照値が等しい==target自身
			if(dds.isSame(target)) return i;
		}
		return -1;
	}
}
