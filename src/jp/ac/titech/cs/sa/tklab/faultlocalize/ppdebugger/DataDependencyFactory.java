package jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger;

import java.util.Hashtable;
import java.util.Map;

import jp.ac.titech.cs.sa.tklab.faultlocalize.StatementData;
import jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.model.DataDependency;

public class DataDependencyFactory {
	private static DataDependencyFactory instance = null;
	private Map<DataDependency,DataDependency> pool;
	
	
	private DataDependencyFactory() {
		pool = new Hashtable<DataDependency,DataDependency>();
	}
	
	public static DataDependencyFactory getInstance(){
		if(instance == null){
			instance = new DataDependencyFactory();
		}
		return instance;
	}
	
	public DataDependency genDataDependency(String varName,StatementData sd){
		DataDependency newdd = new DataDependency(varName, sd);
		synchronized (this) {
			DataDependency dd = pool.get(newdd);
			if(dd != null) return dd;
			pool.put(newdd, newdd);
			return newdd;
		}
		
	}
}
