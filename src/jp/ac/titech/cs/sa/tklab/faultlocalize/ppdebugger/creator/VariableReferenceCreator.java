package jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.creator;


import jp.ac.nagoya_u.is.i.agusa.person.knhr.bxmodel.VariableDefinition;
import jp.ac.nagoya_u.is.i.agusa.person.knhr.bxmodel.VariableReference;
import jp.ac.titech.cs.sa.tklab.faultlocalize.StatementData;
import jp.ac.titech.cs.sa.tklab.faultlocalize.StatementDataFactory;
import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.model.DataDependency;
import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.model.DataDependencySet;
import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.model.execution.ExecutionModel;
import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.model.execution.LineVariable;

public class VariableReferenceCreator {
	static void create(ExecutionModel em,VariableReference ref,Scope scope,LineVariable lineVar,StatementDataFactory factory){
		if(isSkip(ref)) return;
		
		String varName = NameCreator.createVariableName(ref, scope);
		if(em.getVariable(varName) == null){		//メソッドの引数などは定義なしに参照され得る
			return;
		}
		
		//参照イベントの起きた行をデータ依存先の対象ステートメントとする
		StatementData currentSd = factory.genStatementData(ref.getSourcePath(),ref.getLineNumber(),ref.getThread());
		
		//最後の定義情報から依存元のステートメントを得る
		
		VariableDefinition vd = em.getVariable(varName).getLatestDefinition();
		StatementData fromSd = factory.genStatementData(vd.getSourcePath(),vd.getLineNumber(),vd.getThread());
		
		
		//データ依存を作成
		DataDependency dd = new DataDependency(NameCreator.createVariableName(ref, scope),fromSd);
		DataDependencySet dds = new DataDependencySet(currentSd,dd,Integer.valueOf(ref.getEventNumber()));
		
		//依存先のステートメントにデータ依存を追加
		em.addDataDependencySet(currentSd, dds);
		lineVar.add(currentSd, em.getVariable(varName));
	}
	
	/**
	 * 変数名がわからない場合は無視する
	 * @param ref
	 * @return　無視するときにtrue
	 */
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
