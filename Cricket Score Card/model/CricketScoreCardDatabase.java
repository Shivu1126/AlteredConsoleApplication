package model;

import java.util.ArrayList;


public class CricketScoreCardDatabase 
{
	private static CricketScoreCardDatabase cricketScoreDb;
	private ArrayList<PlayerScores> scoreDetailsByplayerBatFirst;
	private ArrayList<PlayerScores> scoreDetailsByplayerBat2nd;
	private ArrayList<BowlerDetails> bowlingPlayers;
	CricketScoreCardDatabase() {
		scoreDetailsByplayerBatFirst = new ArrayList<>();
		scoreDetailsByplayerBat2nd = new ArrayList<>();
		bowlingPlayers = new ArrayList<>();
	}
	
	public static CricketScoreCardDatabase getInstance() 
	{
		if(cricketScoreDb==null)
		{
			cricketScoreDb = new CricketScoreCardDatabase(); 
		}
		return cricketScoreDb;
	}
	
	public ArrayList<PlayerScores> getScoreDetailsByplayerBatFirst() {
		return scoreDetailsByplayerBatFirst;
	}
	public void insertPlayerBatFirst(PlayerScores insertPlayersBatFirst) {
		this.scoreDetailsByplayerBatFirst.add(insertPlayersBatFirst);
	}

	public ArrayList<PlayerScores> getScoreDetailsByplayerBat2nd() {
		return scoreDetailsByplayerBat2nd;
	}
	public void insertPlayerBat2nd(PlayerScores insertplayerBat2nd) {
		this.scoreDetailsByplayerBat2nd.add(insertplayerBat2nd);
	}

	public ArrayList<BowlerDetails> getBowlingPlayers() {
		return bowlingPlayers;
	}
	public void insertBowlingPlayers(BowlerDetails insertBowlingPlayers) {
		this.bowlingPlayers.add(insertBowlingPlayers);
	}
	
	
}
