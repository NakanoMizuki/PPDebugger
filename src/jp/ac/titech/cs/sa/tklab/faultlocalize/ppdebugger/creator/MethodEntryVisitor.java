package jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.creator;


import java.util.List;

import jp.ac.nagoya_u.is.i.agusa.person.knhr.bxmodel.MethodEntry;
import jp.ac.nagoya_u.is.i.agusa.person.knhr.bxmodel.MethodSignature;
import jp.ac.nagoya_u.is.i.agusa.person.knhr.bxmodel.ObjectInfoType;
import jp.ac.nagoya_u.is.i.agusa.person.knhr.bxmodel.PrimitiveValueInfo;
import jp.ac.nagoya_u.is.i.agusa.person.knhr.bxmodel.VariableDefinition;
import jp.ac.titech.cs.sa.tklab.faultlocalize.StatementData;
import jp.ac.titech.cs.sa.tklab.faultlocalize.StatementDataFactory;
import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.DataDependencyFactory;
import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.model.DataDependency;
import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.model.DataDependencySet;
import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.model.execution.ExecutionModel;
import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.model.execution.LineVariable;
import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.model.execution.Variable;

class MethodEntryVisitor {
	static void create(ExecutionModel em,MethodEntry me,LineVariable argsLine,Scope scope){
		if(isSkip(me)) return;
		createDataDependency(em,me,argsLine,scope);
	}
	
	private static boolean isSkip(MethodEntry me){
		if(me.getCalleeSourcePath() == null || me.getCalleeLineNumber() == null) return true;
		if(me.getMethodSignature() == null) return true;
		MethodSignature signature = me.getMethodSignature();
		if(signature.getReturnType() == null) return true;
		if(signature.getArgumentTypes() == null) return true;
		List<String> types = signature.getArgumentTypes().getTypeNames();
		if(types.size() == 0) return true;
		if(types.size() == 1 && !EntryUtil.isStaticMethod(signature)) return true;
		return false;
	}

	
	private static void createDataDependency(ExecutionModel model,MethodEntry entry,LineVariable argsLine,Scope scope){
		MethodSignature signature = entry.getMethodSignature();
		List<String> argumentTypes = signature.getArgumentTypes().getTypeNames();
		List<Object> argumentValues = entry.getArgumentValues().getPrimitiveValueInfosAndObjectInfos();
		List<Variable> variables = argsLine.getVariables();
		StatementDataFactory sdFactory = StatementDataFactory.getInstance();
		DataDependencyFactory ddFactory = DataDependencyFactory.getInstance();
		
		int start;
		if(EntryUtil.isStaticMethod(signature)){
			start = 0;
		}else{
			start = 1;
		}
		int stackAddress = start;		//スタックの何番目に格納される引数か。
		for(int i=start; i < argumentTypes.size(); i++){
			//ローカル変数と仮引数を対応付ける
			String paramName = NameCreator.createActualParamName(scope, i);
			String localName = NameCreator.createFormalParamName(scope, stackAddress);
			scope.putParam(localName, paramName);
			
			//引数間でのデータ依存を発生させる
			String type = argumentTypes.get(i);
			int index = -1;
			if(EntryUtil.isPrimitiveType(type)){
				PrimitiveValueInfo value = (PrimitiveValueInfo)argumentValues.get(i);
				index = EntryUtil.match(value, variables);
			}else{
				ObjectInfoType value = (ObjectInfoType) argumentValues.get(i);
				index = EntryUtil.match(value, variables);
			}
			if(index != -1){
				Variable refferedVariable = variables.get(index);
				variables.remove(index);
				
				//仮引数として新たな変数を追加
				VariableDefinition definition = EntryUtil.createParamDefinition(entry,scope.getMethodName(), i, paramName);
				Variable actualVariable = new Variable(paramName, definition);
				model.addVariable(actualVariable);
				
				//実引数から仮引数へのデータ依存
				StatementData formalParamSD = EntryUtil.createFormalParamSD(entry,scope.getMethodName(), i, paramName);
				StatementData sdData = sdFactory.genStatementData(refferedVariable.getLatestDefinition().getSourcePath(), refferedVariable.getLatestDefinition().getLineNumber(),refferedVariable.getLatestDefinition().getThread());
				DataDependency dd = ddFactory.genDataDependency(refferedVariable.getVarName(),sdData );
				DataDependencySet dds = new DataDependencySet(formalParamSD,dd, entry.getEventNumber());
				model.addDataDependencySet(formalParamSD, dds);
			}
			
			stackAddress += EntryUtil.getBlockSize(type);
		}
	}
	
}
