package bankingSystem;

import java.util.Scanner;

public class Login extends UsesForCustomer
{
	Scanner scanner = new Scanner(System.in);
	protected void loginCustomer()
	{
		int runLoop=1;
		repeat:
			while(runLoop==1)
			{

				int accountNumberInOrNot=0;			

				System.out.println("Enter account number : ");
				int accountNumber=scanner.nextInt();
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
					break repeat;
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
								System.out.println("If you want go to home page...press 1..else press anyother number");
								loop = scanner.nextInt();
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
