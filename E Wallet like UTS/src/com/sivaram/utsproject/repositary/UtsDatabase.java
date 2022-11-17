package com.sivaram.utsproject.repositary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.sivaram.utsproject.model.Admin;
import com.sivaram.utsproject.model.Ticket;
import com.sivaram.utsproject.model.User;

public class UtsDatabase {
	private static UtsDatabase utsdatabase;
	
	private List<User> userList = new ArrayList<>();
	private List<Admin> adminList = new ArrayList<>();
	private List<Ticket> ticketList = new ArrayList<>();
	
	private HashMap<String, Long> routeList = new HashMap<>();
	public static UtsDatabase getInstance()
	{
		if(utsdatabase==null)
			utsdatabase = new UtsDatabase();
		return utsdatabase;
	}

	public List<User> getUserList() {
		return userList;
	}
	public void setUserList(User user) {
		this.userList.add(user);
	}

	public List<Admin> getAdminList() {
		return adminList;
	}

	public void setAdminList(Admin admin) {
		this.adminList.add(admin);
	}

	public HashMap<String, Long> getRouteList() {
		return routeList;
	}

	public void setRouteList(HashMap<String, Long> routeList) {
		this.routeList = routeList;
	}

	public List<Ticket> getTicketList() {
		return ticketList;
	}

	public void setTicketList(Ticket ticketList) {
		this.ticketList.add(ticketList);
	}
	
}
