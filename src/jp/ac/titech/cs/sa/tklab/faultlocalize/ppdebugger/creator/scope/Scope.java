package jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.creator.scope;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.FastIntern;

public class Scope {
	private Stack<String> methodNameStack;
	private Stack<Map<String, String>> paramMapStack; 
	private int next;
	private TreeNode treeNode;
	
	public Scope(){
		treeNode = TreeNode.ROOT;
		methodNameStack = new Stack<String>();
		paramMapStack = new Stack<Map<String,String>>();
		clear();
	}
	
	public void clear(){
		next = 0;
		treeNode = TreeNode.ROOT;
	}
	
	public void entry(String methodName){
		treeNode = treeNode.getChildNode(next);
		methodNameStack.push(FastIntern.get(methodName));
		paramMapStack.add(new HashMap<String, String>());
		next = 0;
	}

	public void exit(){
		methodNameStack.pop();
		paramMapStack.pop();
		next = treeNode.getValue() +1;
		treeNode = treeNode.getParent();
	}
	
	
	public String getMethodName(){
		return methodNameStack.peek();
	}
	
	public TreeNode getTreeNode(){
		return treeNode;
	}
	
	public void putParam(String localName,String paramName){
		paramMapStack.peek().put(FastIntern.get(localName),FastIntern.get(paramName));
	}
	
	public boolean isParam(String localName){
		return paramMapStack.peek().containsKey(localName);
	}
	
	public String getParamName(String localName){
		return paramMapStack.peek().get(localName);
	}
	
}
