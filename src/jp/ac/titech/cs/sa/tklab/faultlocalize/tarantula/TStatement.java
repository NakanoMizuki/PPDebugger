package jp.ac.titech.cs.sa.tklab.faultlocalize.tarantula;

import jp.ac.titech.cs.sa.tklab.faultlocalize.StatementData;
import jp.ac.titech.cs.sa.tklab.faultlocalize.bxmodelutil.EventSignature;


/**
 * 
 * @author nakano
 *
 */
class TStatement{
	private StatementData statementData;
	private int passedCount;
	private int failedCount;
	private double suspicious;


	TStatement(StatementData sd){
		this.statementData = sd;
		passedCount = 0;
		failedCount = 0;
		suspicious = 0;
	}
	TStatement(EventSignature es){
		this(new StatementData(es));
	}


	public StatementData getStatementData(){
		return statementData;
	}
	public int getLineNumber(){
		return statementData.getLineNumber();
	}
	public double getSuspicious(){
		return suspicious;
	}

	void incPassedCount(){
		++ passedCount;
	}
	void incFailedCount(){
		++ failedCount;
	}

	void calcSuspicious(int numPassed,int numFailed){
		double failedRate = (double)failedCount / (double)numFailed;
		double passedRate = (double)passedCount / (double)numPassed;
		suspicious = failedRate / (passedRate + failedRate);
	}

	
	@Override
	public boolean equals(Object o){
		if(o == null || !(o instanceof TStatement)){
			return false;
		}
		TStatement ts = (TStatement) o;
		return statementData.equals(ts.getStatementData());
	}
	@Override
	public int hashCode(){
		String temp = statementData.getSourcePath() + statementData.getLineNumber();
		return temp.hashCode();
	}
	@Override
	public String toString(){
		return statementData.getSourcePath() + "@line" + statementData.getLineNumber()
				+ "\tnPassed=" + passedCount + "  nFailed=" + failedCount + "  suspicious=" + suspicious;
	}
}