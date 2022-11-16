package controller;

import java.util.ArrayList;

import model.MailDatabase;
import model.UserDetail;

public class CheckPasswordAndEmail {
	
	public boolean checkPassword(String passwordLogin,ArrayList<UserDetail> mailAccounts,String mailName)
	{
		for(int i=0;i<mailAccounts.size();i++)
		{
			if(mailName.equals(mailAccounts.get(i).getEmailID()) && passwordLogin.equals(mailAccounts.get(i).getPassword()))
			{
				return true;
			}
		}
		return false;
	}
	
	public int receiverMailIdCheck(String mailName) 
	{

		for(int i=0;i<MailDatabase.getInstance().getUserDetail().size();i++)
		{
			if(mailName.equals(MailDatabase.getInstance().getUserDetail().get(i).getEmailID()))
			{
				return i;
			}
		}
		return -1;		
	}
}
