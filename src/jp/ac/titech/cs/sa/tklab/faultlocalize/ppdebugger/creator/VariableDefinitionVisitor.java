package jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.creator;

import jp.ac.nagoya_u.is.i.agusa.person.knhr.bxmodel.VariableDefinition;
import jp.ac.titech.cs.sa.tklab.faultlocalize.StatementDataFactory;
import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.model.execution.ExecutionModel;
import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.model.execution.Variable;

class VariableDefinitionVisitor {
	static void create(ExecutionModel em,VariableDefinition def,Scope scope){
		if(isSkip(def)) return;
		Variable variable = new Variable(NameCreator.createVariableName(def, scope),def);
		em.addVariable(variable);
		StatementDataFactory factory = StatementDataFactory.getInstance();
		em.addStatementData(factory.genStatementData(def.getSourcePath(),def.getLineNumber(),def.getThread()));
	}
	
	/**
	 * 変数名がわからない場合は無視する
	 * @param def
	 * @return　無視するときにtrue
	 */
	private static boolean isSkip(VariableDefinition def){
		if(def.getDefinedVariable().getFieldInfo() != null
				&& def.getDefinedVariable().getFieldInfo().getVariableName() == null){
			return true;
		}
		if(def.getDefinedVariable().getLocalVariableInfo() != null
				&& def.getDefinedVariable().getLocalVariableInfo().getVariableName() == null){
			return true;
		}
		return false;
	}
	
}
