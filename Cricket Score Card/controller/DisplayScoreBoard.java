package controller;

import java.util.List;

import model.PlayerScores;

public class DisplayScoreBoard 
{

	protected void displayScoreBoard(List<PlayerScores> scoreOfPlayers,String teamName,int totalScore,int Wickets,int Extras)
	{
		System.out.println("-------------------------------------------");
		System.out.println("Team Name : "+teamName);
		System.out.println("-------------------------------------------");
		System.out.println("playerName\t Runs \t Balls \t strikeRate");
		System.out.println("-------------------------------------------");
		for(int i=0;i<11;i++)
		{
			PlayerScores player = scoreOfPlayers.get(i);
			float strikeRate=0;
			
			if(scoreOfPlayers.get(i).getRuns()>0 && scoreOfPlayers.get(i).getBalls()>0)
				strikeRate=(scoreOfPlayers.get(i).getRuns()*100)/scoreOfPlayers.get(i).getBalls();
			
			if(player.getBalls()!=0 || player.getPlayerName().charAt(player.getPlayerName().length()-1)=='*')
			{ 
				System.out.printf(player.getPlayerName()+"\t\t"+player.getRuns()+"\t"+player.getBalls()+"\t %.2f\n",strikeRate);
			}
		}
		System.out.println("-------------------------------------------");
		System.out.println("Total score: "+totalScore+" Extras: "+Extras+" WICKETS: "+Wickets);
		System.out.println("-------------------------------------------");
	}
	
}
