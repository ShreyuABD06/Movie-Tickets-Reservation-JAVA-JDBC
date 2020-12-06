package com.movies.service;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.movies.dao.MovieTicketsDao;
import com.movies.dao.MovieTicketsDaoImpl;
import com.movies.domain.Customer;
import com.movies.domain.CustomerBookings;
import com.movies.domain.Movie;
import com.movies.domain.STATUS;
import com.movies.domain.ShowDetails;
import com.movies.domain.Theatre;
import com.movies.report.ShowReport;

public class MovieTicketServiceImpl implements MovieTicketsService {

	private static MovieTicketsService movieTicketService;
	private MovieTicketsDao movieTicketDao;
	Scanner sc = new Scanner(System.in);

	public MovieTicketServiceImpl() {
		movieTicketDao = MovieTicketsDaoImpl.getInstanceMovieTicketsDaoImpl();
	}

	public static MovieTicketsService getInstanceCouserMgtService() {
		synchronized (MovieTicketServiceImpl.class) {
			if (movieTicketService == null)
				movieTicketService = new MovieTicketServiceImpl();
		}
		return movieTicketService;
	}

	@Override
	public String addTheatre(Theatre theatre) {
		System.out.println("Enter Theatre Name");
		String theatreName = sc.next();
		theatre.setTheatreName(theatreName);
		System.out.println("Enter Theatre Location");
		String location = sc.next();
		theatre.setLocation(location);
		System.out.println("Enter Seating Capacity");
		int seatingCapacity = sc.nextInt();
		theatre.setSeatingCapacity(seatingCapacity);
		System.out.println("Enter phone No");
		String phoneNo = sc.next();
		while (!this.checkContainDigits(phoneNo)) {
			System.out.println("Enter the valid mobile number :");
			phoneNo = this.sc.nextLine();
		}
		theatre.setPhoneNo(phoneNo);
		System.out.println("Does theatre have 3d capability");
		STATUS _3d =STATUS.valueOf(sc.next().toUpperCase());
		theatre.set_3d(_3d);
		System.out.println("Does theatre have iMAX capability");
		STATUS iMax = STATUS.valueOf(sc.next().toUpperCase());
		theatre.setiMax(iMax);
		System.out.println("Do you wish to Add Theatre......(y/n)");
		String d = sc.next();
		if (d.equalsIgnoreCase("y")){ 
		String s = movieTicketDao.addTheatre(theatre);
		if (s != null) {
			System.out.println("Theatre successfuly Added");
			return s;
		}else{
			return null;
			}	
		}else{
		return null;
		}
	}

	private boolean checkContainDigits(String phoneNo) {
		Pattern pattern = Pattern.compile("\\d{10}");
		Matcher matcher = pattern.matcher(phoneNo);
		boolean isvalid = matcher.matches();
		return isvalid;
	}

	@Override
	public List<Theatre> getTheatres() {
		List<Theatre> theatreList = movieTicketDao.getTheatres();
		if (theatreList != null) {
			return theatreList;
		} else {
			System.out.println("NO data avialable");
			return null;
		}
	}

	@Override
	public Theatre updateTheatre(Theatre updatedValue) {
		System.out.println("Enter Id of Theatre to be Updated");
		int Id = sc.nextInt();
		updatedValue = movieTicketDao.getTheatreById(Id);
		System.out.println(updatedValue);
		System.out.println("Do you wish to update Theatre Capacity......(y/n)");
		String d = sc.next();
		if (d.equalsIgnoreCase("y")){
		System.out.println("Enter the seating Capacity to be Updated");
		int seatingCapacity = sc.nextInt();
		updatedValue.setSeatingCapacity(seatingCapacity);
		}
		return updatedValue = movieTicketDao.updateTheatre(updatedValue);

	}

