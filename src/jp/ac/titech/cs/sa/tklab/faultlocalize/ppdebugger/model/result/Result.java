package jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.model.result;

import java.util.Collections;
import java.util.List;

import jp.ac.titech.cs.sa.tklab.faultlocalize.StatementData;

public class Result {
	private final String fileName;
	private final List<StatementProb> stProbs;
	
	public Result(String fileName,List<StatementProb> list){
		this.fileName = fileName;
		Collections.sort(list);
		this.stProbs = list;
	}
	
	public List<StatementProb> getStatementProbs(){
		return stProbs;
	}
	
	public int calcScore(StatementData fault){
		int score = -1;
		for(int i=0; i < stProbs.size() ; i++ ){
			if(stProbs.get(i).getStatementData().isSame(fault)){
				score = i+1;
				return score;
			}
		}
		return score;
	}
	
	//すべての欠陥行を見つけるまでの最大のスコアを返す。ひとつでも見つからない欠陥があったら-1
	public int calcScore(List<StatementData> faults){
		int score = 0;
		for(StatementData fault :faults){
			int tmp = calcScore(fault);
			if(tmp == -1){
				return -1;
			}else{
				score = Math.max(score, tmp);
			}
		}
		return score;
	}
	
	@Override
	public String toString(){
		String sep = System.lineSeparator();
		String str = fileName + " Result----------" + sep;
		for(int i=0; i < stProbs.size(); i ++){
			StatementProb stProb = stProbs.get(i);
			str += (i+1) + ":\t" + stProb.toString() + sep;
		}
		return str;
	}
}
