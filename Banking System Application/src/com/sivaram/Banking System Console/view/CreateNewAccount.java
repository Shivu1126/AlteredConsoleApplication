package view;

import java.util.Scanner;

import controller.*;
import model.*;


public class CreateNewAccount
{
	private Scanner scanner = new Scanner(System.in);
	public void createNewAccount()
	{
		System.out.println("Welcome.....");
		System.out.println("Enter your name : ");
		String customerName=scanner.next();
		CustomerDetail customerInfo;
		int accountNumber=0;

		int sizeOfList=BankingSystemManagement.getInstance().getCustomerList().size();
		if(sizeOfList==0)
		{
			accountNumber = 123;
		}
		else
		{
			customerInfo=BankingSystemManagement.getInstance().getCustomerList().get(sizeOfList-1);
			accountNumber=customerInfo.getAccountNumber()+1;			
		}
		
		System.out.println("Create your password : ");
		String customerPassword=scanner.next();
		while(true)
		{
			CustomerController checkValidOrNot = new CustomerController();
			
			if(checkValidOrNot.checkValidPassword(customerPassword))
			{
				System.out.println("Your password is strong");
				break;
			}
			else
			{
				System.out.println("Your password is weak : ");
				System.out.println("Re-enter password");
				customerPassword=scanner.next();
			}
		}
		int initialBalance = 500;
		CustomerDetail addDetails = new CustomerDetail();
		addDetails.setCustomerName(customerName);
		addDetails.setAccountNumber(accountNumber);
		addDetails.setAccountBalance(initialBalance);
		addDetails.setPassword(customerPassword);
		
		BankingSystemManagement.getInstance().insertCustomerList(addDetails);
		
		System.out.println("Successfully created..");
		System.out.println("-------------------------------------");
		System.out.println("Your details.....");
		System.out.println("-------------------------------------");
		System.out.println("Name    : "+customerName);
		System.out.println("AccountNumber  : "+accountNumber);
		System.out.println("PassWord: "+customerPassword);
		System.out.println("AccountBalance : "+initialBalance);
		System.out.println("-------------------------------------");
	}

}
