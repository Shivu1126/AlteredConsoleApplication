package emailConsoleApplication;

import java.util.ArrayList;

public class CreateNewAcc extends HomePage
{
	protected void createNewID() 
	{
		System.out.println("Enter Your name..");
		String userName = s.next();

		System.out.println("Enter password..");
		System.out.println("( Must be above six charchters.\nMust atleast one characters are UpperCase,LowerCase and Numeric )");
		String password;		
		while(true)
		{
			password = s.next();
			ValidPasswordCheck validOrNot = new ValidPasswordCheck();
			boolean validPass = validOrNot.validPassword(password);
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
			emailIdName = s.next();
			CheckEmailId emailCheck = new CheckEmailId();
			
			boolean checkEmail = emailCheck.checkEmailID(emailIdName);
			if(checkEmail)
			{
				emailIdName+="@zmail.com"; 
				break;
			}
			else
				System.err.println("This mail id already in.\nplease change email name.Enter correct email name.");
		}

		ArrayList<MessageDetail> receivedMailCreate = new ArrayList<>();
		boolean addOrNot = userDetail.add(new UserDetail(userName, password, emailIdName, receivedMailCreate, 0));
		if(addOrNot)
			System.out.println("Account created...");
		else
			System.out.println("Account not created....");
	}

}
