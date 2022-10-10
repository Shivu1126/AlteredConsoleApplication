package libraryManagement;

import java.util.ArrayList;
import java.util.Scanner;

class Library {
	private Scanner scanner = new Scanner(System.in);
	protected void home()
	{
		LibraryDataBase.getInstance().insertBookDetail(new Books("JAVA", 1,"BalaSan",3));
		LibraryDataBase.getInstance().insertBookDetail(new Books("DBMS", 2,"SudhagarSan",3));
		
		LibraryDataBase.getInstance().insertLibrarianDetail(new Librarian(11, "Shiv", "Siva123"));
		
		System.out.println("__Home Page__");
		while(true)
		{
			System.out.println("1. Admin Login");
			System.out.println("2. Exit");
			System.out.println("Enter choice..");
			int choiceHome = scanner.nextInt();
			switch(choiceHome)
			{
				case 1:
					login();	
					break;
				case 2:
					System.out.println("Thank you admin");
					return;
				default:
					System.out.println("Enter proper input..");
			}
		}
	}
	private void login()
	{
		System.out.println("Enter admin id");
		int adminId;
		Librarian adminDetails = null;
		while(true)
		{
			adminId = scanner.nextInt();
			int check=0;
			for(int i=0;i<LibraryDataBase.getInstance().getLibrarianDetail().size();i++)
			{
				if(LibraryDataBase.getInstance().getLibrarianDetail().get(i).librarianId==adminId)
				{
					adminDetails = LibraryDataBase.getInstance().getLibrarianDetail().get(i);
					check=1;
					break;
				}
			}
			if(check==1)
				break;
			else
				System.out.println("Admin id invalid..");
			System.out.println("\nIf you want go to home page please press 1..");
			int returnToHome=scanner.nextInt();
			if(returnToHome==1)
			{
				return;
			}
		}
		
		System.out.println("Enter password");
		String password;
		while(true)
		{
			password = scanner.next();
			int check=0;
			for(int i=0;i<LibraryDataBase.getInstance().getLibrarianDetail().size();i++)
			{
				if(adminDetails.librarianId==adminId && LibraryDataBase.getInstance().getLibrarianDetail().get(i).password.equals(password))
				{
					check=1;
					break;
				}
			}
			if(check==1)
				break;
			else
				System.out.println("Password invalid..");
			System.out.println("\nIf you want go to home page please press 1..");
			int returnToHome=scanner.nextInt();
			if(returnToHome==1)
			{
				return;
			}
		}
		adminPage();
	}
	private void adminPage()
	{
		System.out.println("Admin options...");
		while(true)
		{
			System.out.println("1. Add Book");
			System.out.println("2. Delete Book");
			System.out.println("3. User info maintenace");
			System.out.println("4. Showing Books List");
			System.out.println("5. Assign a Book");
			System.out.println("6. Return a Book");
			System.out.println("7. Exit");
			System.out.println("Enter your option...");
			int choiceAdminPage = scanner.nextInt();
			switch(choiceAdminPage)
			{
				case 1:
					AddBooksAndDeleteBooks add = new AddBooksAndDeleteBooks();
					add.addBook();
					break;
				case 2:
					AddBooksAndDeleteBooks delete = new AddBooksAndDeleteBooks();
					delete.deleteBook();
					break;
				case 3:
					userInfoMaintenance();
					break;
				case 4:
					showBooksDetail();
					break;
				case 5:
					AssignABook assignABook = new AssignABook();
					assignABook.assignTheBookToUser();
					break;
				case 6:
					ReturnTheBook returnTheBook = new ReturnTheBook();
					returnTheBook.returnBook();
					break;
				case 7:
					return;
				default:
					System.out.println("Enter proper input...");
					break;
			}
		}
	}
	
	private void showBooksDetail()
	{
		System.out.println("__________________Books Details______________________");
		System.out.println("-----------------------------------------------------");
		System.out.println("BookId\tBookName\tAuthorName\tBookStock");
		System.out.println("-----------------------------------------------------");
		for(int i=0;i<LibraryDataBase.getInstance().getBookDetail().size();i++)
		{
			if(LibraryDataBase.getInstance().getBookDetail().get(i).stock>0)
			{
				System.out.println(LibraryDataBase.getInstance().getBookDetail().get(i).bookId+"\t"+LibraryDataBase.getInstance().getBookDetail().get(i).bookName+"\t\t"
							+LibraryDataBase.getInstance().getBookDetail().get(i).authorName+"\t\t"+LibraryDataBase.getInstance().getBookDetail().get(i).stock);
			}
		}
		System.out.println("-----------------------------------------------------");

	}
	
	private void userInfoMaintenance()
	{
		if(LibraryDataBase.getInstance().getUserInfo().isEmpty())
		{
			System.out.println("No Users..");
			return;
		}
		System.out.println("==============================================");
		for(int i=0;i<LibraryDataBase.getInstance().getUserInfo().size();i++)
		{
			System.out.println("User Name : "+LibraryDataBase.getInstance().getUserInfo().get(i).userName);
			System.out.println("User Id   : "+LibraryDataBase.getInstance().getUserInfo().get(i).userId);
			ArrayList<GivenBookDetails> userBook = LibraryDataBase.getInstance().getUserInfo().get(i).givingBooks;
			System.out.println("BookName\tAssignDate\tReturnDate\tBookCondition");
			System.out.println("----------------------------------------------");
			for(int j=0;j<userBook.size();j++)
			{
				System.out.println(userBook.get(j).bookName+"\t"+userBook.get(j).assignDate+"\t"+userBook.get(j).returnDate+"\t"+userBook.get(j).bookCondition);
			}
			System.out.println("----------------------------------------------");
		}
		System.out.println("==============================================");
		
	}
}
