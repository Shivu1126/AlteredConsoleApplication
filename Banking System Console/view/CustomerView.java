package view;
import java.util.InputMismatchException;
import java.util.Scanner;

import controller.*;
import model.*;

public class CustomerView extends TransectionController
{
	private Scanner scanner = new Scanner(System.in);
	protected void customerView(CustomerDetail customerInfo)
	{
		while(true)
		{

			int customerOption=0;
			try {
				System.out.println("Enter your option : ");
				System.out.println("1.View Transection");
				System.out.println("2.Withdraw");
				System.out.println("3.Deposite");
				System.out.println("4.Change Password");
				System.out.println("5.Logout");
				customerOption = scanner.nextInt();

				switch(customerOption)
				{
				case 1:
					viewTransection(customerInfo);
					break;
				case 2:
					withdrawAmount(customerInfo);
					break;
				case 3:
					depositeAmount(customerInfo);
					break;
				case 4:
					CustomerController passChange = new CustomerController();
					passChange.changePassword(customerInfo);
					break;
				case 5:
					System.out.println("Thank you "+customerInfo.getCustomerName());
					return;
				default:
					System.out.println("Enter valid input..");
					break;
				}
			} catch (InputMismatchException e) {
				//e.printStackTrace();
				System.err.println("Enter valid input");
				scanner.next();

			}
		}
	}
}
