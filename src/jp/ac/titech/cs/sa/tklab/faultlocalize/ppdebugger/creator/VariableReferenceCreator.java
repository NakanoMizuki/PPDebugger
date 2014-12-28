package jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.creator;


import jp.ac.nagoya_u.is.i.agusa.person.knhr.bxmodel.VariableDefinition;
import jp.ac.nagoya_u.is.i.agusa.person.knhr.bxmodel.VariableReference;
import jp.ac.titech.cs.sa.tklab.faultlocalize.StatementData;
import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.model.DataDependency;
import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.model.DataDependencySet;
import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.model.execution.ExecutionModel;
import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.model.execution.LineVariable;

public class VariableReferenceCreator {
	static void create(ExecutionModel em,VariableReference ref,String scope,LineVariable lineVar){
		if(isSkip(ref)) return;
		
		String varName = NameCreator.createVariableName(ref, scope);
		if(em.getVariable(varName) == null){
			//System.out.println("Reference not existing variable.");
			return;
		}
		
		//参照イベントの起きた行をデータ依存先の対象ステートメントとする
		StatementData currentSd = new StatementData(ref.getSourcePath(),ref.getLineNumber(),ref.getThread());
		
		//最後の定義情報から依存元のステートメントを得る
		
		VariableDefinition vd = em.getVariable(varName).getLatestDefinition();
		StatementData fromSd = new StatementData(vd.getSourcePath(),vd.getLineNumber(),vd.getThread());
		
		
		//データ依存を作成
		DataDependency dd = new DataDependency(NameCreator.createVariableName(ref, scope),fromSd);
		DataDependencySet dds = new DataDependencySet(currentSd,dd);
		
		//依存先のステートメントにデータ依存を追加
		em.addDataDependencySet(currentSd, dds);
		lineVar.add(currentSd, em.getVariable(varName));
	}
	
	private static boolean isSkip(VariableReference ref){
		if(ref.getReferredVariable().getFieldInfo() != null
				&& ref.getReferredVariable().getFieldInfo().getVariableName() == null){
			return true;
		}
		if(ref.getReferredVariable().getLocalVariableInfo() != null
				&& ref.getReferredVariable().getLocalVariableInfo().getVariableName() == null){
			return true;
		}
		return false;
	}

}
