package libraryManagement;

import java.util.ArrayList;

public class LibraryManagementSystem {
	public static void main(String[] args) 
	{
		Library library = new Library();
		library.home();
	}

}

class Books
{
	String bookName;
	int bookId;
	String authorName;
	int stock;
	Books(String bookName, int bookId, String authorName, int stock)
	{
		this.bookName = bookName;
		this.bookId = bookId;	
		this.authorName = authorName;
		this.stock = stock;
	}
}
class Librarian
{
	int librarianId;
	String librarianName;
	String password;
	Librarian(int librarianId, String librarianName, String password) 
	{
		this.librarianId = librarianId;
		this.librarianName = librarianName;
		this.password = password;
	}
}
class UserInfo
{
	String userName;
	int userId;
	ArrayList<GivenBookDetails> givingBooks;
	public UserInfo(String userName, int userId, ArrayList<GivenBookDetails> givingBooks)
	{
		this.userName = userName;
		this.userId = userId;
		this.givingBooks = givingBooks;
	}
}

class GivenBookDetails
{
	int userId;
	String bookName;
	String assignDate;
	String returnDate;
	String bookCondition;
	public GivenBookDetails(int userId, String bookName, String assignDate, String returnDate, String bookCondition)
	{
		this.userId = userId;
		this.bookName = bookName;
		this.assignDate = assignDate;
		this.returnDate = returnDate;
		this.bookCondition = bookCondition;
	}
	
}