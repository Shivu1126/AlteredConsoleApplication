package emailConsoleApplication;

import java.util.ArrayList;

public class PresentOrNotEmail
{
	protected int presentOrNotMail(String mailName,ArrayList<UserDetail> mailAccounts) 
	{

		for(int i=0;i<mailAccounts.size();i++)
		{
			if(mailName.equals(mailAccounts.get(i).emailID))
			{
				return i;
			}
		}
		return -1;		
	}
}