	@Override
	public String addMovie(Movie movie) {
		System.out.println("Enter Movie Name");
		String movieName = sc.next();
		movie.setMovieName(movieName);
		System.out.println("Enter Director Name");
		String director=sc.next();
		movie.setDirector(director);
		System.out.println("Enter Hero Name");
		String hero = sc.next();
		movie.setHero(hero);
		System.out.println("Enter Heroine Name");
		String heroine = sc.next();
		movie.setHeroine(heroine);
		System.out.println("Enter Language");
		String language=sc.next();
		movie.setLanguage(language);
		System.out.println("Enter Release Date(yyyy-mm-dd)");
		Date releaseDate= java.sql.Date.valueOf(sc.next());
		movie.setReleaseDate(releaseDate);
		System.out.println("Enter rating");
		float rating=sc.nextFloat();
		movie.setRating(rating);
		System.out.println("Do you wish to Add Movie......(y/n)");
		String d = sc.next();
		if (d.equalsIgnoreCase("y")) {
		String s = movieTicketDao.addMovie(movie);
		if (s != null) {
			System.out.println("Movie successfuly Added");
			return s;
		}else{
			return null;
		}
		}else {
			return null;
		}
	}

	@Override
	public Movie updateMovie(Movie movie) {
		System.out.println("Enter Id of movie to be Updated");
		int Id = sc.nextInt();
		movie = movieTicketDao.getMovieById(Id);
		System.out.println(movie);
		System.out.println("Do you wish to update ur Mail......(y/n)");
		String d = sc.next();
		if (d.equalsIgnoreCase("y")){
		System.out.println("Enter the release to be Updated(yyyy-mm-dd)");
		Date releaseDate= java.sql.Date.valueOf(sc.next());
		movie.setReleaseDate(releaseDate);
		}
		return movie = movieTicketDao.updateMovie(movie);
	}

	@Override
	public List<Movie> getAllMovies() {
		List<Movie> movieList = movieTicketDao.getAllMovies();
		if (movieList != null) {
			return movieList;
		} else {
			System.out.println("NO data avialable");
			return null;
		}
	}

	@Override
	public String addShow(ShowDetails show) {
		System.out.println("Enter show Name");
		String showName = sc.next();
		show.setShowName(showName);
		System.out.println("Enter Movie id");
		int movieId = sc.nextInt();
		show.setMovieId(movieId);
		System.out.println("Enter Theatre Id");
		int theatreId=sc.nextInt();
		show.setTheatreId(theatreId);
		System.out.println("Enter show Date(yyyy-mm-dd)");
		Date date= java.sql.Date.valueOf(sc.next());
		show.setDate(date);
		System.out.println("Enter Show Time(hh:mm:ss)");
		Time showTime=java.sql.Time.valueOf(sc.next());
		show.setShowTime(showTime);
		System.out.println("Enter Ticket Cost");
		double ticketCost=sc.nextDouble();
		show.setTicketCost(ticketCost);
		System.out.println("Enter Maximum Seats Available");
		int seatsAvailable=sc.nextInt();
		show.setSeatsAvailable(seatsAvailable);
		System.out.println("Do you wish to Add Show......(y/n)");
		String d = sc.next();
		if (d.equalsIgnoreCase("y")) {
		String s = movieTicketDao.addShow(show);
		if (s != null) {
			System.out.println("Show successfuly Added");
			return s;
		}else{
			return null;
		}
		}else {
			return null;
		}
		
	}

	@Override
	public ShowDetails updateShow(ShowDetails show) {
		System.out.println("Enter Id of show to be Updated");
		int Id = sc.nextInt();
		show = movieTicketDao.getShowById(Id);
		System.out.println(show);
		System.out.println("Do you wish to update cost......(y/n)");
		String d = sc.next();
		if (d.equalsIgnoreCase("y")){
		System.out.println("Enter the cost to be Updated");
		double ticketCost=sc.nextDouble();
		show.setTicketCost(ticketCost);
		}
		System.out.println("Do you wish to update date?......(y/n)");
		String d1= sc.next();
		if (d1.equalsIgnoreCase("y")){
		System.out.println("Enter Date To Be updated(yyyy-mm-dd)");
		Date date= java.sql.Date.valueOf(sc.next());
		show.setDate(date);
		}
		System.out.println("Do you wish to update Show Time......(y/n)");
		String d2 = sc.next();
		if (d2.equalsIgnoreCase("y")){
		System.out.println("Enter show time to Be updated(hh:mm:dd)");
		Time showTime=java.sql.Time.valueOf(sc.next());
		show.setShowTime(showTime);
		}
		System.out.println("Do you wish to update Seats Available......(y/n)");
		String d3 = sc.next();
		if (d3.equalsIgnoreCase("y")){
		System.out.println("Enter New seats Available");
		int seatsAvailable=sc.nextInt();
		show.setSeatsAvailable(seatsAvailable);
		}
		return show = movieTicketDao.updateShow(show);
	}

