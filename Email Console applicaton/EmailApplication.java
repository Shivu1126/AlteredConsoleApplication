package emailConsoleApplication;

import java.util.ArrayList;
import java.util.Scanner;

public class EmailApplication {

	
	public static void main(String[] args)
	{
		HomePage start = new HomePage();
		start.startHome();
	}	
}

class UserDetail
{
	String name;
	String password;
	String emailID;
	ArrayList<MessageDetail> mailDetails;
	int msgId;
	UserDetail(String name, String password, String emailID,ArrayList<MessageDetail> mailDetails,int msgId)
	{
		this.name = name;
		this.password = password;
		this.emailID = emailID;
		this.mailDetails = mailDetails;
		this.msgId = msgId;
	}
}

class MessageDetail
{
	String from;
	String to;
	String message;
	int messageId;
	String status;
	MessageDetail(String from, String to, String message,int messageId,String status)
	{
		this.from = from;
		this.to = to;
		this.message = message;
		this.messageId = messageId;
		this.status = status;
	}
}

class HomePage
{
	static ArrayList<UserDetail> userDetail = new ArrayList<UserDetail>();

	static Scanner s=new Scanner(System.in);

	static ArrayList<MessageDetail> sendMailList = new ArrayList<>();
	static ArrayList<MessageDetail> receivedMailList = new ArrayList<>();
	
	static int sendID = 1;
	static int receivedID = 1; 

	protected void startHome()
	{
		ArrayList<MessageDetail> detailMailNew = new ArrayList<>();

		userDetail.add(new UserDetail("Sita", "lieutenantRam11", "princess11@zmail.com", detailMailNew, 0));
		userDetail.add(new UserDetail("Ram", "princessNoor11", "lieutenant11@zmail.com", detailMailNew, 0 ));

		System.out.println("Thank you for visited...");
		int option=0;
		while(option!=3)
		{
			System.out.println("1. Login");
			System.out.println("2. Create new account");
			System.out.println("3. Exit");
			System.out.println("Enter your option...");
			option = s.nextInt();

			switch(option)
			{
			case 1:
				Login log = new Login();
				log.login(userDetail);
				break;
			case 2:
				CreateNewAcc newAcc = new CreateNewAcc();
				newAcc.createNewID();
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