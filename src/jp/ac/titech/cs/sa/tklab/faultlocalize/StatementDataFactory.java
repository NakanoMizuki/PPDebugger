package jp.ac.titech.cs.sa.tklab.faultlocalize;

import java.util.HashSet;
import java.util.Set;

import jp.ac.nagoya_u.is.i.agusa.person.knhr.bxmodel.Thread;


public class StatementDataFactory {
	private Set<StatementData> sdSet;
	
	public StatementDataFactory(){
		sdSet = new HashSet<StatementData>();
	}
	
	
	public synchronized StatementData genStatementData(String sourcePath,int lineNumber,Thread thread){
		for(StatementData sd : sdSet){
			if(sd.getSourcePath().equals(sourcePath) && sd.getLineNumber() == lineNumber && sd.getThread().equals(thread)){
				return sd;
			}
		}
		StatementData newSD = new StatementData(sourcePath,Integer.toString(lineNumber),thread);
		sdSet.add(newSD);
		return newSD;
	}
	public synchronized StatementData genStatementData(String sourcePath,String lineNumber,Thread thread){
		return genStatementData(sourcePath, Integer.valueOf(lineNumber), thread);
	}
}
