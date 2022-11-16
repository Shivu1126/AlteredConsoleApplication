package controller;

import java.util.List;

import model.*;
import view.*;

public class BowlerPerformances extends StartPlaying
{
	protected  BowlerDetails chooseBowler(List<PlayerScores> bowlingTeamDetails)
	{
		System.out.println("-------Bowler Names-------");
		for(int j=0;j<11;j++)
		{
			if(bowlName!=bowlingTeamDetails.get(j).getPlayerName())
				System.out.println((j+1)+". "+bowlingTeamDetails.get(j).getPlayerName());
		}
		System.out.println("--------------------------");
		int bowlId=0;
		while(true)
		{
			System.out.println("Choose the bowler...");
			int bowler = scanner.nextInt();
			if(bowler<12 && tempBow!=bowler)
			{
				System.out.println("Bowler Name : "+bowlingTeamDetails.get(bowler-1).getPlayerName());
				bowlName=bowlingTeamDetails.get(bowler-1).getPlayerName();
				
				int checkNewBowlerOrNot=0;
				for(int i=0;i<CricketScoreCardDatabase.getInstance().getBowlingPlayers().size();i++)
				{
					if(bowlName.equals(CricketScoreCardDatabase.getInstance().getBowlingPlayers().get(i).getBowlerName()))
					{
						checkNewBowlerOrNot=1;
						bowlId=i;
					}
				}
				if(checkNewBowlerOrNot==0)
				{
					BowlerDetails insertBowler = new BowlerDetails();
					insertBowler.setBowlerName(bowlName);
					
					CricketScoreCardDatabase.getInstance().insertBowlingPlayers(insertBowler);
					bowlId=CricketScoreCardDatabase.getInstance().getBowlingPlayers().size()-1;
				}
				tempBow=bowler;	
				break;
			}
			else
			{
				System.out.println("Enter proper input...");
			}			
		}
		return CricketScoreCardDatabase.getInstance().getBowlingPlayers().get(bowlId);
	}
	
}
