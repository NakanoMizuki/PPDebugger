package jp.ac.titech.cs.sa.tklab.faultlocalize.tarantula;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import jp.ac.titech.cs.sa.tklab.faultlocalize.StatementData;
import jp.ac.titech.cs.sa.tklab.faultlocalize.Valuation;

/**
 * 
 * @author nakano
 *
 */
class TStatementHolder{
	private List<TStatement> list;
	
	public TStatementHolder() {
		list = new ArrayList<TStatement>();
	}
	
	void init(){
		list.clear();
	}

	List<TStatement> getList(){
		return list;
	}

	void addPassedStatement(TStatement ts){
		if(list.contains(ts)){
			list.get(list.indexOf(ts)).incPassedCount();
		}else{
			ts.incPassedCount();
			list.add(ts);
		}
	}

	void addFailedStatement(TStatement ts){
		if(list.contains(ts)){
			list.get(list.indexOf(ts)).incFailedCount();
		}else{
			ts.incFailedCount();
			list.add(ts);
		}
	}
	
	void sort(){
		Collections.sort(list,new TStatementComparator());
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
		double sus = -1;
		int numSameProb = 0;
		boolean rankFlag = false;
		for(TStatement tst : list){
			if(tst.getSuspicious() == sus){
				numSameProb++;
			}else{
				if(rankFlag == true){
					break;
				}
				sus = tst.getSuspicious();
				rank += numSameProb;
				numSameProb = 1;
			}
			if(tst.getStatementData().isSame(fault)){
				rankFlag = true;
			}
		}
		if(rankFlag == true){
			cost = rank + numSameProb -1;
			return new Valuation(rank,cost);
		}
		return null;
	}
}
