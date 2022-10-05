package bankingSystem;

public class ViewTransection {
	protected void viewTransection(CustomerDetail customerInfo)
	{
		
		int accountNumber=customerInfo.getAccountNumber();
		System.out.println("-----------------------------------------------------------");
		System.out.println("Name : " +customerInfo.getCustomerName()+ "\tAccount.No :"+customerInfo.getAccountNumber());
		System.out.println("Via\t\tMethod\t\tAmount\t\tBalance");
		System.out.println("-----------------------------------------------------------");

		for(TransectionDetail transection:BankingSystemManagement.getInstance().getTransectionList())
		{
			if(accountNumber==transection.getAccountNumber())
				System.out.println(transection.getTransectionThrough()+"\t\t"+transection.getMethod()+"\t\t"+transection.getAmount()+"\t\t"+transection.getAccountBalance());
		}
		System.out.println("-----------------------------------------------------------");

	}
}
