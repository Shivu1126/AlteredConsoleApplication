package controller;

import java.util.ArrayList;
import java.util.Random;

import model.*;
import view.*;

public class Batting 
{
	public int batting(String battingTeamName,ArrayList<PlayerScores> battingTeam,int who,ArrayList<PlayerScores> bowlingTeam)
	{
		int nextPlayer=1;
		int wickets=0;
		int teamScore=0;
		final int OVER=5;
		
		System.out.println("*********************************");
		System.out.println(battingTeamName+" Start the batting...");
		System.out.println("*********************************");
		System.out.println(battingTeam.get(0).getPlayerName()+" is Striker");
		System.out.println(battingTeam.get(nextPlayer).getPlayerName()+" is Non Striker");
		System.out.println("..................................");
		
		BowlerDetails bowlerDetail;
		
		BowlerPerformances choosingBowler = new BowlerPerformances();
		
		bowlerDetail = choosingBowler.chooseBowler(bowlingTeam);		
		
		int totalBalls=0;
		int extras=0;

		PlayerScores striker=battingTeam.get(0);
		PlayerScores Nonstriker=battingTeam.get(nextPlayer);
		
		String thisOver = "";
		
		while(OVER*6>totalBalls && wickets<10)
		{
			StartPlaying checkScore = new StartPlaying();				
			int runsDetail[]= {-2,-1,0,1,2,3,4,6};
			Random random = new Random();
			int index = random.nextInt(runsDetail.length);
			int run=runsDetail[index];
			if(run==-1)
			{
				System.out.println(striker.getPlayerName()+" is out...");
				thisOver+="out ";
				striker.setBalls(striker.getBalls() + 1);
				wickets++;
				nextPlayer++;
				striker=battingTeam.get(nextPlayer);
				System.out.println("New Striker is "+striker.getPlayerName());
				
				bowlerDetail.setBalls(bowlerDetail.getBalls() + 1);
				bowlerDetail.setWickets(bowlerDetail.getWickets() + 1);
				bowlerDetail.setOvers((float)bowlerDetail.getBalls()/6); 
			}
			else if(run==-2)
			{
				System.out.println("Wide ball(EXTRA)");
				thisOver+="wd ";
				teamScore+=1;
				extras++;
				
				bowlerDetail.setRuns(bowlerDetail.getRuns() + 1);
				if(who==2 && checkScore.checkWinOrLose(teamScore))
				{
					System.out.println(battingTeamName+" is Winning the match...before "+((OVER*6)-totalBalls)+" Balls");
					break;
				}
			}
			else
			{
				System.out.println(striker.getPlayerName()+" take "+run+" run ");
				thisOver+=run+" ";
				teamScore+=run;
				if(run%2==1)
				{
					striker.setRuns(striker.getRuns() + run);
					striker.setBalls(striker.getBalls() + 1);
					PlayerScores newStriker=striker;
					striker=Nonstriker;
					Nonstriker=newStriker;				
				}
				else
				{
					striker.setBalls(striker.getBalls() + 1);
					striker.setRuns(striker.getRuns() + run);
				}
				
				bowlerDetail.setBalls(bowlerDetail.getBalls() + 1);
				bowlerDetail.setRuns(bowlerDetail.getRuns() + run);
				bowlerDetail.setOvers(bowlerDetail.getBalls()/6);
				
				if(who==2 && checkScore.checkWinOrLose(teamScore))
				{
					System.out.println(battingTeamName+" is Winning the match...before "+((OVER*6)-totalBalls)+" Balls");					
					//displayScoreBoard(teamPlayers, battingTeam, teamScore, wickets, extras);
					break;
				}
			}
			
			if(run!=-2)
				totalBalls++;
			
			if(totalBalls%6==0 && run!=-2)
			{

				PlayerScores changeStriker=striker;
				striker=Nonstriker;
				Nonstriker=changeStriker;
				System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~");
				System.out.println("This Over("+(totalBalls/6)+") : "+thisOver);
				System.out.println("__- "+(totalBalls/6)+" OVER COMPLETE -__");
				System.out.println("Score :"+teamScore+" Wickets :"+wickets);
				System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~");
				thisOver="";
				if(totalBalls/6 != OVER)
					bowlerDetail = choosingBowler.chooseBowler(bowlingTeam);
				System.out.println("*******************************");
			}
			
		}
		
		striker.setPlayerName(striker.getPlayerName()+"*");
		Nonstriker.setPlayerName(Nonstriker.getPlayerName()+"*");
		if(who==1)
			System.out.println("First Innings Over...");
		else
			System.out.println("Second Innings Over...");
		DisplayScoreBoard displayScoreDet = new DisplayScoreBoard();
		displayScoreDet.displayScoreBoard(battingTeam, battingTeamName, teamScore, wickets, extras);
		//tempBow=-1;
		return teamScore;
	}
	
}
