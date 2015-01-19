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
	private int eventNumber;
	
	
	/**
	 * Creatorによって呼ばれるコンストラクタ
	 */
	public DataDependencySet(StatementData sd,DataDependency dd,int eventNumber){
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
		this(sd, dd, Integer.valueOf(eventNumber));
	}
	
	
	/**
	 * propagatorに呼ばれるコンストラクタ
	 * @param sd
	 * @param varName
	 * @param eventNumber
	 * @param set
	 * @param label
	 */
	public DataDependencySet(StatementData sd,String varName,int eventNumber ,Set<DataDependency> set,boolean label){
		this.sd = sd;
		this.varName = varName;
		this.eventNumber = eventNumber;
		this.set = set;
		this.label = label;
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
	
	public int getEventNumber(){
		return eventNumber;
	}
	
	/**
	 * eventNumbersは異なってよい
	 * @param dds 比較するインスタンス
	 * @return 同様ならtrue、それ以外でfalse
	 */
	public boolean isSame(DataDependencySet comparedds){
		if(comparedds.label != label) return false;
		if(!sd.equals(comparedds.sd)) return false;
		if(!varName.equals(comparedds.varName)) return false;
		
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
		StringBuilder sb = new StringBuilder();
		String SEP = System.lineSeparator();
		sb.append("D(" + NameCreator.compressMethodName(varName) + "," + sd.toString() +")");
		if(label){
			sb.append("L");
		}
		sb.append("= {");
		sb.append(SEP);
		List<DataDependency> ddList = new ArrayList<DataDependency>(set);
		Collections.sort(ddList);
		for(DataDependency dd : ddList){
			sb.append("\t");
			sb.append(dd.toString());
			sb.append(",");
			sb.append(SEP);
		}
		sb.append("}");
		return sb.toString();
	}

	@Override
	public int compareTo(DataDependencySet o) {
		if(sd.equals(o.sd)){
			if(varName.equals(o.varName)){
				if(set.size() == o.set.size()){
					if(label == o.label) return 0;
					if(label == true) return 1;
					return -1;
				}else if (set.size() < o.set.size()){ 
					return 1;
				}else{
					return-1;
				}
			}else{
				return varName.compareTo(o.varName);
			}
		}else{
			return sd.compareTo(o.sd);
		}
	}

}