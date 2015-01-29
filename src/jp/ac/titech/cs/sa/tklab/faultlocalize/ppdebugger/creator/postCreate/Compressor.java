package jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.creator.postCreate;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jp.ac.titech.cs.sa.tklab.faultlocalize.StatementData;
import jp.ac.titech.cs.sa.tklab.faultlocalize.StatementDataFactory;
import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.DataDependencyFactory;
import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.creator.NameCreator;
import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.model.DataDependency;
import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.model.DataDependencySet;
import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.model.execution.ExecutionModel;
import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.model.execution.Statement;

public class Compressor {
	
	public static void compress(ExecutionModel em){
		List<Statement> statements = em.getStatements();
		StatementDataFactory sdFactory = StatementDataFactory.getInstance();
		DataDependencyFactory ddFactory = DataDependencyFactory.getInstance();
		for(Statement st : statements){
			//依存終了後には必要ないデータを削除する。その上で同一なものはまとめる
			Set<DataDependencySet> ddsSet = new HashSet<DataDependencySet>();
			for(DataDependencySet dds :st.getDataDependencySets()){
				Set<DataDependency> ddSet = new HashSet<DataDependency>();
				for(DataDependency dd : dds.getSet()){
					StatementData oldsd = dd.getStatementData();
					StatementData newsd = sdFactory.genStatementData(oldsd.getSourcePath(),oldsd.getLineNumber());		//スレッドは無視
					DataDependency newdd = ddFactory.genDataDependency(NameCreator.removeObjectID(dd.getVarName()), null,newsd);	//スコープを考慮しないインスタンスを作る
					ddSet.add(newdd);
				}
				dds.setDDSet(ddSet);
				dds.compress();
				ddsSet.add(dds);
			}
			
			List<DataDependencySet> ddsList = new ArrayList<DataDependencySet>(ddsSet);
			int target = 0;
			for(int i =0; i < ddsList.size(); i++){
				int index = target+1;
				while(index != -1){
					index = getDuplicateIndex(ddsList.get(target), ddsList, index);
					if(index != -1){
						ddsList.remove(index);
					}
				}
			}
			st.compress(ddsList);
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
