package jp.ac.titech.cs.sa.tklab.faultlocalize;

import jp.ac.nagoya_u.is.i.agusa.person.knhr.bxmodel.Thread;
import jp.ac.titech.cs.sa.tklab.faultlocalize.bxmodelutil.EventSignature;

/**
 * ステートメントの基本情報を保存するクラス
 * ソースコード名、行番号、Reticellaでのスレッド情報をもつ
 * @author nakano
 *
 */
public class StatementData implements Comparable<StatementData>{
	private final String sourcePath;
	private final int lineNumber;
	private final Thread thread;
	
	
	StatementData(String sourcePath,String lineNumber,Thread thread){
		this.sourcePath = sourcePath;
		this.lineNumber = Integer.valueOf(lineNumber);
		this.thread = thread;
	}
	StatementData(String sourcePath,String lineNumber){
		this.sourcePath = sourcePath;
		this.lineNumber = Integer.valueOf(lineNumber);
		thread = null;
	}

	
	public String getSourcePath(){
		return sourcePath;
	}

	public int getLineNumber(){
		return lineNumber;
	}
	
	public Thread getThread(){
		return thread;
	}

	//Threadは違ってもよい
	public boolean isSame(StatementData sd){
		if(this.sourcePath.equals(sd.sourcePath) && this.lineNumber == sd.lineNumber)
			return true;
		
		return false;
	}
	
	@Override
	public boolean equals(Object o){
		if(o == null || !(o instanceof StatementData)){
			return false;
		}
		StatementData sd = (StatementData)o;
		if(sourcePath.equals(sd.sourcePath)
				&& (lineNumber==sd.lineNumber)
				&& thread.getThreadId().equals(sd.thread.getThreadId())
				&& ((thread.getThreadName() == null && sd.thread.getThreadName() == null)		//どちらも名無しか同じ名前
					 || thread.getThreadName().equals(sd.thread.getThreadName())) ){
			return true;
		}
		return false;
	}
	@Override
	public int hashCode(){
		return sourcePath.hashCode() + lineNumber;
	}
	@Override
	public String toString(){
		return lineNumber + "@" + sourcePath;
		//return ""+lineNumber;
	}

	@Override
	public int compareTo(StatementData o) {
		if(sourcePath.equals(o.sourcePath)){
			if(lineNumber < o.lineNumber){
				return -1;
			}else{
				return 1;
			}
		}else{
			return sourcePath.compareTo(o.sourcePath);
		}
	}
}
