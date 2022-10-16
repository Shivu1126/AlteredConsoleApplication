package model;

public class MessageDetail {
	private String from;
	private String to;
	private String message;
	private int messageId;
	private String status;
	
	public String getFrom() {
		return from;
	}
	public String getTo() {
		return to;
	}
	public String getMessage() {
		return message;
	}
	public int getMessageId() {
		return messageId;
	}
	public String getStatus() {
		return status;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}
	public void setStatus(String status) {
		this.status = status;
	}

}
