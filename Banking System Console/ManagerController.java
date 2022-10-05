package bankingSystem;

public class ManagerController 
{
	public boolean checkManagerInfo(String name, String password)
	{
		if(name.equals("Ram") && password.equals("PrincessNoor11"))
		{
			return true;
		}
		return false;
	}
}
