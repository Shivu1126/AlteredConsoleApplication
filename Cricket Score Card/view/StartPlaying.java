package view;

import java.util.Random;
import java.util.Scanner;

import model.*;
import controller.*;

public class StartPlaying 
{
	public Scanner scanner=new Scanner(System.in);
	protected static String bowlName="";
	protected static int tempBow=-1;
	static int totalFirstBatting=0;
	protected void startPlaying()
	{
		System.out.println("Enter team1 name ");
		String team1=scanner.next();
		System.out.println("Enter team2 name ");
		String team2=scanner.next();
		System.out.println("Lets start the match...");
		System.out.println("-----"+team1+" vs "+team2+"-----");
		System.out.println(team1+" captain toss the coin..");
		System.out.println(team2+" captain...1.head or 2.tail..");
		int tossDecide = scanner.nextInt();
		String toss= tossDecide==1?"head":"tail";
		String tossWin[]= {"head","tail"};
		Random random = new Random();
		int index = random.nextInt(tossWin.length);
		System.out.println("Toss fell :- "+tossWin[index]);
		String tossWinningTeam="";
		String tossLoseTeam="";

		if(tossWin[index].equals(toss))
		{
			System.out.println(team2+" captain win the toss..");
			tossWinningTeam=team2;
			tossLoseTeam=team1;
		}
		else
		{
			System.out.println(team1+" captain win the toss..");
			tossWinningTeam=team1;
			tossLoseTeam=team2;

		}
		System.out.println("Choose 1.bat first or 2.bowl first");
		int chooseBatFirst=scanner.nextInt();
		String batFirst="";
		String bat2nd="";
		if(chooseBatFirst==1)
		{
			System.out.println(tossWinningTeam+" goes to bat first..");
			System.out.println(tossLoseTeam+" goes to bowl first...");
			batFirst=tossWinningTeam;
			bat2nd=tossLoseTeam;
		}
		else
		{
			System.out.println(tossWinningTeam+" goes to bowl first..");
			System.out.println(tossLoseTeam+" goes to bat first...");
			batFirst=tossLoseTeam;
			bat2nd=tossWinningTeam;
		}
		AddPlayer addPlayer = new AddPlayer();
	
		addPlayer.addPlayersName(batFirst,1);
		addPlayer.addPlayersName(bat2nd,2);
		
		DisplayTeamDetails  displayTeam = new DisplayTeamDetails();
		displayTeam.displayTeamDetails(batFirst, bat2nd);

		Batting bat = new Batting();
		
		totalFirstBatting = bat.batting(batFirst, CricketScoreCardDatabase.getInstance().getScoreDetailsByplayerBatFirst(),1,
													CricketScoreCardDatabase.getInstance().getScoreDetailsByplayerBat2nd());
		System.out.println("-----Bowling Status-----");
		DisplayBowlingStatus displayBowl = new DisplayBowlingStatus();
		displayBowl.displayBowlingStatus(CricketScoreCardDatabase.getInstance().getBowlingPlayers());
		System.out.println("------------------------");
		
		System.out.println();
		System.out.println("Target is.."+(totalFirstBatting+1));
		System.out.println();
		
		CricketScoreCardDatabase.getInstance().getBowlingPlayers().clear();
		
		int totalSecondBatting=bat.batting(bat2nd, CricketScoreCardDatabase.getInstance().getScoreDetailsByplayerBat2nd(),2,CricketScoreCardDatabase.getInstance().getScoreDetailsByplayerBatFirst());
		
		System.out.println("-------------------Bowling Status------------------");
		
		displayBowl.displayBowlingStatus(CricketScoreCardDatabase.getInstance().getBowlingPlayers());
		System.out.println("---------------------------------------------------");
		
		
		if(totalFirstBatting>totalSecondBatting)
		{
			System.out.println(batFirst+" team win the match by "+(totalFirstBatting-totalSecondBatting)+" runs.");
		}
		if(totalSecondBatting==totalFirstBatting)
		{
			System.out.println("...MATCH IS DRAW...");
		}

	}
	
	public  boolean checkWinOrLose(int teamScore)
	{
		return teamScore>totalFirstBatting;
	}
}
