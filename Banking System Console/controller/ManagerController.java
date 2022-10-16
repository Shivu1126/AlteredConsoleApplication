package controller;

import java.util.Scanner;

import model.BankingSystemManagement;
import model.CustomerDetail;

public class ManagerController 
{
	private Scanner scanner = new Scanner(System.in);
	public boolean checkManagerInfo(String name, String password)
	{
		if(name.equals("Ram") && password.equals("PrincessNoor11"))
		{
			return true;
		}
		return false;
	}
	
	public void viewAllCustomers()
	{
		System.out.println("Enter admin name");
		String managerName = scanner.next();
		System.out.println("Enter the password");
		String managerPassword = scanner.next();
		ManagerController checkInfo = new ManagerController();

		if(checkInfo.checkManagerInfo(managerName, managerPassword))
		{
			System.out.println("------------------------------------------------------------");
			System.out.println("Name\t\tAccount.No\tBalance");
			System.out.println("------------------------------------------------------------");
			for(CustomerDetail customerInfo : BankingSystemManagement.getInstance().getCustomerList())
			{
				System.out.println(customerInfo.getCustomerName()+"\t\t"+customerInfo.getAccountNumber()+"\t\t"+customerInfo.getAccountBalance());
			}
			System.out.println("------------------------------------------------------------");
		}	
		else
		{
			System.err.println("Invalid name or password");
		}
	}
}
