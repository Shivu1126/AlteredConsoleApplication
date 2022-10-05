package cricketScorecard;

import java.util.Scanner;

public class AddPlayer extends StartPlaying
{
	Scanner scanner = new Scanner(System.in);
	protected void addPlayersName(String teamName,int who)
	{
		System.out.println("Enter "+teamName+" Team XI....");
		for(int i=0;i<11;i++)
		{
			System.out.print((i+1)+". ");
			String name = scanner.next();
			
			if(who==1)
				scoreDetailsByplayerBatFirst.add(new PlayerScores(name, 0, 0));
			else
				scoreDetailsByplayerBat2nd.add(new PlayerScores(name, 0, 0));
		}
	}
}
