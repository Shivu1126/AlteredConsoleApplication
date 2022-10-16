package view;

import java.util.Scanner;

public class HomePage {

	public Scanner scanner=new Scanner(System.in);
	public static void main(String[] args)
	{
		HomePage start = new HomePage();
		start.startHome();
	}	
	
	public void startHome()
	{
		System.out.println("Thank you for visited...");
		int option=0;
		while(option!=3)
		{
			System.out.println("1. Login");
			System.out.println("2. Create new account");
			System.out.println("3. Exit");
			System.out.println("Enter your option...");
			option = scanner.nextInt();

			switch(option)
			{
			case 1:
				Login log = new Login();
				log.login();
				break;
			case 2:
				CreateNewAccount newAccount = new CreateNewAccount();
				newAccount.createNewID();
				break;
			case 3:
				System.out.println("Thank you for using ");
				return;
			default:
				System.out.println("Enter proper input");
				break;
			}
		}
	}

}
