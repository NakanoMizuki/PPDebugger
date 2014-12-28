package jp.ac.titech.cs.sa.tklab.faultlocalize.tarantula;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author nakano
 *
 */
class TStatementHolder{
	private List<TStatement> list = new ArrayList<TStatement>();

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
}
