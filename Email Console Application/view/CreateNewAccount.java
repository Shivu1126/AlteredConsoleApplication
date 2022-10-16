package view;

import java.util.ArrayList;

import controller.*;
import model.*;

public class CreateNewAccount extends HomePage
{
	protected void createNewID() 
	{
		System.out.println("Enter Your name..");
		String userName = scanner.next();

		System.out.println("Enter password..");
		System.out.println("( Must be above six charchters.\nMust atleast one characters are UpperCase,LowerCase and Numeric )");
		String password;		
		while(true)
		{
			password = scanner.next();
			Validations validOrNot = new Validations();
			boolean validPass = validOrNot.passwordValidation(password);
			if(validPass)
			{
				System.out.println("Paasword Accepted");
				break;
			}
			else 
				System.err.println("Password is weak.Re-enter a password...");
		}

		System.out.println("Enter email name..( we will add @zmail.com )");
		String emailIdName;
		while(true)
		{
			emailIdName = scanner.next();
			Validations emailCheck = new Validations();
			
			boolean checkEmail = emailCheck.mailIdValidation(emailIdName);
			if(checkEmail)
			{
				emailIdName+="@zmail.com"; 
				break;
			}
			else
				System.err.println("This mail id already in.\nplease change email name.Enter correct email name.");
		}
		
		UserDetail userDetail = new UserDetail();
		userDetail.setName(userName);
		userDetail.setPassword(password);
		userDetail.setEmailID(emailIdName);
		
		ArrayList<MessageDetail> sendMessageInfo = new ArrayList<>();
		ArrayList<MessageDetail> receivedMessageInfo = new ArrayList<>();

		userDetail.setSendMailList(sendMessageInfo);
		userDetail.setReceivedMailList(receivedMessageInfo);
		
		MailDatabase.getInstance().insertUserDetail(userDetail);
		System.out.println("Account created...");
	
	}

}
