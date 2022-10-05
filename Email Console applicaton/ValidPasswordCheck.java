package emailConsoleApplication;

public class ValidPasswordCheck 
{
	protected boolean validPassword(String password) 
	{
		if(password.length()<=6)
			return false;
		char arr[]=password.toCharArray();
		int cap=0,small=0,num=0;
		for(int i=0;i<password.length();i++)
		{
			if(arr[i]>='A' && arr[i]<='Z')
				cap++;
			if(arr[i]>='a' && arr[i]<='z')
				small++;
			if(arr[i]>='0' && arr[i]<='9')
				num++;
		}
		if(cap>0 && small>0 && num>0)
		{
			return true;
		}
		return false;
	}
}
