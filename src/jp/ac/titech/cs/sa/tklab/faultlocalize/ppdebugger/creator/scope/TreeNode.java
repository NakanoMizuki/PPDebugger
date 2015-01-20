package jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.creator.scope;

import java.util.ArrayList;
import java.util.List;


public class TreeNode {
	public static final TreeNode ROOT = new TreeNode();
	
	private final int value;
	private final TreeNode parent;
	private final List<TreeNode> children;
	
	private TreeNode(){
		value = -1;
		parent = null;
		children = new ArrayList<TreeNode>();
	}
	private TreeNode(int value,TreeNode parent){
		this.value = value;
		this.parent = parent;
		children = new ArrayList<TreeNode>();
		parent.children.add(this);
	}
	
	
	/** valueとインデックスが等しいことを前提としている **/
	public TreeNode getChildNode(int value){
		if(children.size() > value){
			return children.get(value);
		}
		
		synchronized (this) {
			if(children.size() <= value){
				children.add(new TreeNode(value,this));
			}
		}
		return children.get(value);
	}
	
	public int getValue(){
		return value;
	}
	public TreeNode getParent(){
		return parent;
	}
	
}
