package view;

import java.util.InputMismatchException;
import java.util.Scanner;

import controller.CustomerController;
import model.*;

public class Login extends CustomerView
{
	private Scanner scanner = new Scanner(System.in);
	public void loginCustomer()
	{

		int accountNumber=0;
		while(true)
		{
			try {
				System.out.println("Enter account number : ");
				accountNumber = scanner.nextInt();
				break;
			}catch (InputMismatchException e) {
				//e.printStackTrace();
				System.err.println("Invalid account number");
			}
			scanner.next();
		}
		CustomerController accountNumberCheck = new CustomerController();
		CustomerDetail listCustomer;
		int customerIndex = accountNumberCheck.accountNumberChecking(accountNumber);
		if(customerIndex!=-1)
		{
			listCustomer = BankingSystemManagement.getInstance().getCustomerList().get(customerIndex);
		}
		else
		{
			System.out.println("Account number doesn't exist");
			return;
		}

		while(true)
		{
			int redo = 0;
			System.out.println("Enter your password");
			String password=scanner.next();
			if(password.equals(listCustomer.getPassword()))
			{
				customerView(listCustomer);		
				break;
			}
			else
			{
				System.err.println("Incorrect Password ");
				while(true)
				{	
					try {
						System.out.println("If you want go to home page...press 1..else press anyother number");
						redo = scanner.nextInt();
						break;
					}
					catch (InputMismatchException e) {
						System.err.println("Invalid input");
					}
					scanner.next();
				}
			}
			if(redo==1)
			{
				return;
			}
		}
	}
}
