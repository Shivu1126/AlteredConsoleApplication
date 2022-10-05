package bankingSystem;
import java.util.Scanner;

public class UsesForCustomer extends ViewTransection
{
	Scanner scanner = new Scanner(System.in);
	protected void usesForCustomer(CustomerDetail customerInfo)
	{
		while(true)
		{
			System.out.println("Enter your option : ");
			System.out.println("1.View Transection");
			System.out.println("2.Withdraw");
			System.out.println("3.Deposite");
			System.out.println("4.Change Password");
			System.out.println("5.Logout");
			int customerOption=scanner.nextInt();
			switch(customerOption)
			{
			case 1:
				viewTransection(customerInfo);
				break;
			case 2:
				WithdrawAmount withdraw = new WithdrawAmount();
				withdraw.withdrawAmount(customerInfo);
				break;
			case 3:
				DepositeAmount deposit = new DepositeAmount();
				deposit.depositeAmount(customerInfo);
				break;
			case 4:
				CustomerPasswordController passChange = new CustomerPasswordController();
				passChange.changePassword(customerInfo);
				break;
			case 5:
				System.out.println("Thank you "+customerInfo.getCustomerName());
				return;
			default:
				System.out.println("Enter valid input..");
				break;
			}
		}
	}
}
