package controller;

import java.util.Scanner;

import model.*;

public class BookController {
	private Scanner scanner=new Scanner(System.in);
	
	public void addBook()
	{
		System.out.println("Enter new Book name..");
		String newBookName = scanner.next();
		
		for(int i=0;i<LibraryDataBase.getInstance().getBookDetail().size();i++)
		{
			if(newBookName.equals(LibraryDataBase.getInstance().getBookDetail().get(i).getBookName()))	//increment the stock when already present book.
			{
				int currentStock = LibraryDataBase.getInstance().getBookDetail().get(i).getStock();
				LibraryDataBase.getInstance().getBookDetail().get(i).setStock(currentStock+1);;
				System.out.println("Book Added");
				return;
			}
		}
		System.out.println("Enter author name");
		String newAuthorName = scanner.next();
		int newBookId = LibraryDataBase.getInstance().getBookDetail().size()+1;
		System.out.println("Enter how many books");
		int newBookStock = 0;
		while(true)
		{
			newBookStock = scanner.nextInt();
			if(newBookStock<1)				//Stock must be greater then 0
			{
				System.err.println("Enter proper input");
				System.out.println("please enter how many books");
			}
			else
				break;
		}
		
		Books addBookInList =  new Books();
		addBookInList.setBookName(newBookName);
		addBookInList.setAuthorName(newAuthorName);
		addBookInList.setBookId(newBookId);
		addBookInList.setStock(newBookStock);
		
		
		LibraryDataBase.getInstance().insertBookDetail(addBookInList);
		System.out.println("New book added in library");
	}
	
	public void deleteBook()
	{
		System.out.println("Enter deleted book id");
		int deletedBookId;
		Books deletedBookDetail = null;
		while(true)
		{
			deletedBookId = scanner.nextInt();
			int presentOrNot=0;
			for(int i=0;i<LibraryDataBase.getInstance().getBookDetail().size();i++)
			{
				if(LibraryDataBase.getInstance().getBookDetail().get(i).getBookId()==deletedBookId && LibraryDataBase.getInstance().getBookDetail().get(i).getStock()!=0)
				{
					deletedBookDetail = LibraryDataBase.getInstance().getBookDetail().get(i);
					presentOrNot=1;
					break;
				}					
			}
			if(presentOrNot==1)
				break;
			else
				System.err.println("Book not present..");
			System.out.println("If you want continue...press 1...else press anyother number");
			int stopThis = scanner.nextInt();
			if(stopThis!=1)
				return;
		}
		deletedBookDetail.setStock(0);
		System.out.println("Book is deleted from library...");
	}
	
	public void showBooksDetail()
	{
		System.out.println("__________________Books Details______________________");
		System.out.println("-----------------------------------------------------");
		System.out.println("BookId\tBookName\tAuthorName\tBookStock");
		System.out.println("-----------------------------------------------------");
		for(int i=0;i<LibraryDataBase.getInstance().getBookDetail().size();i++)
		{
			if(LibraryDataBase.getInstance().getBookDetail().get(i).getStock()>0)
			{
				System.out.println(LibraryDataBase.getInstance().getBookDetail().get(i).getBookId()+"\t"+LibraryDataBase.getInstance().getBookDetail().get(i).getBookName()+"\t\t"
							+LibraryDataBase.getInstance().getBookDetail().get(i).getAuthorName()+"\t\t"+LibraryDataBase.getInstance().getBookDetail().get(i).getStock());
			}
		}
		System.out.println("-----------------------------------------------------");

	}
}
