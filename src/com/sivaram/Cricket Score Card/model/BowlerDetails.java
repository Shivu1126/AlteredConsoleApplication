package model;

public class BowlerDetails
{
	private String bowlerName;
	private double overs;
	private int balls;
	private int wickets;
	private int runs;
	
	public String getBowlerName() {
		return bowlerName;
	}
	public double getOvers() {
		return overs;
	}
	public int getBalls() {
		return balls;
	}
	public int getWickets() {
		return wickets;
	}
	public int getRuns() {
		return runs;
	}
	public void setBowlerName(String bowlerName) {
		this.bowlerName = bowlerName;
	}
	public void setOvers(double overs) {
		this.overs = overs;
	}
	public void setBalls(int balls) {
		this.balls = balls;
	}
	public void setWickets(int wickets) {
		this.wickets = wickets;
	}
	public void setRuns(int runs) {
		this.runs = runs;
	}
	
}