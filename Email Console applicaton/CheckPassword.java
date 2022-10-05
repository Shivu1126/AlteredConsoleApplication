package emailConsoleApplication;

import java.util.ArrayList;

public class CheckPassword 
{
	protected boolean checkPassword(String passwordLogin,ArrayList<UserDetail> mailAccounts,String mailName)
	{
		for(int i=0;i<mailAccounts.size();i++)
		{
			if(mailName.equals(mailAccounts.get(i).emailID) && passwordLogin.equals(mailAccounts.get(i).password))
			{
				return true;
			}
		}
		return false;
	}
}
