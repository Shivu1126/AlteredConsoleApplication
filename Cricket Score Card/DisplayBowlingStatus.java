package cricketScorecard;

import java.util.ArrayList;

public class DisplayBowlingStatus extends StartPlaying
{
	protected void displayBowlingStatus(ArrayList<BowlerPerformence> printBowlersStatus)
	{
		System.out.println("BowlerName\tOvers\tBalls\tRuns\tWickets");
		for(int b_d=0;b_d<bowlingPlayers.size();b_d++)
		{
			BowlerPerformence bow = printBowlersStatus.get(b_d);
			System.out.println(bow.bowlerName+"\t\t"+bow.overs+"\t"+bow.balls+"\t"+bow.runs+"\t"+bow.wickets);
		}
		
	}
}
