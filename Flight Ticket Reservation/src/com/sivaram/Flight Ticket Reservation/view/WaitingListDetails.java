package view;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.JdbcConnectionClass;

public class WaitingListDetails 
{
	protected void waitingListDeatils()
	{
		try {
			Statement statement = JdbcConnectionClass.getInstance().createStatement();
			
			ResultSet waitingList = statement.executeQuery("select *from waitingList");
			
			if(!waitingList.next())
			{
				System.out.println("Empty List");
				return;
			}
			System.out.println("Waiting List Passengers..");
			System.out.println("------------------------------------------------------------------");
			System.out.println("Name\t\t EmailId\t FlightId\t totalTicket\t Class");
			System.out.println("------------------------------------------------------------------");
			do
			{
				System.out.println(waitingList.getString(1)+"\t"+waitingList.getString(2)+"\t\t"+waitingList.getString(3)+"\t"
						+waitingList.getInt(4)+"\t"+waitingList.getString(5));
			}while(waitingList.next());
			System.out.println("------------------------------------------------------------------");
		
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
