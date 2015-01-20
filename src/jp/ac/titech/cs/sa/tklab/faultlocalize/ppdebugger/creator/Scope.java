package jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.creator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class Scope {
	private static final char DELIMITER='-';
	private Stack<String> methodNameStack;
	private Stack<Map<String, String>> paramMapStack; 
	private List<Integer> list;
	private int next;
	
	public Scope(){
		methodNameStack = new Stack<String>();
		paramMapStack = new Stack<Map<String,String>>();
		list = new ArrayList<Integer>();
		clear();
	}
	
	public void clear(){
		list.clear();
		next = 0;
	}
	
	public void entry(String methodName){
		methodNameStack.push(methodName.intern());
		paramMapStack.add(new HashMap<String, String>());
		list.add(next);
	}

	public void exit(){
		methodNameStack.pop();
		paramMapStack.pop();
		next = list.get(list.size()-1) +1;
		list.remove(list.size() -1);
	}
	
	
	public String getMethodName(){
		return methodNameStack.peek();
	}
	
	public void putParam(String localName,String paramName){
		paramMapStack.peek().put(localName.intern(), paramName.intern());
	}
	
	public boolean isParam(String localName){
		return paramMapStack.peek().containsKey(localName);
	}
	
	public String getParamName(String localName){
		return paramMapStack.peek().get(localName);
	}
	
	public String getScope(){
		StringBuilder sb = new StringBuilder();
		for(int value : list){
			sb.append(Integer.toHexString(value));
			sb.append(DELIMITER);
		}
		sb.deleteCharAt(sb.length()-1);		//最後のDELIMITERを削除
		return sb.toString();
	}
}
