package jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.model.result;

import java.util.Collections;
import java.util.List;

import jp.ac.titech.cs.sa.tklab.faultlocalize.StatementData;
import jp.ac.titech.cs.sa.tklab.faultlocalize.Valuation;

public class Result {
	private final List<StatementProb> stProbs;
	
	public Result(List<StatementProb> list){
		Collections.sort(list);
		this.stProbs = list;
	}
	
	public List<StatementProb> getStatementProbs(){
		return stProbs;
	}
	
	public int calcScore(StatementData fault){
		int score = 1;
		double prob = -1;
		int numSameScore = 0;
		for(int i=0; i < stProbs.size() ; i++ ){
			StatementProb stProb = stProbs.get(i);
			if(stProb.getProb() == prob){
				numSameScore++;
			}else{
				prob = stProb.getProb();
				score += numSameScore;
				numSameScore = 1;
			}
			if(stProb.getStatementData().isSame(fault)){
				return score;
			}
		}
		return -1;
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
	
	public Valuation getValuation(List<StatementData> faults){
		int rank=0;
		int cost=0;
		for(StatementData fault:faults){
			Valuation valuation = getValuation(fault);
			if(valuation == null) return null;
			rank = Math.max(rank, valuation.getRank());
			cost = Math.max(cost, valuation.getCost());
		}
		return new Valuation(rank, cost);
	}
	
	private Valuation getValuation(StatementData fault){
		int rank=1,cost=1;
		double prob = -1;
		int numSameProb = 0;
		boolean rankFlag = false;
		for(int i=0; i < stProbs.size() ; i++ ){
			StatementProb stProb = stProbs.get(i);
			if(stProb.getProb() == prob){
				numSameProb++;
			}else{
				if(rankFlag == true){break;}
				prob = stProb.getProb();
				rank += numSameProb;
				numSameProb = 1;
			}
			if(stProb.getStatementData().isSame(fault)){
				rankFlag = true;
			}
		}
		if(rankFlag == true){
			cost = rank + numSameProb -1;
			return new Valuation(rank,cost);
		}
		return null;
	}
	
	@Override
	public String toString(){
		String sep = System.lineSeparator();
		String str = "Result----------" + sep;
		for(int i=0; i < stProbs.size(); i ++){
			StatementProb stProb = stProbs.get(i);
			str += (i+1) + ":\t" + stProb.toString() + sep;
		}
		return str;
	}
}