	@Override
	public List<ShowDetails> getAllShows() {
		List<ShowDetails> showList = movieTicketDao.getAllShows();
		if (showList != null) {
			return showList;
		} else {
			System.out.println("NO data avialable");
			return null;
		}
	}

	@Override
	public String registerCustomer(Customer customer) {
		System.out.println("Enter customer Name");
		String name = sc.next();
		customer.setName(name);
		System.out.println("Enter phone No");
		String phoneNo = sc.next();
		while (!this.checkContainDigits(phoneNo)) {
			System.out.println("Enter the valid mobile number :");
			phoneNo = this.sc.nextLine();
		}
		customer.setPhoneNo(phoneNo);
		boolean isValid = false;
		System.out.println("Enter the email : Ex (abc@gmail.com) :");
		String email = this.sc.nextLine();
		while (!isValid) {
			isValid = this.isValidEmail(email);
			if (isValid)
				break;
			System.out.println("invalid mail");
			System.out.println("Enter the email : Ex (abc@gmail.com) :");
			email = this.sc.nextLine();
		}
		customer.setEmail(email);	
		String s = movieTicketDao.registerCustomer(customer);
		if (s != null) {
			System.out.println("Customer Registered Successfully");
			return s;
		} else {
			return null;
		}
	}
	
//	public int registerCustomer1(Customer customer){
//		System.out.println("Enter customer Name");
//		String name = sc.next();
//		customer.setName(name);
//		System.out.println("Enter phone No");
//		String phoneNo = sc.next();
//		while (!this.checkContainDigits(phoneNo)) {
//			System.out.println("Enter the valid mobile number :");
//			phoneNo = this.sc.nextLine();
//		}
//		customer.setPhoneNo(phoneNo);
//		boolean isValid = false;
//		System.out.println("Enter the email : Ex (abc@gmail.com) :");
//		String email = this.sc.nextLine();
//		while (!isValid) {
//			isValid = this.isValidEmail(email);
//			if (isValid)
//				break;
//			System.out.println("invalid mail");
//			System.out.println("Enter the email : Ex (abc@gmail.com) :");
//			email = this.sc.nextLine();
//		}
//		customer.setEmail(email);	
//		int s = movieTicketDao.registerCustomer1(customer);
//		
//			return s;
//		
//	}
	private boolean isValidEmail(String email) {
		String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		Pattern pattern = Pattern.compile(EMAIL_PATTERN);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}

	@Override
	public Customer updateCustomer(Customer customer) {
		System.out.println("Enter Id of Customer to be Updated");
		int Id = sc.nextInt();
		customer = getCustomerById(Id);
		System.out.println("Do you wish to update ur Mail......(y/n)");
		String d = sc.next();
		if (d.equalsIgnoreCase("y")) {
			System.out.println("Enter email to be Updated");
			boolean isValid = false;
			System.out.println("email : Ex (abc@gmail.com) :");
			String email = this.sc.nextLine();
			while (!isValid) {
				isValid = this.isValidEmail(email);
				if (isValid)
					break;
				System.out.println("invalid mail");
				System.out.println("Enter the email : Ex (abc@gmail.com) :");
				email = this.sc.nextLine();
			}
			customer.setEmail(email);
			return customer = movieTicketDao.updateCustomer(customer);
		} else {
			return null;
		}
	}

	@Override
	public List<Customer> getAllCustomers() {
		List<Customer> customerList = movieTicketDao.getAllCustomers();
		if (customerList != null) {
			return customerList;
		} else {
			System.out.println("NO data avialable");
			return null;
		}
	}
	
