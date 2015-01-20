package jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.model;

import java.util.Objects;

import jp.ac.titech.cs.sa.tklab.faultlocalize.StatementData;
import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.FastIntern;
import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.creator.NameCreator;
import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.creator.scope.TreeNode;

/**
 * 村松さんの論文の　d(x)=l(x)　に対応するクラス
 * xが変数名、l(x)がxを最後に定義した行番号
 * @author nakano
 *
 */
public class DataDependency implements Comparable<DataDependency>{
	private final String varName;
	private final TreeNode scope;
	private final StatementData sd;
	
	
	public DataDependency(String varName,TreeNode scopeNode,StatementData sd){
		this.varName = FastIntern.get(varName);
		this.scope = scopeNode;
		this.sd = sd;
	}
	
	public String getVarName(){
		return varName;
	}
	
	public StatementData getStatementData(){
		return sd;
	}
	
	public TreeNode getTreeNode(){
		return scope;
	}
	
	
	@Override
	public boolean equals(Object o){
		if(Objects.isNull(o)) return false;
		if(! (o instanceof DataDependency)) return false;
		DataDependency dd = (DataDependency) o;
		if(!varName.equals(dd.varName)) return false;
		if(scope != dd.scope) return false;
		if(!sd.equals(dd.sd)) return false;
		
		return true;
	}
	@Override
	public int hashCode(){
		return varName.hashCode() ^ sd.hashCode();
	}
	@Override
	public String toString(){
		return "d(" + NameCreator.compressMethodName(varName) + ")=" + sd.toString();
	}

	@Override
	public int compareTo(DataDependency o) {
		int sdComp = sd.compareTo(o.sd);
		if(sdComp != 0) return sdComp;
		
		return varName.compareTo(o.varName);
	}
}
