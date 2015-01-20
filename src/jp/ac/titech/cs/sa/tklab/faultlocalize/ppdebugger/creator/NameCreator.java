package jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.creator;

import jp.ac.nagoya_u.is.i.agusa.person.knhr.bxmodel.ConstructorEntry;
import jp.ac.nagoya_u.is.i.agusa.person.knhr.bxmodel.FieldInfo;
import jp.ac.nagoya_u.is.i.agusa.person.knhr.bxmodel.LocalVariableInfo;
import jp.ac.nagoya_u.is.i.agusa.person.knhr.bxmodel.MethodEntry;
import jp.ac.nagoya_u.is.i.agusa.person.knhr.bxmodel.VariableDefinition;
import jp.ac.nagoya_u.is.i.agusa.person.knhr.bxmodel.VariableReference;
import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.creator.scope.Scope;

public class NameCreator {
	public static final char DELIMITER = '#';
	private static final char METHODNAME_DELIMITER = ':';
	private static final String STATIC = "Static";
	private static final String CONSTRUCTOR_NAME = "Constructor";
	private static final String LOCAL_NAME = "*Local";
	private static final String PARAM_NAME = "*Param";
	private static final String RETURN_NAME = "*Return";
	private static final String ARRAY_NAME = "*ARRAY";
	
	
	public static String compressMethodName(String name){
		int index = name.lastIndexOf(METHODNAME_DELIMITER);
		if(index == -1) return name;
		String prefix = name.substring(0,index);
		prefix = prefix.substring(0,prefix.lastIndexOf(METHODNAME_DELIMITER));
		index = prefix.indexOf(METHODNAME_DELIMITER);
		if(index != -1){
			prefix = prefix.substring(index+1);
		}
		String latter = name.substring(name.lastIndexOf(METHODNAME_DELIMITER) +1);
		return prefix + METHODNAME_DELIMITER + latter;
	}
	
	public static String createMethodName(MethodEntry entry){
		String name = "";
		if(entry.getCallerSourcePath() != null){	//mainメソッドはCallerSourcePathを持たない
			name += entry.getCallerSourcePath() + METHODNAME_DELIMITER;
		}
		name += entry.getMethodSignature().getMethodName();
		name += METHODNAME_DELIMITER + entry.getMethodSignature().getReturnType();
		name += METHODNAME_DELIMITER;
		return name;
	}
	
	public static String createConstructorName(ConstructorEntry entry){
		String name = "";
		if(entry.getCallerSourcePath() != null){	//mainメソッドはCallerSourcePathを持たない
			name += entry.getCallerSourcePath() + METHODNAME_DELIMITER;
		}
		name += CONSTRUCTOR_NAME;
		name += METHODNAME_DELIMITER + entry.getMethodSignature().getReturnType();
		name += METHODNAME_DELIMITER;
		return name;
	}
	
	public static String createVariableName(VariableDefinition def,Scope scope){
		return createVariableName(def.getDefinedVariable().getFieldInfo(),def.getDefinedVariable().getLocalVariableInfo(), scope);
	}
	
	public static String createVariableName(VariableReference ref,Scope scope){
		return createVariableName(ref.getReferredVariable().getFieldInfo(),ref.getReferredVariable().getLocalVariableInfo(),scope);
	}
	
	private static String createVariableName(FieldInfo fieldInfo,LocalVariableInfo localInfo,Scope scope){
		if(fieldInfo != null){
			if(fieldInfo.getOwnerObject() != null){	//普通のフィールド
				String varName = fieldInfo.getVariableName();
				if(varName.startsWith(ARRAY_NAME)){
					varName = varName.replaceAll("-.+$", "");		//配列の場合、配列のID-添え字　の形で名前が与えられる
				}
				if(fieldInfo.getOwnerObject().getObjectId() != null){
					return varName + DELIMITER + fieldInfo.getOwnerObject().getObjectId();
				}else{
					return varName + DELIMITER  + fieldInfo.getOwnerObject().getClassName() + DELIMITER + STATIC;
				}
			}else{
				return fieldInfo.getVariableName();
			}
		}else if(localInfo != null){
			String localName = scope.getMethodName() + localInfo.getVariableName();
			if(scope.isParam(localName)) return scope.getParamName(localName);
			return localName;
		}
		return null;
	}
	
	public static boolean isParam(String varName){
		if(varName.contains(PARAM_NAME)) return true;
		return false;
	}
	
	public static String createActualParamName(Scope scope,int paramNo){
		return scope.getMethodName() + PARAM_NAME + paramNo;
	}
	
	public static String createFormalParamName(Scope scope,int stackAddress){
		return scope.getMethodName() + LOCAL_NAME + stackAddress;
	}
	
	public static String createReturnName(Scope scope){
		return scope.getMethodName() + RETURN_NAME;
	}
}
