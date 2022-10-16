package view;

import java.util.ArrayList;

import model.BowlerDetails;

public class DisplayBowlingStatus 
{
	protected void displayBowlingStatus(ArrayList<BowlerDetails> printBowlersStatus)
	{
		System.out.println("BowlerName\tOvers\tBalls\tRuns\tWickets");
		for(int i=0;i<printBowlersStatus.size();i++)
		{
			BowlerDetails bow = printBowlersStatus.get(i);
			System.out.println(bow.getBowlerName()+"\t\t"+bow.getOvers()+"\t"+bow.getBalls()+"\t"+bow.getRuns()+"\t"+bow.getWickets());
		}
		
	}
}
