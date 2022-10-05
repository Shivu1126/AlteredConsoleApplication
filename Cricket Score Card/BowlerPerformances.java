package cricketScorecard;

import java.util.List;

public class BowlerPerformances extends StartPlaying
{
	protected  BowlerPerformence chooseBowler(List<PlayerScores> bowlingTeamDetails)
	{
		System.out.println("-------Bowler Names-------");
		for(int j=0;j<11;j++)
		{
			if(bowlName!=bowlingTeamDetails.get(j).playerName)
				System.out.println((j+1)+". "+bowlingTeamDetails.get(j).playerName);
		}
		System.out.println("--------------------------");
		int bowlId=0;
		while(true)
		{
			System.out.println("Choose the bowler...");
			int bowler = scanner.nextInt();
			if(bowler<12 && tempBow!=bowler)
			{
				System.out.println("Bowler Name : "+bowlingTeamDetails.get(bowler-1).playerName);
				bowlName=bowlingTeamDetails.get(bowler-1).playerName;
				
				int temp=0;
				for(int i=0;i<bowlingPlayers.size();i++)
				{
					if(bowlName.equals(bowlingPlayers.get(i).bowlerName))
					{
						temp=1;
						bowlId=i;
					}
				}
				if(temp==0)
				{
					bowlingPlayers.add(new BowlerPerformence(bowlName, 0, 0, 0, 0));
					bowlId=bowlingPlayers.size()-1;
				}
				tempBow=bowler;	
				break;
			}
			else
			{
				System.out.println("Enter proper input...");
			}			
		}
		return bowlingPlayers.get(bowlId);
	}
	
}
