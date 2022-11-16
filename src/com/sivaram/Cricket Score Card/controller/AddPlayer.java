package controller;

import java.util.Scanner;

import model.CricketScoreCardDatabase;
import model.PlayerScores;

public class AddPlayer 
{
	private Scanner scanner = new Scanner(System.in);
	public void addPlayersName(String teamName,int who)
	{
		System.out.println("Enter "+teamName+" Team XI....");
		for(int i=0;i<11;i++)
		{
			System.out.print((i+1)+". ");
			String name = scanner.next();
			
			if(who==1)
			{
				PlayerScores playerDetailBat1 = setPlayerDetail(name);
				CricketScoreCardDatabase.getInstance().insertPlayerBatFirst(playerDetailBat1);
			}
			else
			{
				PlayerScores playerDetailBat2 = setPlayerDetail(name);
				CricketScoreCardDatabase.getInstance().insertPlayerBat2nd(playerDetailBat2);
			}
		}
	}
	private PlayerScores setPlayerDetail(String name)
	{
		PlayerScores playerDetail = new PlayerScores();
		playerDetail.setPlayerName(name);
		playerDetail.setBalls(0);
		playerDetail.setRuns(0);
		return playerDetail;
	}
}
