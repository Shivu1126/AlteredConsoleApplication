package com.sivaram.utsproject.view;

import java.util.Scanner;

import com.sivaram.utsproject.controller.AdminController;
import com.sivaram.utsproject.controller.UserController;
import com.sivaram.utsproject.model.Admin;
import com.sivaram.utsproject.model.User;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MainView start = new MainView();
		start.mainMenu();
	}
}
class MainView
{
	private Scanner scanner = new Scanner(System.in);


	static {
		User user1 = new User();
		user1.setUserName("Kailash");
		user1.setEmail("kailash@gmail.com");
		user1.setId(11);
		user1.setPhone("9876543210");
		user1.setWalletBalance(100);
		user1.setPassword("jzhkzrg34");
		user1.setStatus("active");
		UserController userController1 = new UserController();
		userController1.setUserDetail(user1);
		User user2 = new User();
		user2.setUserName("Karthiga");
		user2.setEmail("karthiga@gmail.com");
		user2.setId(12);
		user2.setPhone("9876543211");
		user2.setWalletBalance(90);
		user2.setPassword("jzqsghfz53");
		user2.setStatus("active");
		UserController userController2 = new UserController();
		userController2.setUserDetail(user2);
		//		Kailash  - 11  - kailash@gmail.com  -  9876543210  -  100  - (kailash45)     jzhkzrg34
		//		Karthiga - 12 -  karthiga@gmail.com -  9876543211  -   90 -   (karthiga64)  jzqsghfz53
		//		Admin  -   0  - admin@gmail.com - 9876543200  -(admin123)     zclhm012
		Admin admin = new Admin();
		admin.setAdminName("admin");
		admin.setId(0);
		admin.setEmail("admin@gmail.com");
		admin.setPassword("zclhm012");
		admin.setPhone("9876543200");
		AdminController adminContoller = new AdminController();
		adminContoller.setAdminDetail(admin);
	}
	protected void mainMenu() 
	{	
		while(true)
		{
			System.out.println("1. Account Login");
			System.out.println("2. Admin Login");
			System.out.println("3. Exit");
			System.out.println("Enter your option..");
			String opt = scanner.next();
			if(opt.equals("1"))
			{
				UserView user = new UserView();
				user.userView();
			}
			else if(opt.equals("2"))
			{
				AdminView admin = new AdminView();
				admin.adminView();
			}
			else if(opt.equals("3"))
			{
				System.out.println("Thank you");
				return;
			}
			else
			{
				System.out.println("Enter proper input...");
			}
		}
	}
}