package com.movies.domain;

public class CustomerBookings {
	private int bookId;
	private int showId;
	private int customerId;
	private int noOfTickets;

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public int getShowId() {
		return showId;
	}

	public void setShowId(int showId) {
		this.showId = showId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public int getNoOfTickets() {
		return noOfTickets;
	}

	public void setNoOfTickets(int noOfTickets) {
		this.noOfTickets = noOfTickets;
	}

	@Override
	public String toString() {
		return "CustomerBookings [bookId=" + bookId + ", showId=" + showId + ", customerId=" + customerId
				+ ", noOfTickets=" + noOfTickets + "]";
	}

}
