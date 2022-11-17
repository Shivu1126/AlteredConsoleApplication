package com.sivaram.utsproject.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sivaram.utsproject.model.Ticket;
import com.sivaram.utsproject.model.User;
import com.sivaram.utsproject.repositary.UtsDatabase;
import com.sivaram.utsproject.view.UserView;

public class UserController
{
	private UserView userView;
	public UserController(UserView userView) {
		this.userView = userView;
	}
	public UserController() {
	}
	
	public void setUserDetail(User user)
	{
		UtsDatabase.getInstance().setUserList(user);
	}

	public User passwordCheck(String name,String password)
	{
		String encryptedPassword = encryptPassword(password);
		List<User> userList = UtsDatabase.getInstance().getUserList();
		for(User user: userList)
		{
			if(user.getUserName().equals(name) && user.getPassword().equals(encryptedPassword) && user.getStatus().equals("active"))
			{
				userView.alertMsg("Login successfully");
				return user;
			}
		}
		userView.alertMsg("Login failed..");
		return null;
	}

	protected String encryptPassword(String password) {
		String encryptedPassword = "";
		for(int i=0;i<password.length();i++)
		{
			char c = password.charAt(i);
			if(c=='a')
				encryptedPassword += "z";
			else if(c=='A')
				encryptedPassword += "Z";
			else if(c=='0')
				encryptedPassword += "9";
			else
				encryptedPassword += (char)(c-1);
		}
		return encryptedPassword;
	}
	public void updateWalletAmount(int id, int amount) 
	{
		//List<User> userList = UtsDatabase.getInstance().getUserList();
		for(User user : UtsDatabase.getInstance().getUserList())
		{
			if(user.getId()==id)
			{
				user.setWalletBalance(user.getWalletBalance()+amount);
				return;
			}
		}
		userView.alertMsg("Id is not there..");
	}
	public void updateStatus(int id) {
		
		List<User> userList = UtsDatabase.getInstance().getUserList();
		
		for(User user : userList) {
			if(user.getId() == id) {
				user.setStatus("deleted");
				break;
			}
		}
		
//		userView.alertMsg("Id is not there..");
	}
	public boolean checkRoutes(String from, String to, User loginUser, int ticketCount)
	{
		
		if(from.equals(to))
		{
			userView.alertMsg("Enter proper station");
			return false;
		}
		
		HashMap<String , Long> routes = UtsDatabase.getInstance().getRouteList();
		long startKm = -1;
		long endKm = -1;
		for(Map.Entry<String, Long> entry : routes.entrySet() )
		{
			if(entry.getKey().equals(from))
			{
				startKm = (long)entry.getValue();
			}
			if(entry.getKey().equals(to))
			{
				endKm = (long)entry.getValue();
			}
		}
		if(startKm == -1 || endKm == -1)
		{
			userView.alertMsg("Your depot not available..");
			return false;
		}
		
		long maxKm = startKm < endKm ? endKm:startKm;
		long lowKm = startKm > endKm ? endKm:startKm;
		long travelKm = maxKm - lowKm;
		double ticketPrice;
		if(travelKm<=10)
			ticketPrice = 5;
		else 
		{
			ticketPrice= (double)(travelKm/10)*5;
		}
		if(ticketPrice>25)
			ticketPrice = 25;
		
		double totalPrice = ticketPrice*ticketCount;
		if(totalPrice>loginUser.getWalletBalance())
		{
			userView.alertMsg("Sorry to say...your balance is low...");
			return false;
		}
		
		loginUser.setWalletBalance(loginUser.getWalletBalance()-totalPrice);
		Ticket ticket = new Ticket();
		ticket.setPassengerName(loginUser.getUserName());
		ticket.setFromDepot(from);
		ticket.setToDepot(to);
		ticket.setTicketCount(ticketCount);
		ticket.setTicketPrice(ticketPrice);
		ticket.setTotalPrice(totalPrice);
		LocalDateTime startTimeObj = LocalDateTime.now();
	    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
	    LocalDateTime endTimeObj = startTimeObj.plusHours(1);
	    String startTime = startTimeObj.format(myFormatObj);
	    String endTime = endTimeObj.format(myFormatObj);
		ticket.setStartTime(startTime);
		ticket.setEndTime(endTime);
		
		UtsDatabase.getInstance().setTicketList(ticket);
		
		userView.alertMsg("Your ticket is booked..");
		return true;
	}
	public List<Ticket> getTicketDetail() {
		
		return UtsDatabase.getInstance().getTicketList();
	}
}
