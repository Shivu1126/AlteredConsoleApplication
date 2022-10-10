package bankingSystem;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Login extends UsesForCustomer
{
	private Scanner scanner = new Scanner(System.in);
	protected void loginCustomer()
	{
		int runLoop=1;

		while(runLoop==1)
		{

			int accountNumberInOrNot=0;			

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
			
			for(CustomerDetail listCustomer:BankingSystemManagement.getInstance().getCustomerList())
			{
				if(listCustomer.getAccountNumber()==accountNumber)
				{
					accountNumberInOrNot=1;
					break;
				}
			}

			if(accountNumberInOrNot==0)
			{
				System.out.println("Account number not exist...pls create account or re-login");
				break;
			}
			for(CustomerDetail listCustomer:BankingSystemManagement.getInstance().getCustomerList())
			{

				if(listCustomer.getAccountNumber()==accountNumber)
				{		
					while(true)
					{
						int loop = 0;
						System.out.println("Enter your password");
						String password=scanner.next();
						if(password.equals(listCustomer.getPassword()))
						{
							usesForCustomer(listCustomer);		
							break;
						}
						else
						{
							System.err.println("Incorrect Password ");
							while(true)
							{	
								try {
									System.out.println("If you want go to home page...press 1..else press anyother number");
									loop = scanner.nextInt();
									break;
								}
								catch (InputMismatchException e) {
									//e.printStackTrace();
									System.err.println("Invalid input");
								}
								scanner.next();
								
							}
						}
						if(loop==1)
						{
							return;
						}
					}
					break;
				}
			}			
			runLoop=0;
		}
	}
}
