package model;

import java.util.ArrayList;

public class BankingSystemManagement {
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
