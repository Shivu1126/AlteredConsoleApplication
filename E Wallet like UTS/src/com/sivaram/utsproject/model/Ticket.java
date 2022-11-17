package com.sivaram.utsproject.model;

public class Ticket 	
{
	private String passengerName;
	private String fromDepot;
	private String toDepot;
	private int ticketCount;
	private double ticketPrice;
	private double totalPrice;
	private String startTime;
	private String endTime;
	public String getPassengerName() {
		return passengerName;
	}
	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}
	public String getFromDepot() {
		return fromDepot;
	}
	public void setFromDepot(String fromDepot) {
		this.fromDepot = fromDepot;
	}
	public String getToDepot() {
		return toDepot;
	}
	public void setToDepot(String toDepot) {
		this.toDepot = toDepot;
	}
	public int getTicketCount() {
		return ticketCount;
	}
	public void setTicketCount(int ticketCount) {
		this.ticketCount = ticketCount;
	}
	public double getTicketPrice() {
		return ticketPrice;
	}
	public void setTicketPrice(double ticketPrice) {
		this.ticketPrice = ticketPrice;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String formattedDate) {
		this.startTime = formattedDate;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
}
