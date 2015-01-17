package jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.creator;


import java.util.List;

import jp.ac.nagoya_u.is.i.agusa.person.knhr.bxmodel.MethodEntry;
import jp.ac.nagoya_u.is.i.agusa.person.knhr.bxmodel.MethodSignature;
import jp.ac.nagoya_u.is.i.agusa.person.knhr.bxmodel.ObjectInfoType;
import jp.ac.nagoya_u.is.i.agusa.person.knhr.bxmodel.PrimitiveValueInfo;
import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.model.execution.ExecutionModel;
import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.model.execution.LineVariable;
import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.model.execution.Variable;

class MethodEntryVisitor {
	static void create(ExecutionModel em,MethodEntry me,LineVariable argsLine,Scope scope){
		if(isSkip(me)) return;
		createDataDependency(em,me,argsLine,scope);
	}
	
	private static boolean isSkip(MethodEntry me){
		if(me.getMethodSignature() == null) return true;
		MethodSignature signature = me.getMethodSignature();
		if(signature.getReturnType() == null) return true;
		if(EntryUtil.getArgsNum(signature.getReturnType()) == 0) return true;
		return false;
	}

	
	private static void createDataDependency(ExecutionModel model,MethodEntry me,LineVariable argsLine,Scope scope){
		MethodSignature signature = me.getMethodSignature();
		List<String> argumentTypes = signature.getArgumentTypes().getTypeNames();
		List<Object> argumentValues = me.getArgumentValues().getPrimitiveValueInfosAndObjectInfos();
		List<Variable> variables = argsLine.getVariables();
		
		int start;
		if(EntryUtil.isStaticMethod(signature)){
			start = 0;
		}else{
			start = 1;
		}
		int stackAddress = start;		//スタックの何番目に格納される引数か。
		for(int i=start; i < argumentTypes.size(); i++){
			String type = argumentTypes.get(i);
			int index = -1;
			if(isPrimitiveType(type)){
				PrimitiveValueInfo value = (PrimitiveValueInfo)argumentValues.get(i);
				index = EntryUtil.match(value, variables);
			}else{
				ObjectInfoType value = (ObjectInfoType) argumentValues.get(i);
				index = EntryUtil.match(value, variables);
			}
			if(index != -1){
				
				Variable var = new Variable(NameCreator.createActuallArgsName(scope, stackAddress), variables.get(index).getLatestDefinition());
				model.addVariable(var);
				variables.remove(index);
			}
			stackAddress += EntryUtil.getBlockSize(type);
		}
	}
	
	private static boolean isPrimitiveType(String type){
		if(type.equals("byte") || type.equals("char") || type.equals("double") || type.equals("float")
				|| type.equals("int") || type.equals("long") || type.equals("short") || type.equals("boolean"))
			return true;
		
		return false;
	}
	
	
}
