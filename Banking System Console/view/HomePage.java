package view;

import java.util.InputMismatchException;
import java.util.Scanner;

import controller.ManagerController;
import model.BankingSystemManagement;
import model.CustomerDetail;


public class HomePage {

	public static void main(String[] args) {
		HomePage banking = new HomePage();
		banking.homePage();
	}
	
	
	private void homePage(){
		
	    Scanner scanner = new Scanner(System.in);
		CustomerDetail customer = new CustomerDetail();
		customer.setCustomerName("Shiv");
		customer.setAccountNumber(123);
		customer.setAccountBalance(500);
		customer.setPassword("Shiv123");
		BankingSystemManagement.getInstance().insertCustomerList(customer);

		while(true) 
		{
			System.out.println("Your options...");
			System.out.println("1.Login         ");
			System.out.println("2.Create account");
			System.out.println("3.View customer details(Only for admin)");
			System.out.println("4.Exit");
			System.out.println("Enter your option");
			int optionsHome=0;
			try {
				optionsHome = scanner.nextInt();

				switch(optionsHome)
				{
				case 1:
					Login login = new Login();
					login.loginCustomer();
					break;
				case 2:
					CreateNewAccount createAcc = new CreateNewAccount();
					createAcc.createNewAccount();
					break;
				case 3:
					ManagerController managerController = new ManagerController();
					managerController.viewAllCustomers();
					break;
				case 4:
					return;
				default:
					System.out.println("Enter proper input...");
					break;	
				}	
			}
			catch (InputMismatchException e) {
				//e.printStackTrace();
				System.err.println("Invalid input");	
				scanner.next();
			}
			if(optionsHome==4)
				break;
		}
		scanner.close();
	}

}
