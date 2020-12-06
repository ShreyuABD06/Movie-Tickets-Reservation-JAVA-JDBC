package com.movies.service;

import java.util.List;

import com.movies.domain.Customer;
import com.movies.domain.Movie;
import com.movies.domain.ShowDetails;
import com.movies.domain.Theatre;
import com.movies.report.ShowReport;

public interface MovieTicketsService {
	public String addTheatre(Theatre theatre);   
	public List<Theatre> getTheatres(); 
	public Theatre updateTheatre(Theatre updatedValue); 
	public String addMovie(Movie movie); 
	public Movie updateMovie(Movie movie); 
	public List<Movie> getAllMovies(); 
	public String addShow(ShowDetails show);
	public ShowDetails updateShow(ShowDetails show);  
	public List<ShowDetails> getAllShows();
	public String registerCustomer(Customer customer); // Return new registered Customer name
	public Customer updateCustomer(Customer customer);  // Can update only email 
	public List<Customer> getAllCustomers();
	public List<Customer> getAllCustomers(int showId);// Return customers who are registered for given show id
	public boolean registerCustomerForShow(int customerId,int showId,int noOfTickets); // Ensure customer and show both exists then only add to db
	public List<ShowReport> getAllShowDetails();
	public Theatre getTheatreById(int Id);
	public Movie getMovieById(int Id);
	public ShowDetails getShowById(int Id);
	public ShowDetails findShow(int movieId, int theatreId);
	public List<ShowReport> getAllShowDetails(int showId,int id);
}
