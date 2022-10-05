package cricketScorecard;


public class DisplayTeamDetails extends StartPlaying
{

	protected void displayTeamDetails(String Bat1st,String Bat2nd)
	{
		System.out.println(Bat1st+"\tvs\t"+Bat2nd);

		for(int i=0;i<11;i++)
		{
			System.out.println(scoreDetailsByplayerBatFirst.get(i).playerName+"\t\t\t"+scoreDetailsByplayerBat2nd.get(i).playerName);
		}
	}
}
