package jp.ac.titech.cs.sa.tklab.faultlocalize.main;

public class Score {
	private final int max;
	private final int min;
	private final double avg;
	
	Score(int max,int min,double avg){
		this.max=max;
		this.min=min;
		this.avg=avg;
	}
	
	Score(int max,int min){
		this.max = max;
		this.min = min;
		this.avg = -1;
	}

	public int getMax() {
		return max;
	}
	public int getMin() {
		return min;
	}
	public double getAvg() {
		return avg;
	}
	
	@Override
	public String toString(){
		if(avg == -1D) return "avg=? max=" + max + " min=" + min;
		return "avg=" + avg + " max=" + max + " min=" + min;
	}
}
