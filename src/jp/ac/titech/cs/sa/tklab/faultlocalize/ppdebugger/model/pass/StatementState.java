package jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.model.pass;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import jp.ac.titech.cs.sa.tklab.faultlocalize.StatementData;
import jp.ac.titech.cs.sa.tklab.faultlocalize.out.IOut;
import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.model.DataDependencySet;
import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.model.execution.Statement;

public class StatementState implements Comparable<StatementState> {
	private final StatementData sd;
	private Map<DataDependencySet,Integer> ddsMap;
	
	public StatementState(StatementData sd){
		this.sd = sd;
		ddsMap = new HashMap<DataDependencySet,Integer>();
	}
	
	public StatementData getStatementData(){
		return sd;
	}
	
	public int getNum(DataDependencySet dds){
		if(ddsMap.containsKey(dds)){
			return ddsMap.get(dds);
		}
		return 0;
	}
	
	private void addDataDependencySet(DataDependencySet dds){
		if(ddsMap.containsKey(dds)){
			int tmp = ddsMap.get(dds) +1;
			ddsMap.put(dds,tmp);
		}else{
			ddsMap.put(dds, 1);
		}
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
		for(Iterator<DataDependencySet> it = ddsMap.keySet().iterator(); it.hasNext(); ){
			DataDependencySet dds = it.next();
			out.println("\t" + dds.toString().replaceAll("\n", "\n\t") + "\tcount=" + ddsMap.get(dds));
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
