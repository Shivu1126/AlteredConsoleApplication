package controller;

import java.util.Scanner;

import model.LibraryDataBase;

public class ReturnTheBook 
{
	private Scanner scanner = new Scanner(System.in);
	public void returnBook()
	{
		System.out.println("Enter userId");
		int userId = scanner.nextInt();
		int userInOrNot = 0;
		for(int i=0;i<LibraryDataBase.getInstance().getUserInfo().size();i++)
		{
			if(userId == LibraryDataBase.getInstance().getUserInfo().get(i).getUserId())
			{
				userInOrNot = 1;
				break;
			}
		}
		if(userInOrNot == 0)
		{
			System.out.println("User Id Not present.");
			return;
		}
		System.out.println("Enter book name..");
		String returnBookName = scanner.next();
		int stopLoop = 0;

		for(int i=0;i<LibraryDataBase.getInstance().getUserInfo().size();i++)
		{
			stopLoop = 0;
			for(int j=0;j<LibraryDataBase.getInstance().getUserInfo().get(i).getGivingBooks().size();j++)
			{
				if(LibraryDataBase.getInstance().getUserInfo().get(i).getGivingBooks().get(j).getUserId() == userId && LibraryDataBase.getInstance().getUserInfo().get(i).getGivingBooks().get(j).getBookName().equals(returnBookName) )
				{
					if(LibraryDataBase.getInstance().getUserInfo().get(i).getGivingBooks().get(j).getReturnDate().equals(""))
					{
						System.out.println("Enter the return date");
						String bookReturnDate = scanner.next();
						LibraryDataBase.getInstance().getUserInfo().get(i).getGivingBooks().get(j).setReturnDate(bookReturnDate);
						stopLoop = 1;
						break;
					}
				}
			}
			if(stopLoop == 1)
				break;
		}
		
		if(stopLoop == 0)
		{
			System.out.println("You not take this book");
			return;
		}
		
		else
		{
			for(int i=0;i<LibraryDataBase.getInstance().getBookDetail().size();i++)
			{
				if(LibraryDataBase.getInstance().getBookDetail().get(i).getBookName().equals(returnBookName))
				{
					
					System.out.println("Book condition : ");
					System.out.println("1. Proper Return");
					System.out.println("2. Book damged");
					System.out.println("Press 1 or 2 ");
					int bookCondition = scanner.nextInt();				
					ReturnTheBook returnTheBook = new ReturnTheBook();
					returnTheBook.updateBookCondition(bookCondition,userId,returnBookName);
					int updateStock = LibraryDataBase.getInstance().getBookDetail().get(i).getStock()+1;
					LibraryDataBase.getInstance().getBookDetail().get(i).setStock(updateStock);;
					System.out.println("Book returned successfully");
					break;
				}
			}
		}
	}
	
	private void updateBookCondition(int bookCondition, int userId, String returnBookName)
	{

		if(bookCondition==1)
		{
			System.out.println("No fine");
			for(int i=0;i<LibraryDataBase.getInstance().getUserInfo().size();i++)
			{
				for(int j=0;j<LibraryDataBase.getInstance().getUserInfo().get(i).getGivingBooks().size();j++)
				{
					if(LibraryDataBase.getInstance().getUserInfo().get(i).getGivingBooks().get(j).getUserId() == userId && LibraryDataBase.getInstance().getUserInfo().get(i).getGivingBooks().get(j).getBookName().equals(returnBookName) )
					{
						LibraryDataBase.getInstance().getUserInfo().get(i).getGivingBooks().get(j).setBookCondition("Good");
						return;
					}
				}
			}		
		}
		
		else
		{
			System.out.println("Book damaged.Pay ₹75 fine. ");
			for(int i=0;i<LibraryDataBase.getInstance().getUserInfo().size();i++)
			{
				for(int j=0;j<LibraryDataBase.getInstance().getUserInfo().get(i).getGivingBooks().size();j++)
				{
					if(LibraryDataBase.getInstance().getUserInfo().get(i).getGivingBooks().get(j).getUserId() == userId && LibraryDataBase.getInstance().getUserInfo().get(i).getGivingBooks().get(j).getBookName().equals(returnBookName) )
					{
						LibraryDataBase.getInstance().getUserInfo().get(i).getGivingBooks().get(j).setBookCondition("Damaged. Fine ₹75");
						return;
					}
				}
			}
		}
		
	}
}
