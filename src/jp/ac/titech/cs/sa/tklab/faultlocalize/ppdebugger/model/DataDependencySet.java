package jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.model;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import jp.ac.titech.cs.sa.tklab.faultlocalize.StatementData;

public class DataDependencySet{
	private String varName;
	private final StatementData sd;
	private Set<DataDependency> set;
	private boolean label=false;
	private Set<Long> eventNumbers;
	
	
	/**
	 * Creatorによって呼ばれるコンストラクタ
	 * @param sd
	 * @param dd
	 */
	public DataDependencySet(StatementData sd,DataDependency dd){
		this.sd = sd;
		this.varName = dd.getVarName();
		set = new HashSet<DataDependency>();
		set.add(dd);
		if(dd.getStatementData().equals(sd)){
			label = true;
		}
		eventNumbers = new TreeSet<Long>();
	}
	
	/**
	 * 依存伝播時に使われるコンストラクタ 
	 * @param sd
	 * @param varName
	 * @param set
	 */
	public DataDependencySet(StatementData sd,String varName,Set<DataDependency> set,boolean label){
		this.sd = sd;
		this.varName = varName;
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
	
	/**
	 * 名前が一致するか調べる
	 * ただし、ラベルがついているものとついていないものは別物とする
	 * @param dds 比較するインスタンス
	 * @return 同様の名前ならtrue、それ以外でfalse
	 */
	public boolean isSameName(DataDependencySet dds){
		if(dds.label != label) return false;
		if(dds.sd.equals(sd) && dds.varName.equals(varName)) return true;
		return false;
	}
	
	/**
	 * 中身もすべて等しい
	 */
	@Override
	public boolean equals(Object o){
		if(! (o instanceof DataDependencySet)) return false;
		DataDependencySet comparedds = (DataDependencySet) o;
		if(sd.equals(comparedds.sd) && varName.equals(comparedds.varName)){
			//セットの中身の比較
			if(set.size() == comparedds.set.size()){
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
		}	
		
		return false;
	}
	@Override
	public int hashCode(){
		return (sd.toString() + varName).hashCode();
	}
	
	@Override
	public String toString(){
		String str = "D("+varName + "," + sd.toString() +")";
		if(label){
			str += "L";
		}
		str += ":\n";
		for(DataDependency dd : set){
			str += "\t" + dd.toString() + ",\n";
		}
		return str;
	}

}