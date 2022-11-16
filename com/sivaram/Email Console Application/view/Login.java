package view;

import java.util.Scanner;

import controller.*;
import model.*;

public class Login //extends HomePage
{
	private Scanner scanner = new Scanner(System.in);
	
	protected void login()
	{
		UserDetail activeMail;

		System.out.println("Enter mail id");
		String mailName;

		while(true)
		{
			mailName = scanner.next();
			CheckPasswordAndEmail inOrNot = new CheckPasswordAndEmail();
			
			int presentMail = inOrNot.receiverMailIdCheck(mailName);
			if(presentMail!=-1)
			{
				activeMail = MailDatabase.getInstance().getUserDetail().get(presentMail);
				break;
			}
			else
			{
				System.err.println("Please enter proper mail id..");
			}

			System.out.println("If you want create account.\nplease press 1..it will going to home page...");
			int i=scanner.nextInt();
			if(i==1)
			{
				return;
			}
			System.out.println("Re-enter mail id");
		}

		System.out.println("Enter password..");
		while(true)
		{
			String password;
			password = scanner.next();
			CheckPasswordAndEmail passwordCheck = new CheckPasswordAndEmail();
			
			boolean checkPassword = passwordCheck.checkPassword(password,MailDatabase.getInstance().getUserDetail(),mailName);
			if(checkPassword)
				break;
			else
				System.out.println("Password incorrect..");

			System.out.println("If you want go to home page.\n please press 1..");
			int stopOrNot=scanner.nextInt();
			if(stopOrNot==1)
			{
				return;
			}
			System.out.println("Re-enter password");
		}

		System.out.println("Login successfull.");
		UserView use = new UserView();
		use.userView(activeMail);
		
	}
}
