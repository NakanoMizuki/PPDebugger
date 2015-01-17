package jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.creator;

import java.util.HashSet;
import java.util.Set;

import jp.ac.nagoya_u.is.i.agusa.person.knhr.bxmodel.LocalVariableInfo;
import jp.ac.nagoya_u.is.i.agusa.person.knhr.bxmodel.MethodExit;
import jp.ac.nagoya_u.is.i.agusa.person.knhr.bxmodel.VariableDefinition;
import jp.ac.nagoya_u.is.i.agusa.person.knhr.bxmodel.VariableInfoLeafType;
import jp.ac.titech.cs.sa.tklab.faultlocalize.StatementData;
import jp.ac.titech.cs.sa.tklab.faultlocalize.StatementDataFactory;
import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.DataDependencyFactory;
import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.model.DataDependency;
import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.model.DataDependencySet;
import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.model.execution.ExecutionModel;
import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.model.execution.LineVariable;
import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.model.execution.Variable;

class MethodExitVisitor {
	static void create(ExecutionModel em,MethodExit me,LineVariable calleeLine,LineVariable callerLine,LineVariable argsLine,Scope scope){
		StatementDataFactory sdFactory = StatementDataFactory.getInstance();
		if(isSkip(me, calleeLine,sdFactory))return;
		
		DataDependencyFactory ddFactory = DataDependencyFactory.getInstance();
		VariableDefinition definition = createVariableDefinition(me,scope);
		Variable returnVariable = new Variable(NameCreator.createReturnName(scope), definition);
		StatementData currentSD = sdFactory.genStatementData(me.getCalleeSourcePath(),me.getCalleeLineNumber(),me.getThread());
		StatementData callerSD = sdFactory.genStatementData(me.getCallerSourcePath(),me.getCallerLineNumber(),me.getThread());
		Set<DataDependency> set = new HashSet<DataDependency>();
		for(Variable variable: calleeLine.getVariables()){
			VariableDefinition def = variable.getLatestDefinition();
			StatementData fromSd = sdFactory.genStatementData(def.getSourcePath(),def.getLineNumber(),def.getThread());
			DataDependency dd = ddFactory.genDataDependency(variable.getVarName(),fromSd);
			set.add(dd);
		}
		DataDependencySet dds = new DataDependencySet(currentSD,returnVariable.getVarName(),Long.valueOf(me.getEventNumber()),set,false);
		em.addDataDependencySet(callerSD, dds);
		callerLine.add(callerSD,returnVariable);
		argsLine.add(callerSD,returnVariable);
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
	
	private static VariableDefinition createVariableDefinition(MethodExit methodExit,Scope scope){
		VariableDefinition definition = new VariableDefinition();
		definition.setEventNumber(methodExit.getEventNumber());
		definition.setLineNumber(methodExit.getCalleeLineNumber());
		definition.setSourcePath(methodExit.getCalleeSourcePath());
		definition.setThread(methodExit.getThread());
		VariableInfoLeafType variableInfo = new VariableInfoLeafType();
		LocalVariableInfo localInfo = new LocalVariableInfo();
		localInfo.setVariableName(NameCreator.createReturnName(scope));
		variableInfo.setLocalVariableInfo(localInfo);
		definition.setDefinedVariable(variableInfo);
		
		return definition;
	}
}
