package jp.ac.titech.cs.sa.tklab.faultlocalize;

import java.util.Hashtable;
import java.util.Map;

import jp.ac.nagoya_u.is.i.agusa.person.knhr.bxmodel.Thread;
import jp.ac.titech.cs.sa.tklab.faultlocalize.bxmodelutil.EventSignature;


public class StatementDataFactory {
	private static final int DATA_NUM = 10000;
	
	private static StatementDataFactory instance = null;
	private Map<StatementData,StatementData> pool;
	
	
	private StatementDataFactory(){
		pool = new Hashtable<StatementData,StatementData>(DATA_NUM * 4/3);
	}
	
	public static StatementDataFactory getInstance(){
		if(instance == null){
			instance = new StatementDataFactory();
		}
		return instance;
	}
	
	public StatementData genStatementData(String sourcePath,int lineNumber,Thread thread){
		StatementData newSD = new StatementData(sourcePath,Integer.toString(lineNumber),thread);
		synchronized (this) {
			StatementData sd = pool.get(newSD);
			if(sd != null) return newSD;
			pool.put(newSD, newSD);
			return newSD;
		}
		
	}
	public StatementData genStatementData(String sourcePath,String lineNumber){
		return genStatementData(sourcePath,lineNumber,null);
	}
	public StatementData genStatementData(String sourcePath,String lineNumber,Thread thread){
		return genStatementData(sourcePath, Integer.valueOf(lineNumber), thread);
	}
	public StatementData genStatementData(EventSignature es){
		return genStatementData(es.getSourcePath(),es.getLineNumber(),es.getThread());
	}
}
