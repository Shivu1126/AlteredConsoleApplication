package view;

import java.util.Scanner;
import controller.*;
public class AdminView {
	public Scanner scanner = new Scanner(System.in);
	public void adminPage()
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
					BookController add = new BookController();
					add.addBook();
					break;
				case 2:
					BookController delete = new BookController();
					delete.deleteBook();
					break;
				case 3:
					UserInfoList userList = new UserInfoList();
					userList.userInfoMaintenance();
					break;
				case 4:
					BookController showBooks = new BookController();
					showBooks.showBooksDetail();
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
}
