package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import model.JdbcConnectionClass;

public class PassengerDetails 
{
	private Scanner scanner = new Scanner(System.in);
	public void showPassengerDetails() 
	{
		Statement statement = null;
		int ticketInOrNot=0;
		try {
			statement = JdbcConnectionClass.getInstance().createStatement();
		} catch ( SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		ResultSet passengerDetails = null;
		String mailId = "";
		Boolean b = true;
		while(b)
		{
			System.out.println("Enter your mail id");
			mailId = scanner.next();

			try {
				passengerDetails = statement.executeQuery("select *from passengerDetail where mailId = '"+mailId+"'");
				break;
			}
			catch(Exception e)
			{
				System.out.println("Enter proper mail...");
			}
			System.out.println("If you want go to home page press 1..");
			int goHome = scanner.nextInt();
			if(goHome==1)
			{
				return;
			}
		}
		try 
		{
			while(passengerDetails.next())
			{
				System.out.println("----------------------------------------------");
				System.out.println(passengerDetails.getString(1)+" : Your Ticket details...");
				System.out.println("----------------------------------------------");
				System.out.println("MailID       : "+passengerDetails.getString(2));
				System.out.println("TicketID     : "+passengerDetails.getInt(3));
				System.out.println("FlightId     : "+passengerDetails.getInt(4));
				System.out.println("Ticket Price : "+passengerDetails.getInt(5));
				System.out.println("Class        : "+passengerDetails.getString(6));
				System.out.println("----------------------------------------------");
				ticketInOrNot=1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(ticketInOrNot==0)
			System.out.println("Ticket doesn't exixst");
	}
}
