package com.sivaram.utsproject.view;

import java.util.List;
import java.util.Scanner;

import com.sivaram.utsproject.controller.UserController;
import com.sivaram.utsproject.model.Ticket;
import com.sivaram.utsproject.model.User;

public class UserView {
	private Scanner scanner = new Scanner(System.in);
	private Scanner scanStr = new Scanner(System.in);
	private UserController userController;
	public UserView()
	{
		userController = new UserController(this);
	}
	protected void userView() 
	{
		System.out.println("Enter username");
		String name = scanner.next();
		System.out.println("Enter password");
		String password = scanner.next();
		User loginUser = userController.passwordCheck(name,password);
		if(loginUser == null )
		{
			return;
		}
		System.out.println("Welcome "+loginUser.getUserName());
		while(true)
		{
			System.out.println("---------------------------");
			System.out.println("a. Update the wallet amount\n"
					+ "b. Book Train Ticket\n"
					+ "c. Show the valid ticket\n"
					+ "d. Delete Account\n"
					+ "e. Logout\n");
			System.out.println("Enter your option");
			String opt = scanner.next();
			switch (opt) 
			{
				case "a": 
					updateTheWalletAmount(loginUser);
					break;
				case "b":
					bookTicket(loginUser);
					break;
				case "c":
					showTheTicket(loginUser);
					break;
				case "d":
					deleteAccount(loginUser);
					return;
				case "e":
					System.out.println("Thank you");
					return;
				default:
					System.out.println("Enter proper input...");
			}		
		}
	}
	
	private void updateTheWalletAmount(User loginUser) 
	{
		System.out.println("Enter amount");
		int amount = scanner.nextInt();
		userController.updateWalletAmount(loginUser.getId(),amount);
		System.out.println("Wallet amount updated");
		System.out.println("Your wallet amount now :- "+loginUser.getWalletBalance());
	}
	private void bookTicket(User loginUser) 
	{
		System.out.println("Enter from depot");
		String from = scanStr.nextLine();
		System.out.println("Enter to depot");
		String to = scanStr.nextLine();
		System.out.println("How many tickets..?");
		int ticketCount = scanner.nextInt();
		if(! userController.checkRoutes(from,to,loginUser,ticketCount))
		{
			return;
		}
		
	}
	private void showTheTicket(User loginUser) 
	{
		List<Ticket> ticketList = userController.getTicketDetail();
		if(ticketList == null)
		{
			System.out.println("Empty ticket list");
			return;
		}
		int inOrNot = 0;
		System.out.println("-----------------------");
		System.out.println("Your ticket details");
		System.out.println("-----------------------");
		for(Ticket t : ticketList)
		{
			if(t.getPassengerName().equals(loginUser.getUserName()))
			{
				inOrNot = 1;
				System.out.println("Name     : "+t.getPassengerName());
				System.out.println("From     : "+t.getFromDepot());
				System.out.println("To       : "+t.getToDepot());
				System.out.println("Time     : "+t.getStartTime());
				System.out.println("Exp.time : "+t.getEndTime());
				System.out.println("totTicket: "+t.getTicketCount());
				System.out.println("Price    : "+t.getTicketPrice());
				System.out.println("FullPrice: "+t.getTotalPrice());
				System.out.println("----------------------------------");
			}
		}
		if(inOrNot==0)
		{
			System.out.println("No tickets there");
			System.out.println("-----------------------");
		}
	}
	private void deleteAccount(User loginUser) {
		userController.updateStatus(loginUser.getId());
		System.out.println("Your account has been deleted...");
	}
	public void alertMsg(String msg)
	{
		System.out.println(msg);
	}
}