package jp.ac.titech.cs.sa.tklab.faultlocalize;

import java.util.HashSet;
import java.util.Set;

import jp.ac.nagoya_u.is.i.agusa.person.knhr.bxmodel.Thread;
import jp.ac.titech.cs.sa.tklab.faultlocalize.bxmodelutil.EventSignature;


public class StatementDataFactory {
	private static final int DATA_NUM = 1000;			//Setの初期サイズに影響。最終的に格納されるデータサイズ以上の値を指定しておくとhashの再計算が行われずに済む
	
	private static StatementDataFactory instance = null;
	private Set<StatementData> sdSet;
	
	
	private StatementDataFactory(){
		sdSet = new HashSet<StatementData>(DATA_NUM * 4/3);
	}
	
	public static StatementDataFactory getInstance(){
		if(instance == null){
			instance = new StatementDataFactory();
		}
		return instance;
	}
	
	public int getSize(){
		return sdSet.size();
	}
	
	public StatementData genStatementData(String sourcePath,int lineNumber,Thread thread){
		StatementData newSD = new StatementData(sourcePath,Integer.toString(lineNumber),thread);
		int hash = newSD.hashCode();
		synchronized (sdSet) {
			for(StatementData sd : sdSet){
				if(hash == sd.hashCode() && sd.equals(newSD)){
					return sd;
				}
			}
			sdSet.add(newSD);
		}
		return newSD;
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
