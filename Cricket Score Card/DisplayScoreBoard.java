package cricketScorecard;

import java.util.List;

public class DisplayScoreBoard extends StartPlaying
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
			double strikeRate=0;
			
			if(scoreOfPlayers.get(i).runs>0 && scoreOfPlayers.get(i).balls>0)
				strikeRate=(scoreOfPlayers.get(i).runs*100)/scoreOfPlayers.get(i).balls;
			
			if(player.balls!=0 || player.playerName.charAt(player.playerName.length()-1)=='*')
			{ 
				System.out.printf(player.playerName+"\t\t"+player.runs+"\t"+player.balls+"\t %.2f\n",strikeRate);
			}
		}
		System.out.println("-------------------------------------------");
		System.out.println("Total score: "+totalScore+" Extras: "+Extras+" WICKETS: "+Wickets);
		System.out.println("-------------------------------------------");
	}
	
}
