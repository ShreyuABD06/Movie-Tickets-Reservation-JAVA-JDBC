package com.movies.domain;

import java.sql.Date;
import java.sql.Time;

public class ShowDetails {

	private int showId;
	private String showName;
	private int movieId;
	private int theatreId;
	private Date date;
	private Time showTime;
	private double ticketCost;
	private int seatsAvailable;

	public int getShowId() {
		return showId;
	}

	public void setShowId(int showId) {
		this.showId = showId;
	}

	public String getShowName() {
		return showName;
	}

	public void setShowName(String showName) {
		this.showName = showName;
	}

	public int getMovieId() {
		return movieId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}

	public int getTheatreId() {
		return theatreId;
	}

	public void setTheatreId(int theatreId) {
		this.theatreId = theatreId;
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

	public double getTicketCost() {
		return ticketCost;
	}

	public void setTicketCost(double ticketCost) {
		this.ticketCost = ticketCost;
	}

	public int getSeatsAvailable() {
		return seatsAvailable;
	}

	public void setSeatsAvailable(int seatsAvailable) {
		this.seatsAvailable = seatsAvailable;
	}

	@Override
	public String toString() {
		return "ShowDetails [showId=" + showId + ", showName=" + showName + ", movieId=" + movieId + ", theatreId="
				+ theatreId + ", date=" + date + ", showTime=" + showTime + ", ticketCost=" + ticketCost
				+ ", seatsAvailable=" + seatsAvailable + "]";
	}

}
