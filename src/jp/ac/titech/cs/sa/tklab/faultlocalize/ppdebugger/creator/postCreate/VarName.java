package jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.creator.postCreate;

import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.creator.scope.TreeNode;

public class VarName {
	private final String name;
	private final TreeNode scope;
	
	public VarName(String name,TreeNode scope){
		this.name = name;
		this.scope = scope;
	}
	
	
	
	@Override
	public String toString(){
		return name;
	}
	@Override
	public boolean equals(Object o){
		if(o == null || !(o instanceof VarName)) return false;
		VarName compare = (VarName)o;
		if(name.equals(compare.name) && scope == compare.scope) return true;
		return false;
	}
	@Override
	public int hashCode(){
		return name.hashCode();
	}
}
