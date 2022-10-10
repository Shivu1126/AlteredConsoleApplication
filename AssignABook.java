package libraryManagement;

import java.util.ArrayList;
import java.util.Scanner;

public class AssignABook 
{
	Scanner scanner = new Scanner(System.in);
	protected void assignTheBookToUser()
	{
		System.out.println("Enter the book name..");
		String assignBook;
		assignBook = scanner.next();
		int indexBooks = checkBookInOrNot(assignBook);
		if( indexBooks!=-1 && LibraryDataBase.getInstance().getBookDetail().get(indexBooks).stock > 0 )
		{
			Books givenBook = LibraryDataBase.getInstance().getBookDetail().get(indexBooks);
			addBookTOUser(givenBook);
		}
		else
		{
			System.out.println("Sorry.This book is not present in this library..");
		}
			
	}
	
	private int checkBookInOrNot(String assignBook) 
	{
		for(int i=0;i<LibraryDataBase.getInstance().getBookDetail().size();i++)
		{
			if(LibraryDataBase.getInstance().getBookDetail().get(i).bookName.equals(assignBook))
			{
				return i;
			}
		}
		
		return -1;
	}
	
	private void addBookTOUser( Books givenBook)
	{
		System.out.println("Enter user id");
		int newUserId = scanner.nextInt();
		String assignDate;
		for(int i=0;i<LibraryDataBase.getInstance().getUserInfo().size();i++)
		{
			if(LibraryDataBase.getInstance().getUserInfo().get(i).userId == newUserId)
			{
				
				if(bookAlreadyTaken(LibraryDataBase.getInstance().getUserInfo().get(i), givenBook))
				{
					System.out.println("You already take this book and please return the book.");
					return;
				}
				
				System.out.println("Enter book assigned date(dd-mm-yyyy)");
				assignDate = scanner.next();
				LibraryDataBase.getInstance().getUserInfo().get(i).givingBooks.add(new GivenBookDetails(newUserId, givenBook.bookName, assignDate, "", ""));
				givenBook.stock--;
				System.out.println("Book assigned to "+LibraryDataBase.getInstance().getUserInfo().get(i).userName);
				return;
			}
		}
		System.out.println("Your id is not present...");
		System.out.println("If you want add your details to library..press 1..else press anyother number");
		int createUser = scanner.nextInt();
		if(createUser==1)
		{
			System.out.println("Enter user name");
			String newUserName = scanner.next();
			int userId = LibraryDataBase.getInstance().getUserInfo().size()+1;
			System.out.println("Your user id : "+userId);
			GivenBookDetails newGivenBook = new GivenBookDetails(0, "", "", "", "");
			ArrayList<GivenBookDetails> tempList = new ArrayList<>();
			
			tempList.add(newGivenBook);
			
			LibraryDataBase.getInstance().insertUserInfo(new UserInfo(newUserName, userId, tempList));
			System.out.println("New User added...");
			
			System.out.println("Enter book assigned date(dd-mm-yyyy)");
			assignDate = scanner.next();
			
			LibraryDataBase.getInstance().getUserInfo().get(userId-1).givingBooks.set(0,new GivenBookDetails(userId, givenBook.bookName, assignDate, "", ""));
			givenBook.stock--;
			System.out.println("Book assigned to "+LibraryDataBase.getInstance().getUserInfo().get(userId-1).userName);
		
		}
		
	}

	private boolean bookAlreadyTaken(UserInfo userBookDetail, Books givenBook) {
		
		for(int i = 0;i<userBookDetail.givingBooks.size();i++)
		{
			if(userBookDetail.givingBooks.get(i).bookName.equals(givenBook.bookName) && userBookDetail.givingBooks.get(i).returnDate.equals(""))
			{
				return true;
			}
		}
		return false;		
	}
	
	
}
