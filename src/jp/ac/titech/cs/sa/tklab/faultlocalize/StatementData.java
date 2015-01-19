package jp.ac.titech.cs.sa.tklab.faultlocalize;


import jp.ac.nagoya_u.is.i.agusa.person.knhr.bxmodel.Thread;

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
	
	
	public StatementData(String sourcePath,String lineNumber,Thread thread){
		this.sourcePath = sourcePath.intern();
		this.lineNumber = Integer.valueOf(lineNumber);
		this.thread = thread;
	}
	StatementData(String sourcePath,String lineNumber){
		this.sourcePath = sourcePath.intern();
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
		if(sourcePath.equals(sd.sourcePath) && (lineNumber==sd.lineNumber)		//同一ファイルの同一行
			&& isSameThread(sd.thread)){
				return true;
		}
		return false;
	}
	
	private boolean isSameThread(Thread compare){
		if(thread == null && compare == null){				//どちらもスレッドを持たない
			return true;
		}else if(thread == null || compare == null){		//一方のみスレッドを持つ
			return false;
		}
		
		boolean sameId=false,sameName=false;
		if(thread.getThreadId() == null && compare.getThreadId() == null){
			sameId = true;
		}else if(thread.getThreadId() != null && compare.getThreadId() != null
				&& thread.getThreadId().equals(compare.getThreadId())){
			sameId = true;
		}
		if(thread.getThreadName() == null && compare.getThreadName() == null){
			sameName = true;
		}else if(thread.getThreadName() != null && compare.getThreadName() != null 
				&& thread.getThreadName().equals(compare.getThreadName())){
			sameName = true;
		}
		if(sameId && sameName) return true;
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
