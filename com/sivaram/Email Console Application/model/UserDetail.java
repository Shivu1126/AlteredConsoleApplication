package model;

import java.util.ArrayList;

public class UserDetail {
	private String name;
	private String password;
	private String emailID;
	private ArrayList<MessageDetail> sendMailList;
	private ArrayList<MessageDetail> receivedMailList;
	
	public String getName() {
		return name;
	}
	public String getPassword() {
		return password;
	}
	public String getEmailID() {
		return emailID;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}
	
	public ArrayList<MessageDetail> getSendMailList() {
		return sendMailList;
	}
	public void insertSendMailList(MessageDetail sendMail) {
		this.sendMailList.add(sendMail);
	}
	public void setSendMailList(ArrayList<MessageDetail> sendMailList) {
		this.sendMailList = sendMailList;
	}
	
	public ArrayList<MessageDetail> getReceivedMailList() {
		return receivedMailList;
	}
	public void insertReceivedMailList(MessageDetail receivedMail) {
		this.receivedMailList.add(receivedMail);
	}
	public void setReceivedMailList(ArrayList<MessageDetail> receivedMailList) {
		this.receivedMailList = receivedMailList;
	}

}
