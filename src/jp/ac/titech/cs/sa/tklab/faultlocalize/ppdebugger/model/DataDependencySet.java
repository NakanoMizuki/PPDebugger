package jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jp.ac.titech.cs.sa.tklab.faultlocalize.StatementData;
import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.FastIntern;
import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.creator.NameCreator;
import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.creator.scope.TreeNode;

public class DataDependencySet implements Comparable<DataDependencySet>{
	private final StatementData sd;
	private String varName;
	private TreeNode scope;
	private Set<DataDependency> set;
	private final boolean label;
	private int eventNumber;
	
	
	private DataDependencySet(StatementData sd,String varName,TreeNode scope,boolean label,int eventNumber){
		this.sd = sd;
		this.varName = FastIntern.get(varName);
		this.scope = (NameCreator.isField(this.varName))? null:scope;
		this.set = new HashSet<DataDependency>();
		this.label = label;
		this.eventNumber = eventNumber;
	}
	
	/**
	 * Creatorによって呼ばれるコンストラクタ
	 */
	public DataDependencySet(StatementData sd,DataDependency dd,int eventNumber){
		this(sd, dd.getVarName(),dd.getTreeNode(), (dd.getStatementData().equals(sd)), eventNumber);
		set.add(dd);
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
	public DataDependencySet(StatementData sd,String varName,TreeNode scope,int eventNumber ,Set<DataDependency> set,boolean label){
		this(sd, varName, scope, label, eventNumber);
		this.set = set;
	}
	
	public StatementData getStatementData(){
		return sd;
	}
	
	public String getVarName(){
		return varName;
	}
	
	
	public Set<DataDependency> getSet(){
		return set;
	}
	
	public boolean isLabeled(){
		return label;
	}
	
	public int getEventNumber(){
		return eventNumber;
	}
	
	public void addDataDependency(DataDependency dd){
		set.add(dd);
	}
	
	public void setDDSet(Set<DataDependency> set){
		this.set = set;
	}

	
	/** 伝播終了後不要なものをなくす  */
	public void compress(){
		varName = NameCreator.removeObjectID(varName);
		eventNumber = 0;
		scope = null;
	}
	
	/**
	 * eventNumberは異なってよい
	 * @param dds 比較するインスタンス
	 * @return 同様ならtrue、それ以外でfalse
	 */
	public boolean isSame(DataDependencySet comparedds){
		if(comparedds.label != label) return false;
		if(!sd.equals(comparedds.sd)) return false;
		if(!varName.equals(comparedds.varName)) return false;
		if(!(scope == comparedds.scope)) return false;
		
		//セットの中身の比較
		if(set.size() != comparedds.set.size()) return false;
		loop:for(DataDependency dd1 :set){
			for(DataDependency dd2 : comparedds.set){			//同一のDataDependencyが存在するか
				if(dd1.equals(dd2)){
					continue loop;
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
		return sd.hashCode() ^ varName.hashCode();
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
		int sdComp = sd.compareTo(o.sd);
		if(sdComp != 0) return sdComp;
		
		int varComp = varName.compareTo(o.varName);
		if(varComp != 0) return varComp;
		if(label == true && o.label == false) return -1;
		if(label == false && o.label == true) return 1;
		
		
		int setComp = set.size() - o.set.size();
		if(setComp != 0) return setComp;
		if(scope == null && o.scope == null) return 0;
		if(scope == null) return -1;
		if(o.scope == null) return 1;
		return scope.getValue() - o.scope.getValue();
	}

}