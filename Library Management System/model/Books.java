package model;

public class Books
{
	private String bookName;
	private int bookId;
	private String authorName;
	private	int stock;
	
	public String getBookName() {
		return bookName;
	}
	public int getBookId() {
		return bookId;
	}
	public String getAuthorName() {
		return authorName;
	}
	public int getStock() {
		return stock;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	
	
}
