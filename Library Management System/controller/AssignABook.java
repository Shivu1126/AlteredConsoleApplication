package controller;

import java.util.ArrayList;
import java.util.Scanner;

import model.*;

public class AssignABook 
{
	private Scanner scanner = new Scanner(System.in);
	public void assignTheBookToUser()
	{
		System.out.println("Enter the book name..");
		String assignBook;
		assignBook = scanner.next();
		int indexBooks = checkBookInOrNot(assignBook);
		if( indexBooks!=-1 && LibraryDataBase.getInstance().getBookDetail().get(indexBooks).getStock() > 0 )
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
			if(LibraryDataBase.getInstance().getBookDetail().get(i).getBookName().equals(assignBook))
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
			if(LibraryDataBase.getInstance().getUserInfo().get(i).getUserId() == newUserId)
			{
				
				if(bookAlreadyTaken(LibraryDataBase.getInstance().getUserInfo().get(i), givenBook))
				{
					System.out.println("You already take this book and please return the book.");
					return;
				}
				
				System.out.println("Enter book assigned date(dd-mm-yyyy)");
				assignDate = scanner.next();
				
				setBookDetailsInList(newUserId, givenBook, assignDate, "", "", i);
				
				givenBook.setStock(givenBook.getStock() - 1);
				System.out.println("Book assigned to "+LibraryDataBase.getInstance().getUserInfo().get(i).getUserName());
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
			//GivenBookDetails newGivenBook = new GivenBookDetails(0, "", "", "", "");
			
			ArrayList<GivenBookDetails> tempList = new ArrayList<>();
			
		//	tempList.add(newGivenBook);
			UserInfo userDetail = new UserInfo();
			userDetail.setUserId(userId);
			userDetail.setUserName(newUserName);
			userDetail.setGivingBooks(tempList);
			
			LibraryDataBase.getInstance().insertUserInfo(userDetail);
			System.out.println("New User added...");
			
			System.out.println("Enter book assigned date(dd-mm-yyyy)");
			assignDate = scanner.next();
			
			setBookDetailsInList(userId, givenBook, assignDate, "", "", userId-1);
			
			//LibraryDataBase.getInstance().getUserInfo().get(userId-1).givingBooks.add(new GivenBookDetails(userId, givenBook.getBookName(), assignDate, "", ""));
			givenBook.setStock(givenBook.getStock() - 1);
			System.out.println("Book assigned to "+LibraryDataBase.getInstance().getUserInfo().get(userId-1).getUserName());
		
		}
		
	}
	
	private void setBookDetailsInList(int newUserId, Books givenBook, String assignDate, String returnDate, String condition, int userIndex)
	{
		GivenBookDetails givenBookDetail = new GivenBookDetails();
		givenBookDetail.setUserId(newUserId);
		givenBookDetail.setBookName(givenBook.getBookName());
		givenBookDetail.setAssignDate(assignDate);
		givenBookDetail.setReturnDate(returnDate);
		givenBookDetail.setBookCondition(condition);
		
		LibraryDataBase.getInstance().getUserInfo().get(userIndex).getGivingBooks().add(givenBookDetail);
	}

	private boolean bookAlreadyTaken(UserInfo userBookDetail, Books givenBook) {
		
		for(int i = 0;i<userBookDetail.getGivingBooks().size();i++)
		{
			if(userBookDetail.getGivingBooks().get(i).getBookName().equals(givenBook.getBookName()) && userBookDetail.getGivingBooks().get(i).getReturnDate().equals(""))
			{
				return true;
			}
		}
		return false;		
	}
	
	
}
