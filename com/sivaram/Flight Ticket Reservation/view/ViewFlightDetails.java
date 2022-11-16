package view;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.*;

public class ViewFlightDetails 
{
	protected void viewFlightDetails() 
	{
		Statement statement;
		try {
			statement = JdbcConnectionClass.getInstance().createStatement();


			ResultSet flightInfo =  statement.executeQuery("select *from flightdetails");

			System.out.println("FligtId\tFlightName\tBookedTickets\tAvailableTicket\t\tFrom - To\t\tDate and Time\t\tTicketPrice");
			System.out.println("--------------------------------------------------------------------------------------------------------------------------");
			
	//	ArrayList<Flight> flights = new ArrayList<>();
			
			while(flightInfo.next())
			{
				System.out.println(flightInfo.getInt(1)+"\t"+flightInfo.getString(2)+"\t\t"+flightInfo.getInt(3)+"\t\t"+(50 -flightInfo.getInt(3))+"\t\t"
						+flightInfo.getString(4)+"\t"+flightInfo.getString(5)+"\t"+flightInfo.getInt(6));
				
//				Flight flightDetail = new Flight();
//				flightDetail.setFlightId(flightInfo.getInt(1));
//				flightDetail.setFlightName(flightInfo.getString(2));
//				flightDetail.setBookingSeats(flightInfo.getInt(3));
//				flightDetail.setAvailbaleSeats(50-flightInfo.getInt(3));
//				flightDetail.setTravelFromAndTo(flightInfo.getString(4));
//				flightDetail.setDataAndTime(flightInfo.getString(5));
//				flightDetail.setTicketPrice(flightInfo.getInt(6));
//				
//				flights.add(flightDetail);
//				
//				int a=flightDetail.getFlightId();
			}	
			System.out.println("--------------------------------------------------------------------------------------------------------------------------");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
