package jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.creator;

import java.util.List;

import jp.ac.nagoya_u.is.i.agusa.person.knhr.bxmodel.ConstructorEntry;
import jp.ac.nagoya_u.is.i.agusa.person.knhr.bxmodel.MethodSignature;
import jp.ac.nagoya_u.is.i.agusa.person.knhr.bxmodel.ObjectInfoType;
import jp.ac.nagoya_u.is.i.agusa.person.knhr.bxmodel.PrimitiveValueInfo;
import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.model.execution.ExecutionModel;
import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.model.execution.LineVariable;
import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.model.execution.Variable;

class ConstructorEntryVisitor {
	static void create(ExecutionModel model,ConstructorEntry ce,LineVariable argsLine,Scope scope){
		if(isSkip(ce)) return;
		createDataDependency(model, ce, argsLine, scope);
	}
	
	private static boolean isSkip(ConstructorEntry ce){
		if(ce.getCalleeSourcePath() == null || ce.getCalleeLineNumber() == null) return true;
		if(ce.getMethodSignature() == null) return true;
		MethodSignature signature = ce.getMethodSignature();
		if(signature.getReturnType() == null) return true;
		if(signature.getArgumentTypes() == null) return true;
		if(signature.getArgumentTypes().getTypeNames().size() == 0) return true;
		return false;
	}
	
	private static void createDataDependency(ExecutionModel model,ConstructorEntry ce,LineVariable argsLine,Scope scope){
		MethodSignature signature = ce.getMethodSignature();
		List<String> argumentTypes = signature.getArgumentTypes().getTypeNames();
		List<Object> argumentValues = ce.getArgumentValues().getPrimitiveValueInfosAndObjectInfos();
		List<Variable> variables = argsLine.getVariables();
		
		int stackAddress = 0;		//スタックの何番目に格納される引数か。
		for(int i=0; i < argumentTypes.size(); i++){
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
				Variable var = new Variable(NameCreator.createFormalParamName(scope, stackAddress), variables.get(index).getLatestDefinition());
				model.addVariable(var);
				variables.remove(index);
			}
			stackAddress += EntryUtil.getBlockSize(type);
		}
	}
}
