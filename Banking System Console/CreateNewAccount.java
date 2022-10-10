package bankingSystem;

import java.util.Scanner;

public class CreateNewAccount
{
	private Scanner scanner = new Scanner(System.in);
	protected void createNewAccount()
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
		String custPassword=scanner.next();
		while(true)
		{
			CustomerPasswordController checkValidOrNot = new CustomerPasswordController();
			
			if(checkValidOrNot.checkValidPassword(custPassword))
			{
				System.out.println("Your password is strong");
				break;
			}
			else
			{
				System.out.println("Your password is weak : ");
				System.out.println("Re-enter password");
				custPassword=scanner.next();
			}
		}
		int initialBalance = 500;
		CustomerDetail addDetails = new CustomerDetail();
		addDetails.setCustomerName(customerName);
		addDetails.setAccountNumber(accountNumber);
		addDetails.setAccountBalance(initialBalance);
		addDetails.setPassword(custPassword);
		
		BankingSystemManagement.getInstance().insertCustomerList(addDetails);
		
		System.out.println("Successfully created..");
		System.out.println("-------------------------------------");
		System.out.println("Your details.....");
		System.out.println("-------------------------------------");
		System.out.println("Name    : "+customerName);
		System.out.println("AccountNumber  : "+accountNumber);
		System.out.println("PassWord: "+custPassword);
		System.out.println("AccountBalance : "+initialBalance);
		System.out.println("-------------------------------------");
	}

}
