package view;

import java.util.Scanner;

import model.UserDetail;
import controller.*;
public class UserView {
	private Scanner scanner = new Scanner(System.in);
	public void userView(UserDetail activeAccount)
	{
		System.out.println("Welcome "+activeAccount.getName());
		System.out.println("Your Options..");
		while(true)
		{
			System.out.println("1. Compose mail");
			System.out.println("2. Recieved mails");
			System.out.println("3. Send mails");
			System.out.println("4. Delete recieved mails");
			System.out.println("5. Delete send mails");
			System.out.println("6. Logout");
			System.out.println("Enter options..");
			int pageOption = scanner.nextInt();
			UserController uses = new UserController();

			switch(pageOption)
			{
			case 1:
				uses.composeMail(activeAccount);
				break;
			case 2:
				uses.receivedMailDisplayAndDelete(activeAccount.getEmailID(), false);
				break;
			case 3:
				uses.sentedMailDispalyAndDelete(activeAccount.getEmailID(),false);
				break;
			case 4:
				uses.receivedMailDisplayAndDelete(activeAccount.getEmailID(), true);
				break;
			case 5:
				uses.sentedMailDispalyAndDelete(activeAccount.getEmailID(),true);
				break;
			case 6:
				System.out.println("Thank you "+activeAccount.getName());
				return;
			default:
				System.out.println("Enter proper input..");
				break;
			}
		}
	}
}
