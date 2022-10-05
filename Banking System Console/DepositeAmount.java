package bankingSystem;
import java.util.Scanner;

public class DepositeAmount 
{
	Scanner scanner = new Scanner(System.in);
	protected void depositeAmount(CustomerDetail customerInfo)
	{
		System.out.println("Enter which type of deposite");
		System.out.println("1.ATM");
		System.out.println("2.Gpay");
		System.out.println("3.Account Transfer");
		boolean k=true;
		String theWay="";
		int type=0;
		while(k)
		{	
			type=scanner.nextInt();			
			if(type==1)
			{theWay=Via.V1.getVia();k=false;}
			else if(type==2)
			{theWay=Via.V2.getVia();k=false;}
			else if(type==3)
			{theWay=Via.V4.getVia();k=false;}
			else
				System.out.println("Enter 1 to 3....");			
		}
		if(type==3)
		{
			int transferAccountNumberInOrNot=1;
			
			System.out.println("Enter Transfer Account Num : ");
			int transferAccountNumber=scanner.nextInt();
			for(CustomerDetail tranferAccount:BankingSystemManagement.getInstance().getCustomerList())
			{
				if(tranferAccount.getAccountNumber()==transferAccountNumber && transferAccountNumber!=customerInfo.getAccountNumber())
				{
					transferAccountNumberInOrNot=0;
					int tranferAmount=0;
					boolean forLoop=true;
					while(forLoop)
					{
						System.out.println("Enter transfer amount : ");
						tranferAmount=scanner.nextInt();
						if(customerInfo.getAccountBalance()>=tranferAmount)
						{
							tranferAccount.setAccountBalance(tranferAccount.getAccountBalance() + tranferAmount);								
							addTransection(transferAccountNumber, theWay, "Deposite", tranferAmount, tranferAccount.getAccountBalance());
							System.out.println("Money credited to '"+tranferAccount.getCustomerName()+"'");

							customerInfo.setAccountBalance(customerInfo.getAccountBalance() - tranferAmount);				
							addTransection(customerInfo.getAccountNumber(), theWay, "Transfer", tranferAmount, customerInfo.getAccountBalance());
							System.out.println("Your Account Balance : "+customerInfo.getAccountBalance());
							
							forLoop=false;
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
			System.out.println("Enter amount : ");
			int amount=scanner.nextInt();

			customerInfo.setAccountBalance(customerInfo.getAccountBalance() + amount);	
			addTransection(customerInfo.getAccountNumber(), theWay, "Deposite", amount, customerInfo.getAccountBalance());
			System.out.println("Money credited...");
		}

	}
	private void addTransection(int transferAccountNumber, String theWay, String string, int tranferAmount, int AccBalance)
	{
		TransectionDetail transection = new TransectionDetail();
		transection.setAccountNumber(transferAccountNumber);
		transection.setTransectionThrough(theWay);
		transection.setMethod("Deposite");
		transection.setAmount(tranferAmount);
		transection.setAccountBalance(AccBalance);

		BankingSystemManagement.getInstance().insertTransectionList(transection);
	}
}
