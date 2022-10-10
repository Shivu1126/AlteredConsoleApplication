package bankingSystem;

import java.util.Scanner;

public class CustomerPasswordController
{
	private Scanner scanner = new Scanner(System.in);
	protected boolean changePassword(CustomerDetail customerInfo)
	{	
		System.out.println("Enter new password :");
		String newPassword=scanner.next();
		while(true)
		{
			if(checkValidPassword(newPassword))
			{
				customerInfo.setPassword(newPassword);
				System.out.println("Your password accepted");			
				return true;			
			}
			else
			{
				System.out.println("Re-enter password");
				newPassword=scanner.next();
			}
		}
	}
	
	protected boolean checkValidPassword(String newPassword)
	{
		if(newPassword.length()<=6)
			return false;
		char arr[]=newPassword.toCharArray();
		int upper=0,lower=0,numeric=0;
		for(int i=0;i<newPassword.length();i++)
		{
			if(arr[i]>='A' && arr[i]<='Z')
				upper++;
			if(arr[i]>='a' && arr[i]<='z')
				lower++;
			if(arr[i]>='0' && arr[i]<='9')
				numeric++;
		}
		if(upper>0 && lower>0 && numeric>0)
		{
			return true;
		}
		return false;
	}
}
