package jp.ac.titech.cs.sa.tklab.faultlocalize.bxmodelutil;

import jp.ac.nagoya_u.is.i.agusa.person.knhr.bxmodel.Thread;


/**
 * 
 * @author ?
 *
 */
// イベントからステートメントに変換するときに用いる
public class EventSignature{
	private Thread thread;
	private String lineNumber;
	private String sourcePath;
	private String eventNumber;

	EventSignature(Thread thread, String ln, String sp, String en){
		this.thread = thread;
		this.lineNumber = ln;
		this.sourcePath = sp;
		this.eventNumber = en;
	}

	EventSignature(EventSignature es){
		this(es.thread, es.lineNumber, es.sourcePath, es.eventNumber);
	}

	@Override
	public boolean equals(Object o){
		if(o == null || !(o instanceof EventSignature)){
			return false;
		}
		EventSignature es = (EventSignature)o;
		Thread th = es.getThread();

		if(!this.thread.getThreadId().equals(th.getThreadId()) ||
				!this.thread.getThreadName().equals(th.getThreadName())){
			return false;
		}
		if(!this.lineNumber.equals(es.getLineNumber())){
			return false;
		}
		if(!this.sourcePath.equals(es.getSourcePath())){
			return false;
		}
		return true;
	}

	@Override
	public int hashCode(){
		return Integer.parseInt(lineNumber);
	}

	@Override
	public String toString(){
		return new String("Thread:"+thread.getThreadName()+",LineNumber:"+lineNumber+",SourcePath:"+sourcePath);
	}

	public Thread getThread(){
		return thread;
	}

	public String getLineNumber(){
		return lineNumber;
	}

	public String getSourcePath(){
		return sourcePath;
	}

	public String getEventNumber(){
		return eventNumber;
	}

}