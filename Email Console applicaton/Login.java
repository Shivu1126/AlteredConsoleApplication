package emailConsoleApplication;

import java.util.ArrayList;

public class Login extends HomePage
{
	protected void login(ArrayList<UserDetail> mailDetails)
	{
		UserDetail activeMail;

		System.out.println("Enter mail id");
		String mailName;

		while(true)
		{
			mailName = s.next();
			PresentOrNotEmail inOrNot = new PresentOrNotEmail();
			
			int presentMail = inOrNot.presentOrNotMail(mailName,mailDetails);
			if(presentMail!=-1)
			{
				activeMail = mailDetails.get(presentMail);
				break;
			}
			else
				System.err.println("Please enter proper mail id..");

			System.out.println("If you want create account.\nplease press 1..it will going to home page...");
			int i=s.nextInt();
			if(i==1)
			{
				return;
			}
		}

		System.out.println("Enter password..");
		String passwordLogin;
		while(true)
		{
			passwordLogin = s.next();
			CheckPassword passwordCheck = new CheckPassword();
			
			boolean checkPass = passwordCheck.checkPassword(passwordLogin,mailDetails,mailName);
			if(checkPass)
				break;
			else
				System.out.println("Password incorrect..");

			System.out.println("If you want go to home page.\n please press 1..");
			int i=s.nextInt();
			if(i==1)
			{
				return;
			}
		}

		System.out.println("Login successfull.");
		AccountPage accPage = new AccountPage();
		accPage.accountPage(activeMail, mailDetails);

	}
}
