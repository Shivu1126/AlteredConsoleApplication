package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import model.JdbcConnectionClass;


public class CancelTicket 
{
	private Scanner scanner = new Scanner(System.in);
	private Statement statement=null;

	public void cancelTicket()
	{
		ResultSet detailOfpassenger = null;
		ResultSet detailOfFlight = null;
		int checkCancelOrNot=0;
		String cancellationMailId = "";
		int cancellationTicketId;
		try {
			statement = JdbcConnectionClass.getInstance().createStatement();
			
			while(true)
			{
				System.out.println("Enter mail id ");
				cancellationMailId = scanner.next();
				System.out.println("Enter ticket id ");
				cancellationTicketId = scanner.nextInt();
				
				detailOfpassenger = statement.executeQuery("select *from passengerDetail where ticketId = "+cancellationTicketId);
				
				detailOfpassenger.next();
				
				int flightId = detailOfpassenger.getInt(4);
				int ticketPrice = detailOfpassenger.getInt(5);
				
				detailOfFlight = statement.executeQuery("select *from flightdetails where flightId = "+flightId);
				
				detailOfFlight.next();
				int oneTicketPrice = detailOfFlight.getInt(6);
				
				int calculateTicket = ticketPrice/oneTicketPrice;
				int nowBookingSeats = detailOfFlight.getInt(3);
				
				int updateBookingSeats = nowBookingSeats - calculateTicket;
				
				
				checkCancelOrNot = statement.executeUpdate("delete from passengerDetail where ticketId = "+cancellationTicketId+" and mailId = '"+cancellationMailId+"'");
				if(checkCancelOrNot==1)
				{
					System.out.println("Your ("+calculateTicket+")Ticket cancellation is successful.");
					statement.executeUpdate("update flightdetails set bookingSeats = "+updateBookingSeats+" where flightId = "+flightId);
					
					waitingListToPassengerList(flightId, updateBookingSeats,oneTicketPrice);
					
					break;
				}
				else
				{
					System.err.println("Cancellation failed..");
				}
				
				System.out.println("If you want to go home page..press 1...else press anyother number..");
				int cancelLoop = scanner.nextInt();
				if(cancelLoop==1)
					break;
			}
			
		} catch ( SQLException e) {
			// TODO Auto-generated catch block
			System.err.println("Ticket not found");
			e.printStackTrace();
		}
		
	}
	private void waitingListToPassengerList(int flightId, int totalBookingTicket, int ticketPrice)
	{
		try {
			ResultSet waitingList = statement.executeQuery("select *from waitingList where flightId = "+flightId+" and ticketCount <= "+(50-totalBookingTicket));
			
			if(!waitingList.next())
			{
				System.out.println("empty list");
				return;
			}
			
			String passengerName = waitingList.getString(1);
			String passengerMailId = waitingList.getString(2);
			int ticketCount = waitingList.getInt(4);
			String classes = waitingList.getString(5);
			
			int totalPrice = ticketCount*ticketPrice;
			
			int insertPassengerDetais = statement.executeUpdate("insert into passengerDetail values ('"+passengerName+"','"+passengerMailId+"',"+0+","+flightId+","+totalPrice+",'"+classes+"')");
			if(insertPassengerDetais==1)
			{
				int deleteWaitingList = statement.executeUpdate("delete from waitingList where passengerName = '"+passengerName+"' and mailId = '"+passengerMailId+"'");
				if(deleteWaitingList==1)
				{
					System.out.println(passengerName+" : your ticket is booked ");
				}
				ResultSet updateSeats = statement.executeQuery("select *from flightdetails where flightId = "+flightId);
				updateSeats.next();
				int seats = updateSeats.getInt(3);
				int updateSeatCount = seats + ticketCount;
				statement.executeUpdate("update flightdetails set bookingSeats = "+updateSeatCount+" where flightId = "+flightId);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
