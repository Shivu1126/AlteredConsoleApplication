package model;

public class Flight {
	
	private int flightId;
	private String flightName;
	private int bookingSeats;
	private int availbaleSeats;
	private String travelFromAndTo;
	private String dataAndTime;
	private int ticketPrice;
	public int getFlightId() {
		return flightId;
	}
	public String getFlightName() {
		return flightName;
	}
	public int getBookingSeats() {
		return bookingSeats;
	}
	public int getAvailbaleSeats() {
		return availbaleSeats;
	}
	public void setAvailbaleSeats(int availbaleSeats) {
		this.availbaleSeats = availbaleSeats;
	}
	public String getTravelFromAndTo() {
		return travelFromAndTo;
	}
	public String getDataAndTime() {
		return dataAndTime;
	}
	public void setFlightId(int flightId) {
		this.flightId = flightId;
	}
	public void setFlightName(String flightName) {
		this.flightName = flightName;
	}
	public void setBookingSeats(int bookingSeats) {
		this.bookingSeats = bookingSeats;
	}
	public void setTravelFromAndTo(String travelFromAndTo) {
		this.travelFromAndTo = travelFromAndTo;
	}
	public void setDataAndTime(String dataAndTime) {
		this.dataAndTime = dataAndTime;
	}
	public int getTicketPrice() {
		return ticketPrice;
	}
	public void setTicketPrice(int ticketPrice) {
		this.ticketPrice = ticketPrice;
	}
	
}
