package view;

import java.util.Scanner;

import model.*;

class HomePageOfLibrary {
	public static void main(String[] args) 
	{
		StartHome library = new StartHome();
		library.home();
	}
}
class StartHome
{
	private Scanner scanner = new Scanner(System.in);
	protected void home()
	{
//		LibraryDataBase.getInstance().insertBookDetail(new Books("JAVA", 1,"BalaSan",3));
//		LibraryDataBase.getInstance().insertBookDetail(new Books("DBMS", 2,"SudhagarSan",3));
		
		Librarian addAdmin = new Librarian();
		addAdmin.setLibrarianId(11);
		addAdmin.setLibrarianName("Shiva");
		addAdmin.setPassword("Siva123");
		
		LibraryDataBase.getInstance().insertLibrarianDetail(addAdmin);
		
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
				if(LibraryDataBase.getInstance().getLibrarianDetail().get(i).getLibrarianId()==adminId)
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
				if(adminDetails.getLibrarianId()==adminId && LibraryDataBase.getInstance().getLibrarianDetail().get(i).getPassword().equals(password))
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
		AdminView admin = new AdminView();
		admin.adminPage();
	}
}
