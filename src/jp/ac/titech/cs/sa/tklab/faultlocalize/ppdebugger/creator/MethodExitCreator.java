package jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.creator;

import jp.ac.nagoya_u.is.i.agusa.person.knhr.bxmodel.MethodExit;
import jp.ac.nagoya_u.is.i.agusa.person.knhr.bxmodel.VariableDefinition;
import jp.ac.titech.cs.sa.tklab.faultlocalize.StatementData;
import jp.ac.titech.cs.sa.tklab.faultlocalize.StatementDataFactory;
import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.model.DataDependency;
import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.model.DataDependencySet;
import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.model.execution.ExecutionModel;
import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.model.execution.LineVariable;
import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.model.execution.Variable;

class MethodExitCreator {
	static void create(ExecutionModel em,MethodExit me,LineVariable calleeLine,LineVariable callerLine,StatementDataFactory factory){
		if(isSkip(me, calleeLine,factory))return;
		
		StatementData toSd = factory.genStatementData(me.getCallerSourcePath(),me.getCallerLineNumber(),me.getThread());
		for(Variable variable: calleeLine.getVariables()){
			VariableDefinition def = variable.getLatestDefinition();
			StatementData fromSd = factory.genStatementData(def.getSourcePath(),def.getLineNumber(),def.getThread());
			DataDependency dd = new DataDependency(variable.getVarName(),fromSd);
			DataDependencySet dds = new DataDependencySet(toSd, dd,Integer.valueOf(me.getEventNumber()));
			em.addDataDependencySet(toSd, dds);
			callerLine.add(toSd,variable);
		}
	}
	
	private static boolean isSkip(MethodExit me,LineVariable lineVar,StatementDataFactory factory){
		if(lineVar.getVariables().isEmpty()) return true;
		if(me.getCalleeSourcePath() == null || me.getCalleeLineNumber() == null){		//メインメソッドなどはcalleeが存在しない
			return true;
		}else{
			StatementData current =  factory.genStatementData(me.getCalleeSourcePath(),me.getCalleeLineNumber(),me.getThread());
			if(!lineVar.getStatementData().equals(current)) return true;
		}

		return false;
	}
}
