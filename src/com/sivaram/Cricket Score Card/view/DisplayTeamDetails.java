package view;

import model.CricketScoreCardDatabase;

public class DisplayTeamDetails 
{

	protected void displayTeamDetails(String Bat1st,String Bat2nd)
	{
		System.out.println(Bat1st+"\tvs\t"+Bat2nd);

		for(int i=0;i<11;i++)
		{
			System.out.println(CricketScoreCardDatabase.getInstance().getScoreDetailsByplayerBatFirst().get(i).getPlayerName()+
								"\t\t\t"+CricketScoreCardDatabase.getInstance().getScoreDetailsByplayerBat2nd().get(i).getPlayerName());
		}
	}
}
