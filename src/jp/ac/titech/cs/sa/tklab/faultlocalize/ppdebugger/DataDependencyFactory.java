package jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger;

import java.util.Hashtable;
import java.util.Map;

import jp.ac.titech.cs.sa.tklab.faultlocalize.StatementData;
import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.creator.scope.TreeNode;
import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.model.DataDependency;

public class DataDependencyFactory {
	private static final int DATA_NUM = 10000;
	
	private static DataDependencyFactory instance = null;
	private Map<DataDependency,DataDependency> pool;
	
	
	private DataDependencyFactory() {
		pool = new Hashtable<DataDependency,DataDependency>(DATA_NUM *4/3);
	}
	
	public static DataDependencyFactory getInstance(){
		if(instance == null){
			instance = new DataDependencyFactory();
		}
		return instance;
	}
	
	public DataDependency genDataDependency(String varName,TreeNode scope,StatementData sd){
		DataDependency newdd = new DataDependency(varName,scope,sd);
		DataDependency ret = pool.get(newdd);
		if(ret != null) return ret;
		synchronized (pool) {
			ret = pool.get(newdd);
			if(ret != null) return ret;
			pool.put(newdd, newdd);
			return newdd;
		}
		
	}
}
