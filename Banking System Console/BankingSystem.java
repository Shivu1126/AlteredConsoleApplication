package bankingSystem;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class BankingSystem {

	public static void main(String[] args) {
		HomePage banking = new HomePage();
		banking.homePage();
	}
}

class BankingSystemManagement
{
	private static BankingSystemManagement bankingDb;
	private ArrayList<CustomerDetail> customerList;
	private ArrayList<TransectionDetail> transectionList;
	BankingSystemManagement() {
		customerList=new ArrayList<CustomerDetail>();
		transectionList=new ArrayList<TransectionDetail>();
	}
	public static BankingSystemManagement getInstance()
	{
		if(bankingDb==null)
		{
			bankingDb = new BankingSystemManagement();
		}
		return bankingDb;
	}

	public ArrayList<CustomerDetail> getCustomerList() {
		return customerList;
	}

	public ArrayList<TransectionDetail> getTransectionList() {
		return transectionList;
	}

	public void insertCustomerList(CustomerDetail customerInfo) {
		this.customerList.add(customerInfo);
	}

	public void insertTransectionList(TransectionDetail transectionInfo) {
		this.transectionList.add(transectionInfo);
	}

}

class HomePage
{
	private Scanner scanner = new Scanner(System.in);
	protected void homePage(){

		CustomerDetail cd1 = new CustomerDetail();
		cd1.setCustomerName("Shiv");
		cd1.setAccountNumber(123);
		cd1.setAccountBalance(500);
		cd1.setPassword("Shiv123");
		BankingSystemManagement.getInstance().insertCustomerList(cd1);

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
					viewAllCustomers();
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
	}

	private void viewAllCustomers()
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

