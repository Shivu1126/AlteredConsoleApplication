package cricketScorecard;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class StartPlaying 
{
	static Scanner scanner=new Scanner(System.in);
	static Random random = new Random();
	static int totalFirstBatting=0;
	static final int OVER=5;
	static ArrayList<PlayerScores> scoreDetailsByplayerBatFirst = new ArrayList<>();
	static ArrayList<PlayerScores> scoreDetailsByplayerBat2nd = new ArrayList<>();

	static ArrayList<BowlerPerformence> bowlingPlayers = new ArrayList<>();
	static int tempBow=-1;
	static String bowlName="";

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
		
		totalFirstBatting = bat.batting(batFirst, scoreDetailsByplayerBatFirst,1);
		System.out.println("-----Bowling Status-----");
		DisplayBowlingStatus displayBowl = new DisplayBowlingStatus();
		displayBowl.displayBowlingStatus(bowlingPlayers);
		System.out.println("------------------------");
		
		System.out.println();
		System.out.println("Target is.."+(totalFirstBatting+1));
		System.out.println();
		
		bowlingPlayers.clear();
		
		int totalSecondBatting=bat.batting(bat2nd, scoreDetailsByplayerBat2nd,2);
		
		System.out.println("-------------------Bowling Status------------------");
		
		displayBowl.displayBowlingStatus(bowlingPlayers);
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
	
	protected  boolean checkWinOrLose(int teamScore)
	{
		return teamScore>totalFirstBatting;
	}
}
