package jp.ac.titech.cs.sa.tklab.faultlocalize.ppdebugger.model.result;


import jp.ac.titech.cs.sa.tklab.faultlocalize.StatementData;

public class StatementProb implements Comparable<StatementProb>{
	private final StatementData statementData;
	private final double prob;
	
	public StatementProb(StatementData statementData,double prob){
		this.statementData = statementData;
		this.prob = prob;
	}
	
	public StatementData getStatementData(){
		return statementData;
	}
	public double getProb(){
		return prob;
	}
	
	@Override
	public String toString(){
		return statementData.toString() + "\t prob=" +prob;
	}

	@Override
	public int compareTo(StatementProb o) {
		if(prob < o.prob){
			return -1;
		}else if(prob > o.prob){
			return 1;
		}else{				// prob == o.prob
			if(statementData.getSourcePath().equals(o.statementData.getSourcePath())){
				if(statementData.getLineNumber() < o.statementData.getLineNumber()){
					return -1;
				}else{
					return 1;
				}
			}else{
				return statementData.getSourcePath().compareTo(o.statementData.getSourcePath());
			}
		}
	}
	
	@Override
	public int hashCode(){
		return statementData.hashCode();
	}
	
	@Override
	public boolean equals(Object o){
		if(o ==null || ! (o instanceof StatementProb)) return false;
		StatementProb compare = (StatementProb) o;
		if(prob == compare.prob && statementData.equals(compare.statementData)) return true;
		return false;
	}
}
