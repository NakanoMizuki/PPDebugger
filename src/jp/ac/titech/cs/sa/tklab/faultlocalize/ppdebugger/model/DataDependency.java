package jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.model;

import jp.ac.titech.cs.sa.tklab.faultlocalize.StatementData;

/**
 * 村松さんの論文の　d(x)=l(x)　に対応するクラス
 * xが変数名、l(x)がxを最後に定義した行番号
 * @author nakano
 *
 */
public class DataDependency {
	private String varName;
	private final StatementData sd;
	
	
	public DataDependency(String varName,StatementData sd){
		this.varName = varName;
		this.sd = sd;
	}
	
	public String getVarName(){
		return varName;
	}
	public StatementData getStatementData(){
		return sd;
	}
	
	public void setVarName(String varName) {
		this.varName = varName;
	}
	
	@Override
	public boolean equals(Object o){
		if(! (o instanceof DataDependency)) return false;
		DataDependency dd = (DataDependency) o;
		if(varName.equals(dd.varName) && sd.equals(dd.sd)) return true;
		
		return false;
	}
	@Override
	public int hashCode(){
		return (varName + sd.toString()).hashCode();
	}
	@Override
	public String toString(){
		return "d(" + varName + ")=" + sd.toString();
	}
}
