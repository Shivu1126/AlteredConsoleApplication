package model;

public class GivenBookDetails
{
	private int userId;
	private String bookName;
	private String assignDate;
	private String returnDate;
	private String bookCondition;

	public int getUserId() {
		return userId;
	}
	public String getBookName() {
		return bookName;
	}
	public String getAssignDate() {
		return assignDate;
	}
	public String getReturnDate() {
		return returnDate;
	}
	public String getBookCondition() {
		return bookCondition;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public void setAssignDate(String assignDate) {
		this.assignDate = assignDate;
	}
	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}
	public void setBookCondition(String bookCondition) {
		this.bookCondition = bookCondition;
	}
	
}