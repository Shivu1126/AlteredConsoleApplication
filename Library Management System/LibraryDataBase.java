package libraryManagement;

import java.util.ArrayList;

public class LibraryDataBase
{
	private static LibraryDataBase libraryDb;
	private ArrayList<Books> bookDetail;
	private ArrayList<Librarian> librarianDetail;
	private ArrayList<UserInfo> userInfo;
	LibraryDataBase()
	{
		bookDetail = new ArrayList<>();
		librarianDetail = new ArrayList<>();
		userInfo = new ArrayList<>();
	}
	public static LibraryDataBase getInstance()
	{
		if(libraryDb==null)
		{
			libraryDb = new LibraryDataBase();
		}
		return libraryDb;
	}
	
	public ArrayList<Books> getBookDetail() {
		return bookDetail;
	}
	public void insertBookDetail(Books insertBook) {
		this.bookDetail.add(insertBook);
	}
	
	public ArrayList<Librarian> getLibrarianDetail() {
		return librarianDetail;
	}
	public void insertLibrarianDetail(Librarian insertLibrarian) {
		this.librarianDetail.add(insertLibrarian);
	}
	public ArrayList<UserInfo> getUserInfo() {
		return userInfo;
	}
	public void insertUserInfo(UserInfo insertUser) {
		this.userInfo.add(insertUser);
	}
	
	
}
