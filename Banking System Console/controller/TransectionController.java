package controller;
import java.util.InputMismatchException;
import java.util.Scanner;

import model.*;

enum Via{
	V1("ATM"),V2("GPAY"),V3("BANK"),V4("AccountTransfer");
	private String via;
	Via(String str) {
		via = str;
	}
	public String getVia() {
		return this.via;
	}	
}
public class TransectionController 
{
	private Scanner scanner = new Scanner(System.in);
	public void depositeAmount(CustomerDetail customerInfo)
	{
		int typeOfTransection=0;
		while(true)
		{
			try {
				System.out.println("Enter which type of deposite");
				System.out.println("1.ATM");
				System.out.println("2.Bank");
				System.out.println("3.Account Transfer");
				typeOfTransection = scanner.nextInt();
				break;
			} catch (InputMismatchException e) {
				//e.printStackTrace();
				System.err.println("Enter numeric value");
			}
			scanner.next();
		}

		if(typeOfTransection>3 || typeOfTransection<1)
		{
			System.err.println("Enter 1 to 3 only..");
			return;
		}			
		String theWay="";

		if(typeOfTransection==1)
		{
			theWay=Via.V1.getVia();
		}
		if(typeOfTransection==2)
		{
			theWay=Via.V3.getVia();
		}
		if(typeOfTransection==3)
		{
			theWay=Via.V4.getVia();
		}

		if(typeOfTransection==3)
		{
			int transferAccountNumberInOrNot=1;

			int transferAccountNumber=0;
			while(true)
			{
				try {
					System.out.println("Enter Transfer Account Num : ");
					transferAccountNumber = scanner.nextInt();
					break;
				} catch (InputMismatchException e) {
					//e.printStackTrace();
					System.out.println("Enter valid number(account number)");
				}
				scanner.next();
			}
			
			for(CustomerDetail tranfersAccount:BankingSystemManagement.getInstance().getCustomerList())
			{
				if(tranfersAccount.getAccountNumber()==transferAccountNumber && transferAccountNumber!=customerInfo.getAccountNumber())
				{
					transferAccountNumberInOrNot=0;
					while(true)
					{
						int transferAmount=0;
						while(true)
						{
							try {
								System.out.println("Enter amount : ");
								transferAmount = scanner.nextInt();
								break;
							} catch (InputMismatchException e) {
								//e.printStackTrace();
								System.out.println("Enter correct amount");
							}
							scanner.next();
						}
						
						if(customerInfo.getAccountBalance()>=transferAmount)
						{
							tranfersAccount.setAccountBalance(tranfersAccount.getAccountBalance() + transferAmount);								
							addTransection(transferAccountNumber, theWay, "Deposite", transferAmount, tranfersAccount.getAccountBalance());
							System.out.println("Money credited to '"+tranfersAccount.getCustomerName()+"'");

							customerInfo.setAccountBalance(customerInfo.getAccountBalance() - transferAmount);				
							addTransection(customerInfo.getAccountNumber(), theWay, "Transfer", transferAmount, customerInfo.getAccountBalance());
							System.out.println("Your Account Balance : "+customerInfo.getAccountBalance());

							break;
						}
						else
						{
							System.out.println("Insufficient Money...");
						}
					}
				}
			}
			if(transferAccountNumberInOrNot==1)
			{
				System.out.println("Account Number doesn't exist....");
			}
		}
		else
		{
			int amount=0;
			while(true)
			{
				try {
					System.out.println("Enter amount : ");
					amount = scanner.nextInt();
					break;
				} catch (InputMismatchException e) {
					//e.printStackTrace();
					System.out.println("Enter correct amount");
				}
				scanner.next();
			}

			customerInfo.setAccountBalance(customerInfo.getAccountBalance() + amount);	
			addTransection(customerInfo.getAccountNumber(), theWay, "Deposite", amount, customerInfo.getAccountBalance());
			System.out.println("Money credited...");
		}

	}
	private void addTransection(int transferAccountNumber, String theWay, String transferMethod, int tranferAmount, int AccBalance)
	{
		TransectionDetail transection = new TransectionDetail();
		transection.setAccountNumber(transferAccountNumber);
		transection.setTransectionThrough(theWay);
		transection.setMethod(transferMethod);
		transection.setAmount(tranferAmount);
		transection.setAccountBalance(AccBalance);

		BankingSystemManagement.getInstance().insertTransectionList(transection);
	}
	
	public void withdrawAmount(CustomerDetail customerInfo) 
	{

		int typeOfTransection=0;
		while(true)
		{
			try {
				System.out.println("Enter which type of withdraw");
				System.out.println("1.ATM");
				System.out.println("2.Gpay");
				System.out.println("3.Bank");
				typeOfTransection = scanner.nextInt();
				break;
			} catch (InputMismatchException e) {
				//e.printStackTrace();
				System.err.println("Enter numeric value");
			}
			scanner.next();
		}

		if(typeOfTransection>3 || typeOfTransection<1)
		{
			System.err.println("Enter 1 to 3 only..");
			return;
		}			
		String theWay="";		
		if(typeOfTransection==1)
			theWay=Via.V1.getVia();
		if(typeOfTransection==2)
			theWay=Via.V2.getVia();
		if(typeOfTransection==3)
		{
			theWay=Via.V3.getVia();
		}
		while(true)
		{
			int amount=0;
			while(true)
			{
				try {
					System.out.println("Enter amount : ");
					amount = scanner.nextInt();
					break;
				} catch (InputMismatchException e) {
					//e.printStackTrace();
					System.out.println("Enter correct amount");
				}
				scanner.next();
			}
			if(amount<=customerInfo.getAccountBalance()) 
			{
				customerInfo.setAccountBalance(customerInfo.getAccountBalance() - amount);
				System.out.println("Money depited");

				TransectionDetail transection = new TransectionDetail();

				transection.setAccountNumber(customerInfo.getAccountNumber());
				transection.setTransectionThrough(theWay);
				transection.setMethod("withdraw");
				transection.setAmount(amount);
				transection.setAccountBalance(customerInfo.getAccountBalance());

				BankingSystemManagement.getInstance().insertTransectionList(transection);

				System.out.println("Your Account Balance : "+customerInfo.getAccountBalance());

				break; 
			}
			else
				System.out.println("insufficent money...");
		}
	}
	public void viewTransection(CustomerDetail customerInfo)
	{
		
		int accountNumber=customerInfo.getAccountNumber();
		System.out.println("-----------------------------------------------------------");
		System.out.println("Name : " +customerInfo.getCustomerName()+ "\tAccount.No :"+customerInfo.getAccountNumber());
		System.out.println("Via\t\tMethod\t\tAmount\t\tBalance");
		System.out.println("-----------------------------------------------------------");

		for(TransectionDetail transection:BankingSystemManagement.getInstance().getTransectionList())
		{
			if(accountNumber==transection.getAccountNumber())
				System.out.println(transection.getTransectionThrough()+"\t\t"+transection.getMethod()+"\t\t"+transection.getAmount()+"\t\t"+transection.getAccountBalance());
		}
		System.out.println("-----------------------------------------------------------");

	}
}
