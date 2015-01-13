package jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.creator;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Scope {
	private static final String DELIMITER="-";
	private Stack<String> methodNameStack;
	private List<Integer> list;
	private int next;
	
	public Scope(){
		methodNameStack = new Stack<String>();
		list = new ArrayList<Integer>();
		clear();
	}
	
	public void clear(){
		list.clear();
		next = 0;
	}
	
	public void entry(String methodName){
		methodNameStack.push(methodName);
		list.add(next);
	}

	public void exit(){
		methodNameStack.pop();
		next = list.get(list.size()-1) +1;
		list.remove(list.size() -1);
	}
	
	
	public String getMethodName(){
		return methodNameStack.peek();
	}
	
	public String getScope(){
		String str="";
		for(int value : list){
			str += DELIMITER + value;
		}
		return str;
	}
}
