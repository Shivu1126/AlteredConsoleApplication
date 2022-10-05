package cricketScorecard;

import java.util.ArrayList;

public class Batting extends StartPlaying
{
	protected int batting(String battingTeam,ArrayList<PlayerScores> teamPlayers,int who)
	{
		int i=1;
		int wickets=0;
		int teamScore=0;

		System.out.println("*********************************");
		System.out.println(battingTeam+" Start the batting...");
		System.out.println("*********************************");
		System.out.println(teamPlayers.get(0).playerName+" is Striker");
		System.out.println(teamPlayers.get(i).playerName+" is Non Striker");
		System.out.println("..................................");
		
		BowlerPerformence bowlerDetail;
		
		BowlerPerformances choosingBowler = new BowlerPerformances();
		
		
		if(who==1)		
			 bowlerDetail = choosingBowler.chooseBowler(scoreDetailsByplayerBat2nd);		
		else
			 bowlerDetail = choosingBowler.chooseBowler(scoreDetailsByplayerBatFirst);

		int totalBalls=0;
		int extras=0;

		PlayerScores striker=teamPlayers.get(0);
		PlayerScores Nonstriker=teamPlayers.get(i);
		
		String thisOver = "";

		while(OVER*6>totalBalls && wickets<10)
		{
			int runsDetail[]= {-2,-1,0,1,2,3,4,6};
			int index = random.nextInt(runsDetail.length);
			int run=runsDetail[index];
			if(run==-1)
			{
				System.out.println(striker.playerName+" is out...");
				thisOver+="out ";
				striker.balls++;
				wickets++;
				i++;
				striker=teamPlayers.get(i);
				System.out.println("New Striker is "+striker.playerName);
				
				bowlerDetail.balls++;
				bowlerDetail.wickets++;
				bowlerDetail.overs = bowlerDetail.balls/6; 
			}
			else if(run==-2)
			{
				System.out.println("Wide ball(EXTRA)");
				thisOver+="wd ";
				teamScore+=1;
				extras++;
				
				bowlerDetail.runs++;
				//totalBalls--;
				if(who==2 && checkWinOrLose(teamScore))
				{
					System.out.println(battingTeam+" is Winning the match...before "+((OVER*6)-totalBalls)+" Balls");
					//displayScoreBoard(teamPlayers, battingTeam, teamScore, wickets, extras);
					break;
				}
			}
			else
			{
				System.out.println(striker.playerName+" take "+run+" run ");
				thisOver+=run+" ";
				teamScore+=run;
				if(run%2==1)
				{
					striker.runs+=run;
					striker.balls++;
					PlayerScores newStriker=striker;
					striker=Nonstriker;
					Nonstriker=newStriker;				
				}
				else
				{
					striker.balls++;
					striker.runs+=run;
				}
				
				bowlerDetail.balls++;
				bowlerDetail.runs+=run;
				bowlerDetail.overs=bowlerDetail.balls/6;
				
				if(who==2 && checkWinOrLose(teamScore))
				{
					System.out.println(battingTeam+" is Winning the match...before "+((OVER*6)-totalBalls)+" Balls");					
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
				if(totalBalls/6 != OVER && who==1)
					bowlerDetail = choosingBowler.chooseBowler(scoreDetailsByplayerBat2nd);
				else if(totalBalls/6 != OVER && who==2)
					bowlerDetail = choosingBowler.chooseBowler(scoreDetailsByplayerBatFirst);
				
			//	System.out.println("New Bowler Name : "+ bowlerDetail.bowlerName);
				System.out.println("*******************************");
			}
			
		}
		
		striker.playerName=striker.playerName+"*";
		Nonstriker.playerName=Nonstriker.playerName+"*";
		if(who==1)
			System.out.println("First Innings Over...");
		else
			System.out.println("Second Innings Over...");
		DisplayScoreBoard displayScoreDet = new DisplayScoreBoard();
		displayScoreDet.displayScoreBoard(teamPlayers, battingTeam, teamScore, wickets, extras);
		tempBow=-1;
		return teamScore;
	}
	
}
