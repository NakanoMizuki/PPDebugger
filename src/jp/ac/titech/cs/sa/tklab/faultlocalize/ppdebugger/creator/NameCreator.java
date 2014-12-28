package jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.creator;

import jp.ac.nagoya_u.is.i.agusa.person.knhr.bxmodel.ConstructorEntry;
import jp.ac.nagoya_u.is.i.agusa.person.knhr.bxmodel.FieldInfo;
import jp.ac.nagoya_u.is.i.agusa.person.knhr.bxmodel.LocalVariableInfo;
import jp.ac.nagoya_u.is.i.agusa.person.knhr.bxmodel.MethodEntry;
import jp.ac.nagoya_u.is.i.agusa.person.knhr.bxmodel.VariableDefinition;
import jp.ac.nagoya_u.is.i.agusa.person.knhr.bxmodel.VariableReference;

public class NameCreator {
	private static final String DELIMITER = "#";
	private static final String STATIC = "st";
	
	
	public static String getOriginalName(String name){
		return name.replaceAll(DELIMITER + ".+$", "");
	}
	
	/*
	public static String createMethodName(MethodEntry entry){
		String name = entry.getCallerSourcePath();
		name += DELIMITER + entry.getMethodSignature().getMethodName();
		name += DELIMITER + entry.getMethodSignature().getReturnType();
		name += DELIMITER + entry.getMethodSignature().getArgumentTypes();
		return name;
	}
	
	public static String createMethodName(ConstructorEntry entry){
		String name = entry.getCallerSourcePath();
		name += DELIMITER + entry.getMethodSignature().getMethodName();
		name += DELIMITER + entry.getMethodSignature().getReturnType();
		name += DELIMITER + entry.getMethodSignature().getArgumentTypes();
		return name;
	}
	*/
	
	public static String createVariableName(VariableDefinition def,String scope){
		return createVariableName(def.getDefinedVariable().getFieldInfo(),def.getDefinedVariable().getLocalVariableInfo(), scope);
	}
	
	public static String createVariableName(VariableReference ref,String scope){
		return createVariableName(ref.getReferredVariable().getFieldInfo(),ref.getReferredVariable().getLocalVariableInfo(),scope);
	}
	
	private static String createVariableName(FieldInfo fieldInfo,LocalVariableInfo localInfo,String scope){
		if(fieldInfo != null){
			if(fieldInfo.getOwnerObject().getObjectId() != null){
				return fieldInfo.getVariableName() + DELIMITER + fieldInfo.getOwnerObject().getObjectId();
			}else{
				return fieldInfo.getVariableName() + DELIMITER  + fieldInfo.getOwnerObject().getClassName() + DELIMITER + STATIC;
			}
		}else if(localInfo != null){
			return localInfo.getVariableName() + DELIMITER + scope;
		}
		return null;
	}
}
