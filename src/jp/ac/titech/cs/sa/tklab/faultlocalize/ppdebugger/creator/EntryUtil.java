package jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.creator;

import java.util.List;

import jp.ac.nagoya_u.is.i.agusa.person.knhr.bxmodel.MethodSignature;
import jp.ac.nagoya_u.is.i.agusa.person.knhr.bxmodel.ObjectInfoType;
import jp.ac.nagoya_u.is.i.agusa.person.knhr.bxmodel.PrimitiveValueInfo;
import jp.ac.nagoya_u.is.i.agusa.person.knhr.bxmodel.VariableInfoLeafType;
import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.model.execution.Variable;

/**
 * MethodEntryVisitor,ConstructorEntryVisitorで使用されるクラス
 * 仮引数と実引数の対応関係を判断する
 * @author nakano
 *
 */
public class EntryUtil {
	
	static int getArgsNum(String returnType){
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
	
	static boolean isStaticMethod(MethodSignature signature){
		String returnType = signature.getReturnType();
		if(getArgsNum(returnType) != signature.getArgumentTypes().getTypeNames().size()){	//スタティックメソッド以外だと最初の引数がインスタンス自身になる
			return false;
		}
		return true;
	}
	
	
	/**
	 * valueと等しいものがvariables内にあるか調べる。
	 * @param valueInfo
	 * @param variables
	 * @return なければ-1、あればリスト内のインデックスを返す。
	 */
	static int match(PrimitiveValueInfo valueInfo,List<Variable> variables){
		if(valueInfo.getPrimitiveValue() == null) return -1;
		for(int i= 0; i < variables.size(); i++){
			VariableInfoLeafType varInfo = variables.get(i).getLatestDefinition().getDefinedVariable();
			PrimitiveValueInfo current = null;
			if(varInfo.getFieldInfo() != null && varInfo.getFieldInfo().getValue() != null){
				current = varInfo.getFieldInfo().getValue().getPrimitiveValueInfo();
			}else if(varInfo.getLocalVariableInfo() != null && varInfo.getLocalVariableInfo().getValue() != null){
				current = varInfo.getLocalVariableInfo().getValue().getPrimitiveValueInfo();
			}
			if(current == null || current.getPrimitiveValue() == null) continue;
			if(isSameType(valueInfo.getPrimitiveTypeName(), current.getPrimitiveTypeName())
						&& isSameValue(valueInfo.getPrimitiveValue(), current.getPrimitiveValue()))
					return i;
		}
		return -1;
	}
	static int match(ObjectInfoType valueInfo,List<Variable> variables){
		for(int i=0; i < variables.size(); i++){
			VariableInfoLeafType varInfo = variables.get(i).getLatestDefinition().getDefinedVariable();
			ObjectInfoType current = null;
			if(varInfo.getFieldInfo() != null && varInfo.getFieldInfo().getValue() != null){
				current = varInfo.getFieldInfo().getValue().getObjectInfo();
			}else if(varInfo.getLocalVariableInfo() != null && varInfo.getLocalVariableInfo().getValue() != null){
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
	
	static boolean isSameType(String type1,String type2){
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
	
	static boolean isPrimitiveType(String type){
		if(type.equals("byte") || type.equals("char") || type.equals("double") || type.equals("float")
				|| type.equals("int") || type.equals("long") || type.equals("short") || type.equals("boolean"))
			return true;
		
		return false;
	}
	
	/**
	 * スタック上のブロックをいくつ必要とするか
	 * 通常4バイトだが、Doubleなどでは8バイト
	 * @param type 型名 "int" "double" ...
	 * @return バイト数(1 or 2)
	 */
	static int getBlockSize(String type){
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
