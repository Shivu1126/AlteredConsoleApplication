package bankingSystem;
import java.util.Scanner;

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

public class WithdrawAmount 
{
	Scanner scanner = new Scanner(System.in);	
	protected void withdrawAmount(CustomerDetail customerInfo) 
	{
		System.out.println("Enter which type of withdraw");
		System.out.println("1.ATM");
		System.out.println("2.Gpay");
		System.out.println("3.Bank");
		int typeOfTransection=scanner.nextInt();
		if(typeOfTransection>3 && typeOfTransection<1)
		{
			System.err.println("Enter valid inpur..");
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
		boolean loop=true;
		while(loop)
		{
			System.out.println("Enter amount : ");
			int amount=scanner.nextInt();
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

				loop=false; 
			}
			else
				System.out.println("insufficent money...");
		}
	}
}
