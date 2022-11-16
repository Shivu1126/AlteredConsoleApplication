package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import model.JdbcConnectionClass;

public class BookTicket 
{
	private Scanner scanner = new Scanner(System.in);
	public void bookFlightTicket() throws ClassNotFoundException, SQLException
	{
		Statement statement = JdbcConnectionClass.getInstance().createStatement();

		int checkFlightId;
		while(true)
		{
			System.out.println("You are From..?(Nearest Airport)");
			String fromCity = scanner.next();
			System.out.println("You are Going to..?");
			String toCity = scanner.next();
			String fromAndTo = fromCity+" - "+toCity;
			
			ResultSet findFlight = statement.executeQuery("select *from flightdetails where travelFromAndTo = '"+fromAndTo+"'");
	
			if(!findFlight.next())
			{
				System.out.println("Flight is not avaliable");
				break;
			}
			
			checkFlightId = findFlight.getInt(1);
			
			System.out.println("Your flight details....");
			System.out.println("--------------------------------------------");
			System.out.println("Flight ID        : "+findFlight.getInt(1));
			System.out.println("Flight Name      : "+findFlight.getString(2));
			System.out.println("Booked Tickets   : "+findFlight.getInt(3));
			System.out.println("AvailableTickets : "+(50-findFlight.getInt(3)));
			System.out.println("Date and Time    : "+findFlight.getString(5));
			System.out.println("Ticket Price     : "+findFlight.getInt(6));
			System.out.println("--------------------------------------------");

			
			System.out.println("Enter your Name..");
			String passengerName = scanner.next();
			
			System.out.println("Enter your mail id");
			String passengerMailId = scanner.next();
			while(true)
			{
				MailIdCheck checkMail = new MailIdCheck();
				if(!checkMail.checkValidEmail(passengerMailId))
				{
					System.out.println("Invalid email Id..");
					System.out.println("Enter valid emailId");
					passengerMailId = scanner.next();
				}
				else
					break;
			}
			
			System.out.println("Enter How many tickets..");
			int countTicket = scanner.nextInt();
				
			System.out.println("Enter 1. Business Class or 2. Economical Class");
			int classes = scanner.nextInt();
			String whichClass = classes==1?"Business":"Economical";
			
			if(countTicket>(50-findFlight.getInt(3)))
			{
				System.out.println("Ticket is not available");
				System.out.println("You are being the waiting list.");
				waitingList(passengerName,passengerMailId,findFlight.getInt(1),countTicket,whichClass);
				break;
			}
			
			int updateTicketCount = findFlight.getInt(3)+countTicket;
			int businessClassPrice = (findFlight.getInt(6)/100 * 25)+findFlight.getInt(6);
			int ticketPrice = whichClass.equals("Business")?countTicket*businessClassPrice:countTicket*findFlight.getInt(6);
					
			int booked = statement.executeUpdate("insert into passengerDetail values ('"+passengerName+"','"+passengerMailId+"',"+0+","+findFlight.getInt(1)+","+ticketPrice+",'"+whichClass+"')");
			if(booked==1)
				System.out.println("Booked successfully..."
						+ "You will check your ticket on a Passenger Details...");
			else
			{
				System.out.println("Some issue on your details..Please check all your details");
				break;
			}
		
			Statement statement1 = JdbcConnectionClass.getInstance().createStatement();
			int count = statement1.executeUpdate("update flightdetails set bookingSeats = "+updateTicketCount+" where flightId = "+checkFlightId+"");
			if(count==1)
			{
				break;
			}
		}

	}
	private void waitingList(String passengerName, String passengerMailId,int flightId, int ticketCount, String classes) throws ClassNotFoundException, SQLException 
	{
		Statement statement = JdbcConnectionClass.getInstance().createStatement();
		int addWaitingList = statement.executeUpdate("insert into waitingList values ('"+passengerName+"','"+passengerMailId+"',"+flightId+","+ticketCount+",'"+classes+"')");
		if(addWaitingList==1)
		{
			System.out.println("Please often check in Passenger Details List section...");
		}
	}

}
