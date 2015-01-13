package jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.creator;

import jp.ac.nagoya_u.is.i.agusa.person.knhr.bxmodel.ConstructorEntry;
import jp.ac.nagoya_u.is.i.agusa.person.knhr.bxmodel.FieldInfo;
import jp.ac.nagoya_u.is.i.agusa.person.knhr.bxmodel.LocalVariableInfo;
import jp.ac.nagoya_u.is.i.agusa.person.knhr.bxmodel.MethodEntry;
import jp.ac.nagoya_u.is.i.agusa.person.knhr.bxmodel.VariableDefinition;
import jp.ac.nagoya_u.is.i.agusa.person.knhr.bxmodel.VariableReference;

public class NameCreator {
	private static final String DELIMITER = "#";
	private static final String METHODNAME_DELIMITER = ":";
	private static final String STATIC = "Static";
	
	
	public static String getOriginalName(String name){
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
	
	public static String createMethodName(ConstructorEntry entry){
		String name = "";
		if(entry.getCallerSourcePath() != null){	//mainメソッドはCallerSourcePathを持たない
			name += entry.getCallerSourcePath() + METHODNAME_DELIMITER;
		}
		name += entry.getMethodSignature().getMethodName();
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
	
	public static String createVariableName(Scope scope,int stackAddress){
		return scope.getMethodName() + stackAddress + DELIMITER + scope.getScope();
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
}
