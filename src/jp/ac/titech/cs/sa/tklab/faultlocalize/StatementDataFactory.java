package jp.ac.titech.cs.sa.tklab.faultlocalize;

import java.util.Comparator;
import java.util.TreeSet;

import jp.ac.nagoya_u.is.i.agusa.person.knhr.bxmodel.Thread;
import jp.ac.titech.cs.sa.tklab.faultlocalize.bxmodelutil.EventSignature;


public class StatementDataFactory {
	private static StatementDataFactory instance = null;
	private TreeSet<StatementData> sdSet;
	
	
	private StatementDataFactory(){
		sdSet = new TreeSet<StatementData>(new Comparator<StatementData>() {
			@Override
			public int compare(StatementData sd1,StatementData sd2){	//hashCodeの昇順
				if(sd1.hashCode() == sd2.hashCode()) return 0;
				if(sd1.hashCode() < sd2.hashCode()) return -1;
				return 1;
			}
		});
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
		synchronized (sdSet) {
			StatementData tmp = sdSet.floor(newSD);			//hashCodeが最も近いものを取り出す
			if(tmp != null && tmp.hashCode() == newSD.hashCode() && tmp.equals(newSD)){
				return tmp;
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
