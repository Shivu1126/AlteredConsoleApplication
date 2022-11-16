package model;

public class PlayerScores
{
	private String playerName;
	private int runs;
	private int balls;
	
	public String getPlayerName() {
		return playerName;
	}
	public int getRuns() {
		return runs;
	}
	public int getBalls() {
		return balls;
	}
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	public void setRuns(int runs) {
		this.runs = runs;
	}
	public void setBalls(int balls) {
		this.balls = balls;
	}
	
}