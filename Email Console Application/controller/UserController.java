package controller;

import java.util.ArrayList;
import java.util.Scanner;

import model.*;

public class UserController 
{
	private Scanner scanner=new Scanner(System.in);
	
	public void composeMail(UserDetail activeAccount)
	{
		System.out.println("To : ");
		String toMailId;
		int receiverIndex;
		UserDetail receiverAccount;
		while(true)
		{
			toMailId = scanner.next();
			CheckPasswordAndEmail inOrNot = new CheckPasswordAndEmail();
			receiverIndex = inOrNot.receiverMailIdCheck(toMailId);
			if(receiverIndex!=-1)
			{
				receiverAccount = MailDatabase.getInstance().getUserDetail().get(receiverIndex);
				break;
			}
			else
			{
				System.err.println("Invalid Email address..");
				System.out.println("Enter proper email address..");
			}
		}
		System.out.println("Enter your message..");
		scanner.nextLine();
		String message = scanner.nextLine();
		
		String fromEmailId = activeAccount.getEmailID();
		int generateSendId = activeAccount.getSendMailList().size()+1;
		
		MessageDetail sendMessage = new MessageDetail();
		sendMessage.setFrom(fromEmailId);
		sendMessage.setTo(toMailId);
		sendMessage.setMessage(message);
		sendMessage.setMessageId(generateSendId);
		sendMessage.setStatus("in");
		
		activeAccount.insertSendMailList(sendMessage);
		
		int generateReceivedId = receiverAccount.getReceivedMailList().size()+1;

		MessageDetail receivedMessage = new MessageDetail();
		receivedMessage.setFrom(fromEmailId);
		receivedMessage.setTo(toMailId);
		receivedMessage.setMessage(message);
		receivedMessage.setMessageId(generateReceivedId);
		receivedMessage.setStatus("in");
		
		receiverAccount.insertReceivedMailList(receivedMessage);
		System.out.println("Message Sented..");
	}

	public void receivedMailDisplayAndDelete(String userId,boolean deleteOrNot) 
	{
		ArrayList<UserDetail> userDetail = MailDatabase.getInstance().getUserDetail();
		int userIndex=-1;
		for(int i=0;i<userDetail.size();i++)
		{
			if(userDetail.get(i).getEmailID().equals(userId))
			{
				userIndex = i;
				break;
			}
		}
		
		if(MailDatabase.getInstance().getUserDetail().get(userIndex).getReceivedMailList().size()==0)
		{
			System.out.println("Empty received mails list");
			return;
		}
		
		System.out.println("-------------------------------------------------");
		System.out.println(userId+": your received mails ");			
		System.out.println("-------------------------------------------------");
		System.out.println("messageId\tmessageFrom \t Message");
		System.out.println("-------------------------------------------------");
		for(MessageDetail msg: MailDatabase.getInstance().getUserDetail().get(userIndex).getReceivedMailList())
		{
			if(msg.getStatus().equals("in"))
			{
				System.out.println(msg.getMessageId()+"\tFrom:"+msg.getFrom()+"\t-> "+msg.getMessage());
			}
		}
		System.out.println("-------------------------------------------------");

		if(deleteOrNot)
		{
			System.out.println("Enter message id for delete that message..");
			int deleteId = scanner.nextInt();
			
					for(MessageDetail msg: MailDatabase.getInstance().getUserDetail().get(userIndex).getReceivedMailList())
					{
						if(msg.getMessageId()==deleteId && msg.getStatus().equals("in"))
						{
							msg.setStatus("removed");
							System.out.println("Message is removed..");
							return;
						}
					}
			
			System.err.println("Message not present");
		}

	}	

	public void sentedMailDispalyAndDelete(String userId, boolean deleteOrNot)
	{
		ArrayList<UserDetail> userDetail = MailDatabase.getInstance().getUserDetail();
		int userIndex=-1;
		for(int i=0;i<userDetail.size();i++)
		{
			if(userDetail.get(i).getEmailID().equals(userId))
			{
				userIndex = i;
				break;
			}
		}
		
		if(MailDatabase.getInstance().getUserDetail().get(userIndex).getSendMailList().size()==0)
		{
			System.out.println("Empty send mails list");
			return;
		}	
	
		System.out.println("-------------------------------------------------");
		System.out.println(userId+": your send mails ");			
		System.out.println("-------------------------------------------------");
		System.out.println("messageId\tmessageTo \t Message");
		System.out.println("-------------------------------------------------");
		for(MessageDetail msg: MailDatabase.getInstance().getUserDetail().get(userIndex).getSendMailList())
		{
			if( msg.getStatus().equals("in"))
			{
				System.out.println(msg.getMessageId()+"\tTo:"+msg.getTo()+"\t-> "+msg.getMessage());
			}
		}
		System.out.println("-------------------------------------------------");

		if(deleteOrNot)
		{
			System.out.println("Enter message id for delete that message..");
			int deleteId = scanner.nextInt();

			for(MessageDetail msg: MailDatabase.getInstance().getUserDetail().get(userIndex).getSendMailList())
			{
				if(msg.getMessageId()==deleteId && msg.getStatus().equals("in"))
				{
					msg.setStatus("removed");
					System.out.println("Message is removed..");
					return;
				}
			}
			System.err.println("Message not present");

		}
	}

}