	@Override
	public List<Customer> getAllCustomers(int showId) {
		List<Customer> custList = movieTicketDao.getAllCustomers(showId);
		// Iterator<Student> itr = stList.iterator();
		if (custList != null) {
			return custList;
		} else {
			System.out.println("NO data avialable");
			return null;
		}	
	}

	@Override
	public boolean registerCustomerForShow(int customerId, int showId,int noOfTickets) {
		boolean status = movieTicketDao.registerCustomerForShow(customerId, showId,noOfTickets);
		return status;
	}

	@Override
	public List<ShowReport> getAllShowDetails() {
		List<ShowReport> report = movieTicketDao.getAllShowDetails();
		if (report != null) {
			return report;
		} else {
			System.out.println("NO data avialable");
			return null;
		}
	}

	@Override
	public Theatre getTheatreById(int Id) {
		Theatre theatre = movieTicketDao.getTheatreById(Id);
		if (theatre != null) {
			return theatre;
		} else {
			return null;
		}
	}

	@Override
	public Movie getMovieById(int Id) {
		Movie movie = movieTicketDao.getMovieById(Id);
		if (movie != null) {
			return movie;
		} else {
			return null;
		}
	}

	@Override
	public ShowDetails getShowById(int Id) {
		ShowDetails show = movieTicketDao.getShowById(Id);
		if (show != null) {
			return show;
		} else {
			return null;
		}
	}
	

	public Customer getCustomerById(int Id) {
		Customer customer = movieTicketDao.getCustomerById(Id);
		if (customer != null) {
			return customer;
		} else {
			return null;
		}
	}


	@Override
	public ShowDetails findShow(int movieId, int theatreId) {
		ShowDetails show = new ShowDetails();
		show = movieTicketDao.findShow(movieId, theatreId);
		if (show!= null) {
			return show;
		} else {
			return null;
		}
	}

	public List<ShowReport> getAllShowDetails(int showId,int id) {
		List<ShowReport> report = movieTicketDao.getAllShowDetails(showId,id);
		if (report != null) {
			return report;
		} else {
			System.out.println("NO data avialable");
			return null;
		}
	}

	public int deleteFromBookings(int id1,int shid1) {
		return movieTicketDao.deleteFromBookings(id1,shid1);
		
	}
	public double getTicketCostById(int progId){
		return movieTicketDao.getTicketCostById(progId);
	}


	public ShowDetails updateSeatsAvailable(ShowDetails sh,int id,int noOfTickets) {
		sh = movieTicketDao.getShowById(id);
//		int seatsAvailable=sh.getSeatsAvailable();
//		seatsAvailable=sh.getSeatsAvailable()-noOfTickets;		
//		sh.setSeatsAvailable(seatsAvailable);
		return sh = movieTicketDao.updateSeatsvailable(sh,id,noOfTickets);
	}

	public ShowDetails updateSeatsAvailable1(ShowDetails sh,int id,int noOfTickets) {
		sh = movieTicketDao.getShowById(id);
//		int seatsAvailable=sh.getSeatsAvailable();
//		seatsAvailable=sh.getSeatsAvailable()-noOfTickets;		
//		sh.setSeatsAvailable(seatsAvailable);
		return sh = movieTicketDao.updateSeatsvailable1(sh,id,noOfTickets);
	}

	public int getNoOFTicketsBooked(int id1, int shid1) {
		return movieTicketDao.getNoOfTicketsBooked(id1,shid1);
	}

	public CustomerBookings updateCustomerBookings(CustomerBookings cb, int shid1,int id1, int noOfTickets) {
//		cb = movieTicketDao.getShowById(shid1);
//		int seatsAvailable=sh.getSeatsAvailable();
//		seatsAvailable=sh.getSeatsAvailable()-noOfTickets;		
//		sh.setSeatsAvailable(seatsAvailable);
		return cb = movieTicketDao.updateCustomerBookings(cb,shid1,id1, noOfTickets);
	}

	public boolean validateAdmin(String username, String password) {
		Boolean b = movieTicketDao.validateAdmin(username, password);
		return b;
	}
}
