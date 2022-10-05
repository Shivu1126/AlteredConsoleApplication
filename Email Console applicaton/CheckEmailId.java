package emailConsoleApplication;

public class CheckEmailId extends HomePage
{
	protected boolean checkEmailID(String emailIdName) {

		String mailId = emailIdName+"@zmail.com";
		for(int i=0;i<userDetail.size();i++)
		{
			if(mailId.equals(userDetail.get(i).emailID))
			{
				return false;
			}
		}		
		return true;
	}
}
