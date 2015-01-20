package jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.model;

import java.util.Objects;

import jp.ac.titech.cs.sa.tklab.faultlocalize.StatementData;
import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.FastIntern;
import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.creator.NameCreator;

/**
 * 村松さんの論文の　d(x)=l(x)　に対応するクラス
 * xが変数名、l(x)がxを最後に定義した行番号
 * @author nakano
 *
 */
public class DataDependency implements Comparable<DataDependency>{
	private final String varName;
	private String suffix;
	private final StatementData sd;
	
	
	public DataDependency(String varName,StatementData sd){
		String[] tokens = varName.split(Character.toString(NameCreator.DELIMITER)); 
		this.varName = FastIntern.get(tokens[0]);
		this.suffix = (tokens.length == 2) ? FastIntern.get(tokens[1]): FastIntern.get("");
		this.sd = sd;
	}
	
	public String getVarName(){
		return varName + NameCreator.DELIMITER + suffix;
	}
	public String getVarNameWithoutSuffix(){
		return varName;
	}
	
	public StatementData getStatementData(){
		return sd;
	}
	
	public void removeSuffix(){
		suffix = FastIntern.get("");
	}
	
	@Override
	public boolean equals(Object o){
		if(Objects.isNull(o)) return false;
		if(! (o instanceof DataDependency)) return false;
		DataDependency dd = (DataDependency) o;
		if(varName.equals(dd.varName) && (suffix.equals(dd.suffix)) && sd.equals(dd.sd)) return true;
		
		return false;
	}
	@Override
	public int hashCode(){
		return varName.hashCode() ^ suffix.hashCode() ^ sd.hashCode();
	}
	@Override
	public String toString(){
		return "d(" + NameCreator.compressMethodName(varName) + ")=" + sd.toString();
	}

	@Override
	public int compareTo(DataDependency o) {
		int sdComp = sd.compareTo(o.sd);
		if(sdComp != 0) 
			return sdComp;
		
		int comp = varName.compareTo(o.varName);
		if(comp == 0)
			return suffix.compareTo(o.suffix);
		
		return varName.compareTo(o.varName);
	}
}
