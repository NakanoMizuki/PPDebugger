package jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.creator;


import java.util.List;

import jp.ac.nagoya_u.is.i.agusa.person.knhr.bxmodel.MethodEntry;
import jp.ac.nagoya_u.is.i.agusa.person.knhr.bxmodel.MethodSignature;
import jp.ac.nagoya_u.is.i.agusa.person.knhr.bxmodel.ObjectInfoType;
import jp.ac.nagoya_u.is.i.agusa.person.knhr.bxmodel.PrimitiveValueInfo;
import jp.ac.titech.cs.sa.tklab.faultlocalize.StatementDataFactory;
import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.model.execution.ExecutionModel;
import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.model.execution.LineVariable;
import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.model.execution.Variable;

class MethodEntryCreator {
	static void create(ExecutionModel em,MethodEntry me,LineVariable lineVar,StatementDataFactory factory){
		if(isSkip(me)) return;
		createDataDependency(em,me,lineVar,factory);
	}
	
	private static boolean isSkip(MethodEntry me){
		if(me.getMethodSignature() == null) return true;
		MethodSignature signature = me.getMethodSignature();
		if(signature.getReturnType() == null) return true;
		
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
				args = args.substring(index);
				break;
			case 'L':
				count ++;
				index = args.charAt(';') +1;
				args.substring(index);
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
	
	private static void createDataDependency(ExecutionModel em,MethodEntry me,LineVariable lineVar,StatementDataFactory factory){
		MethodSignature signature = me.getMethodSignature();
		List<String> argumentTypes = signature.getArgumentTypes().getTypeNames();
		List<Object> argumentValues = me.getArgumentValues().getPrimitiveValueInfosAndObjectInfos();
		
		int start = (isStaticMethod(signature)) ? 0:1;
		for(int i = argumentTypes.size() -1; start <= i; i--){	//直前の要素から見る
			String type = argumentTypes.get(i);
			int index = 0;
			if(isPrimitiveType(type)){
				PrimitiveValueInfo value = (PrimitiveValueInfo)argumentValues.get(i);
				index = match(value, lineVar.getVariables());
			}else{
				ObjectInfoType value = (ObjectInfoType) argumentValues.get(i);
				index = match(value, lineVar.getVariables());
			}
			if(index != -1){
				
			}
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
	 * @param value
	 * @param variables
	 * @return なければ-1、あればリスト内のインデックスを返す。
	 */
	private static int match(PrimitiveValueInfo value,List<Variable> variables){
		return -1;
	}
	private static int match(ObjectInfoType value,List<Variable> variables){
		return -1;
	}
}
