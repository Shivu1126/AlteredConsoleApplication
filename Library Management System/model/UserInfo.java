package model;

import java.util.ArrayList;

public class UserInfo
{
	private String userName;
	private int userId;
	private ArrayList<GivenBookDetails> givingBooks;

	public String getUserName() {
		return userName;
	}
	public int getUserId() {
		return userId;
	}
	public ArrayList<GivenBookDetails> getGivingBooks() {
		return givingBooks;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public void setGivingBooks(ArrayList<GivenBookDetails> givingBooks) {
		this.givingBooks = givingBooks;
	}
	
}
