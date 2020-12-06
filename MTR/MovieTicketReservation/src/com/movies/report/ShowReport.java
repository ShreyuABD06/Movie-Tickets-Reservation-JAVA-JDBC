package com.movies.report;

import java.sql.Date;
import java.sql.Time;

public class ShowReport {
	private int rId;
	private String customerName;
	private String movieName;
	private String theatreName;
	private String location;
	private Date date;
	private Time showTime;
	private int noOfTickets;
	private double cost;

	public int getrId() {
		return rId;
	}

	public void setrId(int rId) {
		this.rId = rId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public String getTheatreName() {
		return theatreName;
	}

	public void setTheatreName(String theatreName) {
		this.theatreName = theatreName;
	}
	

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Time getShowTime() {
		return showTime;
	}

	public void setShowTime(Time showTime) {
		this.showTime = showTime;
	}

	public int getNoOfTickets() {
		return noOfTickets;
	}

	public void setNoOfTickets(int noOfTickets) {
		this.noOfTickets = noOfTickets;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	@Override
	public String toString() {
		return "ShowReport [rId=" + rId + ", customerName=" + customerName + ", movieName=" + movieName
				+ ", theatreName=" + theatreName + ", location=" + location + ", date=" + date + ", showTime="
				+ showTime + ", noOfTickets=" + noOfTickets + ", cost=" + cost + "]";
	}

}
