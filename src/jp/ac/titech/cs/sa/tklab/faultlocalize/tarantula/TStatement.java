package jp.ac.titech.cs.sa.tklab.faultlocalize.tarantula;

import jp.ac.titech.cs.sa.tklab.faultlocalize.StatementData;


/**
 * 
 * @author nakano
 *
 */
class TStatement{
	private final StatementData statementData;
	private int passedCount;
	private int failedCount;
	private double suspicious;


	TStatement(StatementData sd){
		this.statementData = sd;
		passedCount = 0;
		failedCount = 0;
		suspicious = 0;
	}


	public StatementData getStatementData(){
		return statementData;
	}
	public int getLineNumber(){
		return statementData.getLineNumber();
	}
	public String getSourcePath(){
		return statementData.getSourcePath();
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
	
	void calcOchiai(int numPassed,int numFailed){
		suspicious = (double) failedCount / Math.sqrt(numFailed * (failedCount+passedCount));
	}
	
<<<<<<< HEAD
=======
	void calcER1a(int numPassed,int numFailed){
		if(numFailed == failedCount){
			suspicious = numPassed - passedCount;
		}else{
			suspicious = -1;
		}
	}
	
>>>>>>> multiFailed
	void calcER5b(int numPassed,int numFailed){
		suspicious = (double)failedCount / (double) (numFailed + numPassed);
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