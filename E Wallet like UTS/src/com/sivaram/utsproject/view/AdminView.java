package com.sivaram.utsproject.view;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.sivaram.utsproject.controller.AdminController;
import com.sivaram.utsproject.model.Admin;
import com.sivaram.utsproject.model.Ticket;

public class AdminView {
	private Scanner scanner = new Scanner(System.in);
	private AdminController adminController;
	public AdminView() {
		adminController = new AdminController(this);
	}
	protected void adminView()
	{
		System.out.println("Enter admin name");
		String name = scanner.next();
		System.out.println("Enter password");
		String password = scanner.next();
		Admin admin = adminController.adminLoginCheck(name, password);
		if(admin==null)
		{
			return;
		}
		System.out.println("Options...\n----------------------------");
		while(true)
		{
			System.out.println("a. Provide Train schedule\n"
					+ "b. Check Revenue\n"
					+ "c. Logout\n");
			System.out.println("Enter your option");
			String opt = scanner.next();
			switch(opt)
			{
				case "a":
					provideTrainSchedule();
					break;
				case "b":
					checkRevenue();
					break;
				case "c":
					System.out.println("Thank you");
					return;
				default:
					System.out.println("Enter valid input");
			}
		}
	}
	private void provideTrainSchedule() 
	{
		if(adminController.getRouteList() == null)
		{
			System.out.println("Already schedule is provided");
			return;
		}
		try {
			FileReader reader = new FileReader("C:\\Users\\saran\\Downloads\\Traindata.json");
			Object obj = new JSONParser().parse(reader);
			JSONObject jo = (JSONObject) obj;
			JSONArray list = (JSONArray) jo.get("routes");
			HashMap<String, Long> routeList = new HashMap<>();
			for(int i=0;i<list.size();i++)
			{
				JSONObject routes = (JSONObject) list.get(i);
				System.out.println(routes.get("destination") + " " + routes.get("distance"));	
				routeList.put((String)routes.get("destination"), (Long)routes.get("distance"));
			}
			adminController.setRoutes(routeList);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void checkRevenue() {
		// TODO Auto-generated method stub
		List<Ticket> ticketList = adminController.getTicketDetail();
		if(ticketList == null)
		{
			System.out.println("Empty ticket list");
			return;
		}
		long revenue=0;
		for(Ticket t : ticketList)
		{
			revenue = (long) (revenue + t.getTotalPrice());
		}
		System.out.println("Total Revenue : "+revenue);
	}
	
	public void alerMsg(String msg) {
		System.out.println(msg);
	}
}
