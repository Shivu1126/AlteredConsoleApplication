package libraryManagement;

import java.util.Scanner;

public class ReturnTheBook 
{
	Scanner scanner = new Scanner(System.in);
	protected void returnBook()
	{
		System.out.println("Enter userId");
		int userId = scanner.nextInt();
		int userInOrNot = 0;
		for(int i=0;i<LibraryDataBase.getInstance().getUserInfo().size();i++)
		{
			if(userId == LibraryDataBase.getInstance().getUserInfo().get(i).userId)
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
			for(int j=0;j<LibraryDataBase.getInstance().getUserInfo().get(i).givingBooks.size();j++)
			{
				if(LibraryDataBase.getInstance().getUserInfo().get(i).givingBooks.get(j).userId == userId && LibraryDataBase.getInstance().getUserInfo().get(i).givingBooks.get(j).bookName.equals(returnBookName) )
				{
					if(LibraryDataBase.getInstance().getUserInfo().get(i).givingBooks.get(j).returnDate.equals(""))
					{
						System.out.println("Enter the return date");
						String bookReturnDate = scanner.next();
						LibraryDataBase.getInstance().getUserInfo().get(i).givingBooks.get(j).returnDate = bookReturnDate;
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
				if(LibraryDataBase.getInstance().getBookDetail().get(i).bookName.equals(returnBookName))
				{
					
					System.out.println("Book condition : ");
					System.out.println("1. Proper Return");
					System.out.println("2. Book damged");
					System.out.println("Press 1 or 2 ");
					int bookCondition = scanner.nextInt();				
					ReturnTheBook returnTheBook = new ReturnTheBook();
					returnTheBook.updateBookCondition(bookCondition,userId,returnBookName);
					
					LibraryDataBase.getInstance().getBookDetail().get(i).stock++;
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
				for(int j=0;j<LibraryDataBase.getInstance().getUserInfo().get(i).givingBooks.size();j++)
				{
					if(LibraryDataBase.getInstance().getUserInfo().get(i).givingBooks.get(j).userId == userId && LibraryDataBase.getInstance().getUserInfo().get(i).givingBooks.get(j).bookName.equals(returnBookName) )
					{
						LibraryDataBase.getInstance().getUserInfo().get(i).givingBooks.get(j).bookCondition = "Good";
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
				for(int j=0;j<LibraryDataBase.getInstance().getUserInfo().get(i).givingBooks.size();j++)
				{
					if(LibraryDataBase.getInstance().getUserInfo().get(i).givingBooks.get(j).userId == userId && LibraryDataBase.getInstance().getUserInfo().get(i).givingBooks.get(j).bookName.equals(returnBookName) )
					{
						LibraryDataBase.getInstance().getUserInfo().get(i).givingBooks.get(j).bookCondition = "Damaged. Fine ₹75";
						return;
					}
				}
			}
		}
		
	}
}
