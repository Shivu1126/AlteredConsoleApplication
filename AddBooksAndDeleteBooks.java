package libraryManagement;

import java.util.Scanner;

class AddBooksAndDeleteBooks {
	Scanner scanner=new Scanner(System.in);
	
	protected void addBook()
	{
		System.out.println("Enter new Book name..");
		String newBookName = scanner.next();
		
		for(int i=0;i<LibraryDataBase.getInstance().getBookDetail().size();i++)
		{
			if(newBookName.equals(LibraryDataBase.getInstance().getBookDetail().get(i).bookName))	//increment the stock when already present book.
			{
				LibraryDataBase.getInstance().getBookDetail().get(i).stock++;
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
		
		LibraryDataBase.getInstance().insertBookDetail(new Books(newBookName, newBookId, newAuthorName, newBookStock));
		System.out.println("New book added in library");
	}
	
	protected void deleteBook()
	{
		System.out.println("Enter deleted book id");
		int deletedBookId;
		Books deletedBookDetail = null;
		while(true)
		{
			deletedBookId = scanner.nextInt();
			int j=0;
			for(int i=0;i<LibraryDataBase.getInstance().getBookDetail().size();i++)
			{
				if(LibraryDataBase.getInstance().getBookDetail().get(i).bookId==deletedBookId && LibraryDataBase.getInstance().getBookDetail().get(i).stock!=0)
				{
					deletedBookDetail = LibraryDataBase.getInstance().getBookDetail().get(i);
					j=1;
					break;
				}					
			}
			if(j==1)
				break;
			else
				System.err.println("Book not present..");
			System.out.println("If you want continue...press 1...else press anyother number");
			int stopThis = scanner.nextInt();
			if(stopThis!=1)
				return;
		}
		deletedBookDetail.stock = 0;
		System.out.println("Book is deleted from library...");
	}
}
