package emailConsoleApplication;

import java.util.ArrayList;

public class AccountPage extends HomePage
{
	protected void accountPage(UserDetail activeAccount,ArrayList<UserDetail> mailDetails)
	{
		System.out.println("Welcome "+activeAccount.name);
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
			int pageOption = s.nextInt();

			switch(pageOption)
			{
			case 1:
				composeMail(activeAccount,mailDetails);
				break;
			case 2:
				receivedMailDisplayAndDelete(receivedMailList, activeAccount.emailID, 1);
				break;
			case 3:
				sentedMailDispalyAndDelete(sendMailList, activeAccount.emailID,1);
				break;
			case 4:
				receivedMailDisplayAndDelete(receivedMailList, activeAccount.emailID, 2);
				break;
			case 5:
				sentedMailDispalyAndDelete(sendMailList, activeAccount.emailID,2);
				break;
			case 6:
				System.out.println("Thank you "+activeAccount.name);
				return;
			default:
				System.out.println("Enter proper input..");
				break;
			}
		}

	}
	
	protected static void composeMail(UserDetail activeAccount, ArrayList<UserDetail> mailInform)
	{
		System.out.println("To : ");
		String toMailId;
		int presentOrNot;
		while(true)
		{
			toMailId = s.next();
			PresentOrNotEmail inOrNot = new PresentOrNotEmail();
			presentOrNot = inOrNot.presentOrNotMail(toMailId,mailInform);
			if(presentOrNot!=-1)
			{
				break;
			}
			else
			{
				System.err.println("Invalid Email address..");
				System.out.println("Enter proper email address..");
			}
		}
		System.out.println("Enter your message..");
		s.nextLine();
		String message = s.nextLine();
		
		sendMailList.add(new MessageDetail(activeAccount.emailID, toMailId, message, sendID, "in"));
		receivedMailList.add(new MessageDetail(activeAccount.emailID, toMailId, message, receivedID, "in"));
		sendID++;
		receivedID++;
		
		System.out.println("Message Sented..");
	}

	private static void receivedMailDisplayAndDelete(ArrayList<MessageDetail> receivedMails,String userId,int deleteOrNot) 
	{
		int presentIds[]=new int[receivedMails.size()];
		int count=0;
		for(MessageDetail m: receivedMails)
		{
			if(m.to.equals(userId))
			{
				count++;
			}
		}
		if(count==0)
		{
			System.out.println("Empty list");
			return;
		}
		int i=0;
		System.out.println("-------------------------------------------------");
		System.out.println(userId+": your received mails ");			
		System.out.println("-------------------------------------------------");
		System.out.println("messageId\tmessageFrom \t Message");
		System.out.println("-------------------------------------------------");
		for(MessageDetail m: receivedMails)
		{
			if(m.to.equals(userId) && m.status.equals("in"))
			{
				presentIds[i++]=m.messageId;
				System.out.println(m.messageId+"\tFrom:"+m.from+"\t-> "+m.message);
			}
		}
		System.out.println("-------------------------------------------------");
		
		if(deleteOrNot==2)
		{
			System.out.println("Enter message id for delete that message..");
			int deleteId = s.nextInt();
			for(int j=0;j<i;j++)
			{
				if(presentIds[j]==deleteId)
				{
					for(MessageDetail m: receivedMails)
					{
						if(m.messageId==presentIds[j])
						{
							m.status="removed";
							System.out.println("Message is removed..");
							break;
						}
					}
					return;
				}
			}
			System.err.println("Id not present..Message not removed");
		}
		
	}	
	
	private static void sentedMailDispalyAndDelete(ArrayList<MessageDetail> sendMails,String userId, int deleteOrNot)
	{
		int presentIds[]=new int[sendMails.size()];
		int count=0;
		//System.out.println(userId);
		for(MessageDetail m: sendMails)
		{
			if(m.from.equals(userId))
			{
				count++;
			}
		}
		if(count==0)
		{
			System.out.println("Empty list");
			return;
		}
		int i=0;

		System.out.println("-------------------------------------------------");
		System.out.println(userId+": your send mails ");			
		System.out.println("-------------------------------------------------");
		System.out.println("messageId\tmessageTo \t Message");
		System.out.println("-------------------------------------------------");
		for(MessageDetail m: sendMails)
		{
			if(m.from.equals(userId) && m.status.equals("in"))
			{
				presentIds[i++]=m.messageId;
				System.out.println(m.messageId+"\tTo:"+m.to+"\t-> "+m.message);
			}
		}
		System.out.println("-------------------------------------------------");
		
		if(deleteOrNot==2)
		{
			System.out.println("Enter message id for delete that message..");
			int deleteId = s.nextInt();
			for(int j=0;j<i;j++)
			{
				if(presentIds[j]==deleteId)
				{
					for(MessageDetail m: sendMails)
					{
						if(m.messageId==presentIds[j])
						{
							m.status="removed";
							System.out.println("Message is removed..");
							break;
						}
					}
					return;
				}
			}
			System.err.println("Id not present..Message not removed");
		}
	}

}
