package jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.creator;


import java.util.ArrayList;
import java.util.List;

import jp.ac.nagoya_u.is.i.agusa.person.knhr.bxmodel.MethodEntry;
import jp.ac.nagoya_u.is.i.agusa.person.knhr.bxmodel.MethodSignature;
import jp.ac.nagoya_u.is.i.agusa.person.knhr.bxmodel.ObjectInfoType;
import jp.ac.nagoya_u.is.i.agusa.person.knhr.bxmodel.PrimitiveValueInfo;
import jp.ac.nagoya_u.is.i.agusa.person.knhr.bxmodel.VariableInfoLeafType;
import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.model.execution.ExecutionModel;
import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.model.execution.LineVariable;
import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.model.execution.Variable;

class MethodEntryCreator {
	static void create(ExecutionModel em,MethodEntry me,LineVariable argsLine,Scope scope){
		if(isSkip(me)) return;
		createDataDependency(em,me,argsLine,scope);
	}
	
	private static boolean isSkip(MethodEntry me){
		if(me.getMethodSignature() == null) return true;
		MethodSignature signature = me.getMethodSignature();
		if(signature.getReturnType() == null) return true;
		if(getArgsNum(signature.getReturnType()) == 0) return true;
		return false;
	}

	private static int getArgsNum(String returnType){
		String args = returnType.replaceAll("\\).*", "");
		args = args.replaceAll("\\(", "");
		if(args.length() == 0) return 0;
		int count = 0;
		while(args.length() != 0){
			int index = 0;
			while((index < args.length() ) && (args.charAt(index) == '[')){	//配列の記号'['の分だけ先に進める
				index++;
			}
			
			switch(args.charAt(index)){
			case 'B':
			case 'C':
			case 'D':
			case 'F':
			case 'I':
			case 'J':
			case 'S':
			case 'Z':
				count ++;
				index += 1;
				index = Math.min(index, args.length());
				args = args.substring(index);
				break;
			case 'L':
				count ++;
				index = args.indexOf(';')+1;
				index = Math.min(index, args.length());
				args = args.substring(index);
				break;
			default:
				throw new RuntimeException("Method Return Type is Illegal!");	
			}
		}
		return count;
	}
	
	private static boolean isStaticMethod(MethodSignature signature){
		String returnType = signature.getReturnType();
		if(getArgsNum(returnType) != signature.getArgumentTypes().getTypeNames().size()){	//スタティックメソッド以外だと最初の引数がインスタンス自身になる
			return false;
		}
		return true;
	}
	
	private static void createDataDependency(ExecutionModel model,MethodEntry me,LineVariable lineVar,Scope scope){
		MethodSignature signature = me.getMethodSignature();
		List<String> argumentTypes = signature.getArgumentTypes().getTypeNames();
		List<Object> argumentValues = me.getArgumentValues().getPrimitiveValueInfosAndObjectInfos();
		List<Variable> variables = new ArrayList<Variable>();
		variables.addAll(lineVar.getVariables());	//リストのディープコピー
		
		int start;
		if(isStaticMethod(signature)){
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
				index = match(value, variables);
			}else{
				ObjectInfoType value = (ObjectInfoType) argumentValues.get(i);
				index = match(value, variables);
			}
			if(index != -1){
				Variable var = new Variable(NameCreator.createActuallArgsName(scope, stackAddress), variables.get(index).getLatestDefinition());
				model.addVariable(var);
				variables.remove(index);
			}
			stackAddress += getBlockSize(type);
		}
	}
	
	private static boolean isPrimitiveType(String type){
		if(type.equals("byte") || type.equals("char") || type.equals("double") || type.equals("float")
				|| type.equals("int") || type.equals("long") || type.equals("short") || type.equals("boolean"))
			return true;
		
		return false;
	}
	
	/**
	 * valueと等しいものがvariables内にあるか調べる。
	 * @param valueInfo
	 * @param variables
	 * @return なければ-1、あればリスト内のインデックスを返す。
	 */
	private static int match(PrimitiveValueInfo valueInfo,List<Variable> variables){
		if(valueInfo.getPrimitiveValue() == null) return -1;
		for(int i= 0; i < variables.size(); i++){
			VariableInfoLeafType varInfo = variables.get(i).getLatestDefinition().getDefinedVariable();
			PrimitiveValueInfo current = null;
			if(varInfo.getFieldInfo() != null){
				current = varInfo.getFieldInfo().getValue().getPrimitiveValueInfo();
			}else if(varInfo.getLocalVariableInfo() != null){
				current = varInfo.getLocalVariableInfo().getValue().getPrimitiveValueInfo();
			}
			if(current == null || current.getPrimitiveValue() == null) continue;
			if(isSameType(valueInfo.getPrimitiveTypeName(), current.getPrimitiveTypeName())
						&& isSameValue(valueInfo.getPrimitiveValue(), current.getPrimitiveValue()))
					return i;
		}
		return -1;
	}
	private static int match(ObjectInfoType valueInfo,List<Variable> variables){
		for(int i=0; i < variables.size(); i++){
			VariableInfoLeafType varInfo = variables.get(i).getLatestDefinition().getDefinedVariable();
			ObjectInfoType current = null;
			if(varInfo.getFieldInfo() != null){
				current = varInfo.getFieldInfo().getValue().getObjectInfo();
			}else if(varInfo.getLocalVariableInfo() != null){
				current = varInfo.getLocalVariableInfo().getValue().getObjectInfo();
			}
			if(current == null) continue;
			return i;
		}
		return -1;
	}
	
	private static boolean isSameValue(String value1,String value2){
		if(value1.equals(value2)) return true;
		if(value1.equals("true") && value2.equals("1")) return true;
		if(value1.equals("1") && value2.equals("true")) return true;
		if(value1.equals("false") && value2.equals("0")) return true;
		if(value1.equals("0") && value2.equals("false")) return true;
		if(value1.length() == 1 && value2.length() == 1){	//char
			if(Character.getNumericValue(value1.charAt(0)) == Character.getNumericValue(value2.charAt(0))) return true;
		}
		return false;

	}
	
	private static boolean isSameType(String type1,String type2){
		switch (type1) {
		case "boolean":
		case "byte":
		case "char":
		case "int":
		case "short":
			if(type2.equals("boolean") || type2.equals("byte") || type2.equals("char")
					|| type2.equals("int") || type2.equals("short"))
				return true;
			return false;
		case "double":
			return type1.equals(type2);
		case "float":
			return type1.equals(type2);
		case "long":
			return type1.equals(type2);
		default:	//配列またはオブジェクト
			return true;
		}
	}
	
	/**
	 * スタック上のブロックをいくつ必要とするか
	 * 通常4バイトだが、Doubleなどでは8バイト
	 * @param type 型名 "int" "double" ...
	 * @return バイト数(1 or 2)
	 */
	private static int getBlockSize(String type){
		switch (type) {
		case "boolean":
		case "byte":
		case "char":
		case "int":
		case "short":
			return 1;
		case "double":
		case "float":
		case "long":
			return 2;
		default:	//配列またはオブジェクト
			return 1;
		}
	}
}
