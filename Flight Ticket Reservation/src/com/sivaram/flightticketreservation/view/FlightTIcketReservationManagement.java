package view;

import java.sql.SQLException;
import java.util.Scanner;

import controller.*;


public class FlightTIcketReservationManagement{

	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		FlightTicket bookTicket = new FlightTicket();
		bookTicket.flightTicketBooking();
	}

}
class FlightTicket  
{
	private Scanner scanner = new Scanner(System.in);
	protected void flightTicketBooking() 
	{
		System.out.println("Welcome to Booking Flight Ticket");
		System.out.println("--------------------------------");
		while(true)
		{
			System.out.println("1. View Flight Details");
			System.out.println("2. Book Ticket");
			System.out.println("3. Cancel Ticket");
			System.out.println("4. Show Passenger Ticket Details");
			System.out.println("5. Waiting List Details");
			System.out.println("6. Exit");

			System.out.println("Enter your option");
			int choice = scanner.nextInt();
			
			switch(choice)
			{
			case 1:
				
					ViewFlightDetails viewFlight = new ViewFlightDetails();
					viewFlight.viewFlightDetails();

				break;
			case 2:
				try {
					BookTicket booking = new BookTicket();
					booking.bookFlightTicket();
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
				break;
			case 3:
				CancelTicket cancel = new CancelTicket();
				cancel.cancelTicket();
				break;
			case 4:
				PassengerDetails passengerDetail = new PassengerDetails(); 
				passengerDetail.showPassengerDetails();
				break;
			case 5:
				WaitingListDetails waitingList = new WaitingListDetails();
 				waitingList.waitingListDeatils();
				break;
			case 6:
				return;
			default:
				System.out.println("Enter proper input");
				break;
			}
		}
			
	}
}