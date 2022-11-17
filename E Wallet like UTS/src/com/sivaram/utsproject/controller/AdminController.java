package com.sivaram.utsproject.controller;

import java.util.HashMap;
import java.util.List;

import com.sivaram.utsproject.model.Admin;
import com.sivaram.utsproject.model.Ticket;
import com.sivaram.utsproject.repositary.UtsDatabase;
import com.sivaram.utsproject.view.AdminView;

public class AdminController 
{

	private AdminView adminView;
	public AdminController(AdminView adminView) 
	{
		this.adminView = adminView;
	}
	public AdminController() {
	}
	public void setAdminDetail(Admin admin) {
		UtsDatabase.getInstance().setAdminList(admin);
	}
	public Admin adminLoginCheck(String name, String password)
	{
		List<Admin> adminList = UtsDatabase.getInstance().getAdminList();
		UserController userController = new UserController();
		String encryptedPassword = userController.encryptPassword(password);
		for(Admin admin: adminList)
		{
			if(admin.getAdminName().equals(name) && admin.getPassword().equals(encryptedPassword))
			{
				adminView.alerMsg("Admin login successfully");
				return admin;
			}
		}
		adminView.alerMsg("Admin Login failed..");
		return null;
	}
	
	public HashMap<String, Long> getRouteList() {
		return UtsDatabase.getInstance().getRouteList();
	}
	public void setRoutes(HashMap<String, Long> routeList) {
		UtsDatabase.getInstance().setRouteList(routeList);
	}
	public List<Ticket> getTicketDetail() {
		return new UserController().getTicketDetail();
	}
	
}
