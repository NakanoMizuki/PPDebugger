package jp.ac.titech.cs.sa.tklab.faultlocalize.tarantula;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import jp.ac.titech.cs.sa.tklab.faultlocalize.StatementData;

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
	
	int calcScore(StatementData fault){
		int score=0;
		for(TStatement tst :list){
			score ++;
			if(tst.getStatementData().isSame(fault)){
				return score;
			}
		}
		return -1;
	}
	
	int calcScore(List<StatementData> faults){
		int score=0;
		for(StatementData sd:faults){
			int tmp =  calcScore(sd);
			if(tmp == -1) return -1;
			score = Math.max(score,tmp);
		}
		return score;
	}
}
