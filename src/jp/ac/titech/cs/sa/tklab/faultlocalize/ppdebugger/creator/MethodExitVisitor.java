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
import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.creator.scope.Scope;
import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.model.DataDependency;
import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.model.DataDependencySet;
import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.model.execution.ExecutionModel;
import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.model.execution.LineVariable;
import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.model.execution.Variable;

class MethodExitVisitor {
	static void create(ExecutionModel model,MethodExit me,LineVariable returnLine,LineVariable callerLine,LineVariable argsLine,Scope scope){
		StatementDataFactory sdFactory = StatementDataFactory.getInstance();
		if(isSkip(me, returnLine,sdFactory))return;
		
		DataDependencyFactory ddFactory = DataDependencyFactory.getInstance();
		VariableDefinition definition = createVariableDefinition(me,scope);
		Variable returnVariable = new Variable(NameCreator.createReturnName(scope), definition);
		StatementData currentSD = sdFactory.genStatementData(me.getCalleeSourcePath(),me.getCalleeLineNumber(),me.getThread());
		StatementData callerSD = sdFactory.genStatementData(me.getCallerSourcePath(),me.getCallerLineNumber(),me.getThread());
		Set<DataDependency> set = new HashSet<DataDependency>();
		for(Variable variable: returnLine.getVariables()){
			VariableDefinition def = variable.getLatestDefinition();
			StatementData fromSd = sdFactory.genStatementData(def.getSourcePath(),def.getLineNumber(),def.getThread());
			DataDependency dd = ddFactory.genDataDependency(variable.getVarName(),scope.getTreeNode(),fromSd);
			set.add(dd);
		}
		//呼び出し元に追加
		DataDependency returnDependency = ddFactory.genDataDependency(returnVariable.getVarName(),scope.getTreeNode(), currentSD);
		DataDependencySet callerDDS = new DataDependencySet(callerSD,returnDependency,me.getEventNumber());
		model.addDataDependencySet(callerSD, callerDDS);
		
		callerLine.add(callerSD,returnVariable);
		argsLine.add(callerSD,returnVariable);
	}
	
	private static boolean isSkip(MethodExit me,LineVariable returnline,StatementDataFactory factory){
		if(returnline.getVariables().isEmpty()) return true;
		//メインメソッドなどはcalleeが存在しない
		if(me.getCalleeSourcePath() == null || me.getCalleeLineNumber() == null) return true;
		
		StatementData current =  factory.genStatementData(me.getCalleeSourcePath(),me.getCalleeLineNumber(),me.getThread());
		if(!returnline.getStatementData().equals(current)) return true;	//現在の行でない

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
