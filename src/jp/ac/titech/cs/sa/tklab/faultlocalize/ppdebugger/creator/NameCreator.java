package jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.creator;

import jp.ac.nagoya_u.is.i.agusa.person.knhr.bxmodel.ConstructorEntry;
import jp.ac.nagoya_u.is.i.agusa.person.knhr.bxmodel.FieldInfo;
import jp.ac.nagoya_u.is.i.agusa.person.knhr.bxmodel.LocalVariableInfo;
import jp.ac.nagoya_u.is.i.agusa.person.knhr.bxmodel.MethodEntry;
import jp.ac.nagoya_u.is.i.agusa.person.knhr.bxmodel.VariableDefinition;
import jp.ac.nagoya_u.is.i.agusa.person.knhr.bxmodel.VariableReference;

public class NameCreator {
	private static final char DELIMITER = '#';
	private static final char METHODNAME_DELIMITER = ':';
	private static final String STATIC = "Static";
	private static final String CONSTRUCTOR_NAME = "Constructor";
	private static final String LOCAL_NAME = "*Local";
	private static final String PARAM_NAME = "*Param";
	private static final String RETURN_NAME = "*Return";
	
	
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
	
	public static String removeSuffix(String name){
		return name.replaceAll(DELIMITER + ".+$", "");
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
				if(fieldInfo.getOwnerObject().getObjectId() != null){
					return fieldInfo.getVariableName() + DELIMITER + fieldInfo.getOwnerObject().getObjectId();
				}else{
					return fieldInfo.getVariableName() + DELIMITER  + fieldInfo.getOwnerObject().getClassName() + DELIMITER + STATIC;
				}
			}else{
				return fieldInfo.getVariableName();
			}
		}else if(localInfo != null){
			return scope.getMethodName() + localInfo.getVariableName() + DELIMITER + scope.getScope();
		}
		return null;
	}
	
	public static String createParamArgsName(Scope scope,int paramNo){
		return scope.getMethodName() + PARAM_NAME + paramNo + DELIMITER + scope.getScope();
	}
	
	public static String createActuallArgsName(Scope scope,int stackAddress){
		return scope.getMethodName() + LOCAL_NAME + stackAddress + DELIMITER + scope.getScope();
	}
	
	public static String createReturnName(Scope scope){
		return scope.getMethodName() + RETURN_NAME + DELIMITER + scope.getScope();
	}
}
