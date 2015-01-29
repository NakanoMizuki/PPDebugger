package jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.model.learned;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jp.ac.titech.cs.sa.tklab.faultlocalize.StatementData;
import jp.ac.titech.cs.sa.tklab.faultlocalize.out.IOut;
import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.model.DataDependencySet;
import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.model.execution.Statement;

public class StatementState implements Comparable<StatementState> {
	private int executedCount;
	private final StatementData sd;
	private final Map<DataDependencySet,DataDependencySet> ddsMap;
	private final Map<DataDependencySet,Integer> countMap;
	
	public StatementState(StatementData sd){
		executedCount = 0;
		this.sd = sd;
		ddsMap = new Hashtable<DataDependencySet,DataDependencySet>();
		countMap = new HashMap<DataDependencySet,Integer>();
	}
	
	
	public void addCount(){
		executedCount ++;
	}
	
	public StatementData getStatementData(){
		return sd;
	}
	
	public double getProb(DataDependencySet dds){
		DataDependencySet content = ddsMap.get(dds);
		if(content == null) return 0;
		return (double) countMap.get(content)/executedCount;
	}
	
	private void addDataDependencySet(DataDependencySet dds){
		DataDependencySet content = ddsMap.get(dds);
		if(content != null){
			int count = countMap.get(content) + 1;
			countMap.put(content, count);
		}else{
			ddsMap.put(dds, dds);
			countMap.put(dds, 1);
		}
	}
	
	public Set<DataDependencySet> getDataDependencySets(){
		return ddsMap.keySet();
	}
	
	public void addStatement(Statement st){
		for(DataDependencySet dds : st.getDataDependencySets()){
			this.addDataDependencySet(dds);
		}
	}
	
	/**
	 * 全ての状態（データ依存伝搬）を出力するメソッド
	 * @param out 出力形式
	 */
	public void printState(IOut out){
		out.println(sd.toString());
		List<DataDependencySet> ddslist = new ArrayList<DataDependencySet>(countMap.keySet());
		Collections.sort(ddslist);
		String sep = System.lineSeparator();
		for(DataDependencySet dds: ddslist ){
			out.println("\t" + dds.toString().replaceAll(sep, sep + "\t") + "\tcount=" + countMap.get(dds));
		}
	}
	
	@Override
	public int compareTo(StatementState stst){
		if(sd.getSourcePath().equals(stst.getStatementData().getSourcePath()) ){
			if(sd.getLineNumber() < stst.getStatementData().getLineNumber()){
				return -1;
			}else{
				return 1;
			}
		}else{
			return sd.getSourcePath().compareTo(stst.getStatementData().getSourcePath());
		}
	}
	
	@Override
	public String toString(){
		return sd.toString();
	}
}
