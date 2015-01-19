package jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.model;

import jp.ac.titech.cs.sa.tklab.faultlocalize.StatementData;
import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.creator.NameCreator;

/**
 * 村松さんの論文の　d(x)=l(x)　に対応するクラス
 * xが変数名、l(x)がxを最後に定義した行番号
 * @author nakano
 *
 */
public class DataDependency implements Comparable<DataDependency>{
	private final String varName;
	private final StatementData sd;
	
	
	public DataDependency(String varName,StatementData sd){
		this.varName = varName.intern();
		this.sd = sd;
	}
	
	public String getVarName(){
		return varName;
	}
	public StatementData getStatementData(){
		return sd;
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
		return varName.hashCode() + sd.hashCode();
	}
	@Override
	public String toString(){
		return "d(" + NameCreator.compressMethodName(varName) + ")=" + sd.toString();
	}

	@Override
	public int compareTo(DataDependency o) {
		if(sd.equals(o.sd)){
			return varName.compareTo(o.varName);
		}else{
			return sd.compareTo(o.sd);
		}
	}
}
