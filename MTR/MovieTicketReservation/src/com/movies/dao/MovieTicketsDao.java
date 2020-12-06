package com.movies.dao;

import java.util.List;

import com.movies.domain.Customer;
import com.movies.domain.CustomerBookings;
import com.movies.domain.Movie;
import com.movies.domain.ShowDetails;
import com.movies.domain.Theatre;
import com.movies.report.ShowReport;

public interface MovieTicketsDao {
	public String addTheatre(Theatre theatre);   
	public List<Theatre> getTheatres(); 
	public Theatre updateTheatre(Theatre updatedValue); 
	public String addMovie(Movie movie); 
	public Movie updateMovie(Movie movie); 
	public List<Movie> getAllMovies(); 
	public String addShow(ShowDetails show);
	public ShowDetails updateShow(ShowDetails show);  
	public List<ShowDetails> getAllShows();
	public String registerCustomer(Customer customer); 
	public Customer updateCustomer(Customer customer);   
	public List<Customer> getAllCustomers();
	public List<Customer> getAllCustomers(int showId);
	public boolean registerCustomerForShow(int customerId,int showId,int noOfTickets); 
	public List<ShowReport> getAllShowDetails();
	public Theatre getTheatreById(int Id);
	public Movie getMovieById(int Id);
	public ShowDetails getShowById(int Id);
	public ShowDetails findShow(int movieId, int theatreId);
	public Customer getCustomerById(int id);
//	public List<ShowReport> getAllShowDetails(int id);
	public int deleteFromBookings(int id1, int shid1);
//	public int deleteFromBookings(int shid1);
	public double getTicketCostById(int progId);
//	public ShowDetails updateSeatsvailable(ShowDetails sh,int id,int noOfTickets);
	List<ShowReport> getAllShowDetails(int showId, int id);
	ShowDetails updateSeatsvailable(ShowDetails sh, int id, int noOfTickets);
	public int getNoOfTicketsBooked(int id1, int shid1);
	 public ShowDetails updateSeatsvailable1(ShowDetails sh,int id,int noOfTickets);
	public CustomerBookings updateCustomerBookings(CustomerBookings cb, int shid1, int id1, int noOfTickets);
//	public int registerCustomer1(Customer customer);
	public Boolean validateAdmin(String username, String password);
}
