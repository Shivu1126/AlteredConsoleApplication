package cricketScorecard;

public class CricketScorecard {
	
	public static void main(String[] args) {
		StartPlaying start = new StartPlaying();
		start.startPlaying();
		
	}
}

class PlayerScores
{
	String playerName;
	int runs;
	int balls;
	public PlayerScores(String playerName, int runs, int balls) 
	{
		this.playerName=playerName;
		this.runs=runs;
		this.balls=balls;		
	}
}
class BowlerPerformence
{
	String bowlerName;
	double overs;
	int balls;
	int wickets;
	int runs;
	public BowlerPerformence(String bowlerName,double overs,int balls,int wickets,int runs) 
	{
		this.bowlerName=bowlerName;
		this.overs=overs;
		this.balls=balls;
		this.wickets=wickets;
		this.runs=runs;
	}
}