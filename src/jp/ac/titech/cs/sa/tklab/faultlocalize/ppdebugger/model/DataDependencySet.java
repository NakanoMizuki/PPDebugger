package jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jp.ac.titech.cs.sa.tklab.faultlocalize.StatementData;
import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.creator.NameCreator;

public class DataDependencySet implements Comparable<DataDependencySet>{
	private String varName;
	private final StatementData sd;
	private Set<DataDependency> set;
	private final boolean label;
	private long eventNumber;
	
	
	/**
	 * Creatorによって呼ばれるコンストラクタ
	 * @param sd
	 * @param dd
	 */
	public DataDependencySet(StatementData sd,DataDependency dd,long eventNumber){
		this.sd = sd;
		this.varName = dd.getVarName();
		set = new HashSet<DataDependency>();
		set.add(dd);
		this.eventNumber = eventNumber;
		if(dd.getStatementData().equals(sd)){
			label = true;
		}else{
			label = false;
		}
	}
	public DataDependencySet(StatementData sd,DataDependency dd,String eventNumber) {
		this(sd, dd, Long.valueOf(eventNumber));
	}
	
	
	/**
	 * 依存伝播時に使われるコンストラクタ 
	 * @param sd
	 * @param varName
	 * @param set
	 */
	public DataDependencySet(StatementData sd,String varName,long eventNumber ,Set<DataDependency> set,boolean label){
		this.sd = sd;
		this.varName = varName;
		this.eventNumber = eventNumber;
		this.set = set;
		this.label = label;
		eventNumber = -1;
	}
	
	
	public void addDataDependency(DataDependency dd){
		set.add(dd);
	}
	
	public StatementData getStatementData(){
		return sd;
	}
	public String getVarName(){
		return varName;
	}
	
	public void setVarName(String name){
		varName = name;
	}
	
	public Set<DataDependency> getSet(){
		return set;
	}
	
	public void setDDSet(Set<DataDependency> set){
		this.set = set;
	}

	public boolean isLabeled(){
		return label;
	}
	
	public void setEventNumber(int eventNumber){
		this.eventNumber = eventNumber;
	}
	
	public long getEventNumber(){
		return eventNumber;
	}
	
	/**
	 * eventNumbersは異なってよい
	 * @param dds 比較するインスタンス
	 * @return 同様ならtrue、それ以外でfalse
	 */
	public boolean isSame(DataDependencySet comparedds){
		if(comparedds.label != label) return false;
		if(!sd.equals(comparedds.sd) ||  !varName.equals(comparedds.varName)) return false;
		
		//セットの中身の比較
		if(set.size() != comparedds.set.size()) return false;
		label:for(DataDependency dd1 :set){
			for(DataDependency dd2 : comparedds.set){			//同一のDataDependencyが存在するか
				if(dd1.equals(dd2)){
					continue label;
				}
			}
			return false;				//中身のDataDependencyがひとつでも違ったら別の物
		}
		
		return true;
	}
	
	/**
	 * eventNumberも等しい
	 */
	@Override
	public boolean equals(Object o){
		if(! (o instanceof DataDependencySet)) return false;
		DataDependencySet comparedds = (DataDependencySet) o;
		if(eventNumber != comparedds.eventNumber) return false;
		return isSame(comparedds);
	}
	
	@Override
	public int hashCode(){
		return sd.hashCode() + varName.hashCode();
	}
	
	@Override
	public String toString(){
		String str = "D(" + NameCreator.compressMethodName(varName) + "," + sd.toString() +")";
		if(label){
			str += "L";
		}
		str += ":" + System.lineSeparator();
		List<DataDependency> ddList = new ArrayList<DataDependency>(set);
		Collections.sort(ddList);
		for(DataDependency dd : ddList){
			str += "\t" + dd.toString() + "," + System.lineSeparator();
		}
		return str;
	}

	@Override
	public int compareTo(DataDependencySet o) {
		if(sd.equals(o.sd)){
			if(varName.equals(o.varName)){
				if (set.size() < o.set.size()) return 1;
				else return -1;
			}else{
				return varName.compareTo(o.varName);
			}
		}else{
			return sd.compareTo(o.sd);
		}
	}

}