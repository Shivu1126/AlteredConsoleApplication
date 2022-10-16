package model;

import java.util.ArrayList;

public class MailDatabase {

	private static MailDatabase mailDb;
	private ArrayList<UserDetail> userDetail;
	
	MailDatabase() {
		userDetail = new ArrayList<UserDetail>();
	}
	public static MailDatabase getInstance()
	{
		if(mailDb==null)
		{
			mailDb = new MailDatabase();
		}
		return mailDb;	
	}
	
	public ArrayList<UserDetail> getUserDetail() {
		return userDetail;
	}

	public void insertUserDetail(UserDetail insertUser) {
		this.userDetail.add(insertUser);
	}

}
