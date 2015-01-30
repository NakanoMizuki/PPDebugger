package jp.ac.titech.cs.sa.tklab.faultlocalize;

public class Valuation {
	private final int rank;
	private final int cost;
	
	public Valuation(int rank,int cost){
		this.rank = rank;
		this.cost = cost;
	}
	
	public int getRank(){
		return rank;
	}
	
	public int getCost(){
		return cost;
	}
	
	@Override
	public String toString(){
		return "rank=" + rank + " cost=" + cost;
	}

}
