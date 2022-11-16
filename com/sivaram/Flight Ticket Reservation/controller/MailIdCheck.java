package controller;

public class MailIdCheck 
{
	protected boolean checkValidEmail(String mailId)
	{
		if(mailId.length()<11)
			return false;
		
		String valid = "@mail.com";
		int count=0;
		int mailIdLength = mailId.length()-1;
		for(int i=valid.length()-1;i>=0;i--)
		{
			if(valid.charAt(i)==mailId.charAt(mailIdLength))
				count++;
			mailIdLength--;
		}
		//System.out.println(count);
		if(count==valid.length())
			return true;
		else
			return false;
	}
}
