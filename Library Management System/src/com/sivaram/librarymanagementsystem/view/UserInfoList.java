package view;

import java.util.ArrayList;

import model.GivenBookDetails;
import model.LibraryDataBase;

public class UserInfoList {
	
	
	public void userInfoMaintenance()
	{
		if(LibraryDataBase.getInstance().getUserInfo().isEmpty())
		{
			System.out.println("No Users..");
			return;
		}
		System.out.println("==============================================");
		for(int i=0;i<LibraryDataBase.getInstance().getUserInfo().size();i++)
		{
			System.out.println("User Name : "+LibraryDataBase.getInstance().getUserInfo().get(i).getUserName());
			System.out.println("User Id   : "+LibraryDataBase.getInstance().getUserInfo().get(i).getUserId());
			ArrayList<GivenBookDetails> userBook = LibraryDataBase.getInstance().getUserInfo().get(i).getGivingBooks();
			System.out.println("BookName\tAssignDate\tReturnDate\tBookCondition");
			System.out.println("----------------------------------------------");
			for(int j=0;j<userBook.size();j++)
			{
				System.out.println(userBook.get(j).getBookName()+"\t"+userBook.get(j).getAssignDate()+"\t"+userBook.get(j).getReturnDate()+"\t"+userBook.get(j).getBookCondition());
			}
			System.out.println("----------------------------------------------");
		}
		System.out.println("==============================================");
		
	}
}
