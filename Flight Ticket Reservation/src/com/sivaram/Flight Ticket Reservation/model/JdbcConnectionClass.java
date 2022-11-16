package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcConnectionClass {

	private static Connection jdbcConnection = null;
	
	public static Connection getInstance() 
	{
		if(jdbcConnection==null)
		{
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				jdbcConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/flightticketreservation?useSSL=false","root","0001");
			} catch (SQLException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
		}
		return jdbcConnection;
	}

}
