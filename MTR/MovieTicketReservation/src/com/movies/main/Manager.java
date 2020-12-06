package com.movies.main;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.movies.dao.MovieTicketsDao;
import com.movies.domain.Customer;
import com.movies.domain.CustomerBookings;
import com.movies.domain.Movie;
import com.movies.domain.ShowDetails;
import com.movies.domain.Theatre;
import com.movies.report.ShowReport;
import com.movies.service.MovieTicketServiceImpl;

public class Manager {
	Scanner sc = null;

	public static void main(String[] args) {
		Manager m = new Manager();

		m.choose();
		// m.begin();
		// m.start();
	}

	private void choose() {

		this.sc = new Scanner(System.in);
		do {
			System.out.println(
					"\n---------------------------**************************WELCOME TO MOVIE TICKET RESERVATION***********************-------------------------------------\n\n");
			System.out.println("HOME: 1:ADMIN  2:CUSTOMER  3:exit");
			System.out.println(
					"\n-------------------------------------------------------------------------------------------------------------------------------------------------------\n");
			System.out.println("Enter your Choice");
			int choice = this.getUserChoice2();
			switch (choice) {
			case 1:
				System.out.println("Enter your E-mail :");// Create table
				// for
				// admin with
				// only 1
				// admin in db
				MovieTicketServiceImpl c = new MovieTicketServiceImpl();
				String username = sc.next();
				System.out.println("Enter your Password :");
				String password = sc.next();
				boolean check = c.validateAdmin(username, password);
				if (check) {
				this.manage();
				}else{
					System.out.println("Enter Valid email and password");
				}
				break;
			case 2:
				this.customer();
				break;
			case 3: {
				System.out.println("Closing All Tabs");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				System.out.println("Thank You ............Visit Again");
				System.exit(0);
			}
			}
		} while (true);
	}

	private void customer() {
		// cancelBooking1();
		MovieTicketServiceImpl c = new MovieTicketServiceImpl();
		this.sc = new Scanner(System.in);
		do {
			System.out.println(
					"\n---------------------------**************************WELCOME TO MOVIE TICKET RESERVATION***********************-------------------------------------\n\n");
			System.out.println(
					"Book: 1:viewTheatres 2: View Movies 3:View Shows   4:Find Show(By movieId and theatreId)    5:book movie 6:CancelBooking 7:exit");
			System.out.println(
					"\n-------------------------------------------------------------------------------------------------------------------------------------------------------\n");
			System.out.println("Enter your Choice");
			int choice = this.getUserChoice1();
			switch (choice) {
			case 1: {
				List<Theatre> list = c.getTheatres();
				System.out.println("Please wait .......................... ");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				if (list != null) {
					System.out.println(
							"------------------------------------****Theatres Around You****-----------------------------------");
					Iterator<Theatre> itr = list.iterator();
					while (itr.hasNext()) {
						System.out.println(itr.next());
//						System.out.format("\n%-8d%\t%-25s\t%-30s\t%-%-10d-%-25s%-35s%-6s\n",Object[]);
					}
				} else {
					System.out.println("No Theatres Found");
				}
				break;
			}
			case 2: {
				List<Movie> m = c.getAllMovies();
				System.out.println("Please wait .......................... ");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				if (m != null) {
					System.out.println(
							"------------------------------------****Movies****-----------------------------------");
					Iterator<Movie> itr = m.iterator();
					while (itr.hasNext()) {
						System.out.println(itr.next());
					}
				} else {
					System.out.println("No Movies to Display");
				}
				break;
			}
			case 3: {
				List<ShowDetails> list1 = c.getAllShows();
				System.out.println("Please wait .......................... ");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				if (list1 != null) {
					System.out.println(
							"------------------------------------*******Shows*******-----------------------------------");
					Iterator<ShowDetails> itr = list1.iterator();
					while (itr.hasNext()) {
						System.out.println(itr.next());
					}
				} else {
					System.out.println("No Shows to Display");
				}
				break;
			}
			case 4: {
				ShowDetails sho = new ShowDetails();
				System.out.println("Enter movie Id to Be Searched");
				int mid = sc.nextInt();
				System.out.println("Enter theatre Id to Be Searched");
				int tid = sc.nextInt();
				sho = c.findShow(mid, tid);
				if (sho != null) {
					System.out.println("Please wait .......................... ");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					System.out.println(sho);
				} else {
					System.out.println("show Not Found");
				}
				break;
			}
			case 5: {
				bookMovie();
				break;
			}
			case 6: {
				cancelBooking();
				break;
			}
			case 7: {
				System.out.println("Closing All Tabs");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				System.out.println("Thank You Visit Again............");
				this.sc.close();
				System.exit(0);
			}
			}
		} while (true);
	}

	private void cancelBooking() {
		MovieTicketServiceImpl c = new MovieTicketServiceImpl();
		Customer cus = new Customer();
		System.out.println("enter your customer id");
		int id1 = sc.nextInt();
		cus = c.getCustomerById(id1);
		if (cus != null) {
			System.out.println("Welcome !!!!!!!!!!!!!....");
			System.out.println("Enter showId of the booking to Be Cancelled");
			int shid1 = sc.nextInt();
			System.out.println("Are you sure you want to cancel Booking(y/n)");
			String d = sc.next();
			if (d.equalsIgnoreCase("y")) {
				int cancelled = c.deleteFromBookings(id1, shid1);
				System.out.println("Please wait .......................... ");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				if (cancelled > 0) {
					System.out.println("Your booking for show was Cancelled..............");
					System.out.println("Go to Booking portal to continue Booking.........");
				}
			}
		} else {
			System.out.println("Sorry......you have not Booked Ticket at all............:)");
		}

	}

	private void cancelBooking1() {
		MovieTicketServiceImpl c = new MovieTicketServiceImpl();
		Customer cus = new Customer();
		System.out.println("enter your customer id");
		int id1 = sc.nextInt();
		cus = c.getCustomerById(id1);
		if (cus != null) {
			System.out.println("Welcome !!!!!!!!!!!!!....");
			System.out.println("Enter showId of the booking to Be Cancelled");
			int shid1 = sc.nextInt();

			int noOfTicketsBooked = c.getNoOFTicketsBooked(id1, shid1);
			System.out.println("Tickets Booked=" + noOfTicketsBooked);
			System.out.println("How many tickets you wish to cancel");
			int noOfTickets = sc.nextInt();
			while (noOfTickets > noOfTicketsBooked) {
				System.out.println("You have only Booked " + noOfTicketsBooked + " Tickets");
				System.out.println("Please enter No Of Tickets again");
				noOfTickets = sc.nextInt();
			}
			if (noOfTickets == noOfTicketsBooked) {
				System.out.println("Are you sure you want to cancel Booking(y/n)");
				String d = sc.next();
				if (d.equalsIgnoreCase("y")) {
					int cancelled = c.deleteFromBookings(id1, shid1);
					System.out.println("Please wait .......................... ");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					if (cancelled > 0) {
						System.out.println("Your booking for show was Cancelled..............");
						ShowDetails sh = new ShowDetails();
						sh = c.updateSeatsAvailable1(sh, shid1, noOfTickets);
						System.out.println(sh);
						System.out.println("Go to Booking portal to continue Booking.........");
					}
				} else {
					System.out.println("Thank you for not Cancelling tickets");
				}
			} else {
				System.out.println("Are you sure you want to cancel Booking(y/n)");
				String d1 = sc.next();
				if (d1.equalsIgnoreCase("y")) {
					CustomerBookings cb = new CustomerBookings();
					cb = c.updateCustomerBookings(cb, id1, shid1, noOfTickets);
					System.out.println("Remaining Tickets=" + cb);
				}
			}
		} else {
			System.out.println("Sorry......you have not Booked Ticket at all............:)");
		}

	}

	private void bookMovie() {
		MovieTicketServiceImpl c = new MovieTicketServiceImpl();

		this.sc = new Scanner(System.in);
		do {
			System.out.println(
					"-----------------------------------------------------------------------Welcome To Booking Module-----------------------------------------------------");
			System.out.println("Book: 1:Register Customer 2:Book Movie 3:exit");
			System.out.println(
					"\n-------------------------------------------------------------------------------------------------------------------------------------------------------\n");
			System.out.println("Enter your Choice");
			int choice = this.getUserChoice2();
			switch (choice) {
			case 1: {
				Customer cs = new Customer();
				String cust = c.registerCustomer(cs);
				System.out.println("Please wait .......................... ");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				if (cust != null) {
					System.out.println("Customer : " + cust);
					System.out.println("Thank You");
				} else {
					System.out.println("Could nOt register ....Try Again:)");
				}
				break;
			}
			case 2: {
				Customer cus = new Customer();
				System.out.println("Enter Your Registered Customer id");
				int id = sc.nextInt();
				cus = c.getCustomerById(id);
				if (cus != null) {
					System.out.println("Welcome,You can book ur Tickets Now..........:)");
					CustomerBookings cb = new CustomerBookings();
					// System.out.println("Enter Customer Id to Book a
					// Show");z// int customerId = sc.nextInt();
					cb.setCustomerId(id);
					System.out.println("Enter show Id which you wish to Book tickets for");
					int showId = sc.nextInt();
					cb.setShowId(showId);
					if (c.getShowById(showId) != null) {
						System.out.println("Enter no of tickets");
						int noOfTickets = sc.nextInt();
						cb.setNoOfTickets(noOfTickets);
						int i = paybill(showId, id, noOfTickets);
						// cb.setNoOfTickets(noOfTickets);
						if (i == 1) {
							boolean isBooked = c.registerCustomerForShow(id, showId, noOfTickets);
							System.out.println("Please wait .......................... ");
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e1) {
								e1.printStackTrace();
							}
							if (isBooked) {

								ShowDetails sh = new ShowDetails();
								sh = c.updateSeatsAvailable(sh, showId, noOfTickets);
								System.out.println(sh);

								List<ShowReport> sR = c.getAllShowDetails(showId, id);
								if (sR != null) {
									Iterator<ShowReport> itr = sR.iterator();
									System.out.println("Please wait .......................... ");
									try {
										Thread.sleep(1000);
									} catch (InterruptedException e1) {
										e1.printStackTrace();
									}
									System.out.println("your tickets are Booked");
									try {
										Thread.sleep(1000);
									} catch (InterruptedException e1) {
										e1.printStackTrace();
									}
									while (itr.hasNext()) {
										System.out.println(itr.next());
									}
									System.out.println("Thank You Visit Again.....:) :) ;)");
								}
								// paybill(showId,id,noOfTickets);
							}
						}
					} else {
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e1) {
							e1.printStackTrace();
						}
						System.out.println("No SHow With Show Id " + showId);
					}
				} else {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					System.out.println("Please Register yourself Before you Book");
				}
				break;
			}

			case 3: {
				System.out.println("Closing All Tabs");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				System.out.println("Thank You Visit Again............");
				this.sc.close();
				System.exit(0);
			}
			}
		} while (true);

	}

	private int paybill(int showId, int id, int noOfTickets) {
		MovieTicketServiceImpl c = new MovieTicketServiceImpl();
		this.sc = new Scanner(System.in);
		do {
			System.out.println("Please Pay your Bill");
			System.out.println("How Do Yo Wish To Pay(  1:Cash  2:Card 3:exit  ");
			System.out.println("Enter your Choice");
			int choice = this.getUserChoice2();
			switch (choice) {
			case 1: {
				System.out.println("Confirm your tickets By Paying......(y/n)");
				String d = sc.next();
				if (d.equalsIgnoreCase("y")) {
					System.out.println("Please wait .......................... ");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					System.out.println("Generating Bill ........");
					// ShowDetails sh = new ShowDetails();
					// sh = c.updateSeatsAvailable(sh,showId,noOfTickets);
					System.out.println("Please wait .......................... ");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					Double n = c.getTicketCostById(showId);
					Double totalCost = n * noOfTickets;
					System.out.println("no of Tickets Purchased=" + noOfTickets);
					System.out.println(noOfTickets + " * " + n + " = " + totalCost);
					System.out.println("Please Pay Rupees " + totalCost);
					System.out.println("customer paid(y/n)");
					String d1 = sc.next();
					if (d1.equalsIgnoreCase("y")) {
						return 1;
					} else {
						System.out.println("Transaction Cancelled........Amount was not Collected");
						System.out.println("Thank You");
						return 0;
					}
				} else {
					System.out.println("You Must Pay Before Booking");
				}
				break;
			}
			case 2: {
				System.out.println("Please Enter your Card Number(XXXX)");
				String cardNo = sc.next();
				while (!this.checkContainDigits(cardNo)) {
					System.out.println("Enter the valid card number :");
					cardNo = this.sc.nextLine();
				}
				System.out.println("Please Wait While we confirm With the Bank");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("Please wait,It willl take a while .......................... ");
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("Card Number Verified");
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("Confirm your tickets By Paying......(y/n)");
				String d = sc.next();
				if (d.equalsIgnoreCase("y")) {
					System.out.println("Please wait .......................... ");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					System.out.println("Generating Bill ........");
					// ShowDetails sh = new ShowDetails();
					// sh = c.updateSeatsAvailable(sh,showId,noOfTickets);
					System.out.println("Please wait .......................... ");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					Double n = c.getTicketCostById(showId);
					Double totalCost = n * noOfTickets;
					System.out.println("no of Tickets Purchased=" + noOfTickets);
					System.out.println("Please Pay Rupees " + totalCost);
					System.out.println("customer paid(y/n)");
					String d1 = sc.next();
					if (d1.equalsIgnoreCase("y")) {
						return 1;
					} else {
						System.out.println("Transaction Cancelled........Amount was not Collected");
						System.out.println("Thank You");
						return 0;
					}
				} else {
					System.out.println("You Must Pay Before Booking");
				}

				break;
			}
			case 3: {
				System.out.println("Sorry Ur Tickets Cant Be Booked Without Paying............");
				// this.sc.close();
				System.out.println("Do You wish to exit......(y/n)");
				String f = sc.next();
				if (f.equalsIgnoreCase("y")) {
					System.out.println("Please wait .......................... ");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					System.out.println("Thank You .......Visit Again");
					System.exit(0);
				}
			}
			}

		} while (true);
	}

	private boolean checkContainDigits(String cardNo) {
		Pattern pattern = Pattern.compile("\\d{4}");
		Matcher matcher = pattern.matcher(cardNo);
		boolean isvalid = matcher.matches();
		return isvalid;
	}

	private int getUserChoice2() {
		boolean isNumber = true;
		int choice = 0;
		try {
			choice = Integer.parseInt(this.sc.nextLine());
		} catch (NumberFormatException e) {
			isNumber = false;
		}
		while (!(choice > 0 && choice <= 3 || !isNumber)) {
			System.out.println("Choice must be 1 to 3 only");
			try {
				choice = Integer.parseInt(this.sc.nextLine());
			} catch (NumberFormatException e) {
				isNumber = false;
			}
		}
		return choice;
	}

	private int getUserChoice1() {
		boolean isNumber = true;
		int choice = 0;
		try {
			choice = Integer.parseInt(this.sc.nextLine());
		} catch (NumberFormatException e) {
			isNumber = false;
		}
		while (!(choice > 0 && choice <= 7 || !isNumber)) {
			System.out.println("Choice must be 1 to 7 only");
			try {
				choice = Integer.parseInt(this.sc.nextLine());
			} catch (NumberFormatException e) {
				isNumber = false;
			}
		}
		return choice;
	}

	private void manage() {
		MovieTicketServiceImpl c = new MovieTicketServiceImpl();

		this.sc = new Scanner(System.in);
		do {
			System.out.println(
					"\n---------------------------**************************WELCOME TO MOVIE TICKET RESERVATION***********************-------------------------------------\n\n");
			System.out.println("\t\t\t\t1.ADD THEATRE\t\t2.VIEW THEATRES\t\t3.UPDATE THEATRE(Seating Capacity)\n"
					+ "\t\t\t\t4.ADD_MOVIE\t\t5.VIEW_MOVIES\t\t6.UPDATE_MOVIE(Release Date)\n"
					+ "\t\t\t\t7.ADD_SHOW\t\t8.VIEW_SHOW\t\t9.UPDATE_SHOWS(Ticket Cost,Date,Show Time)\n"
					+ "\t\t\t\t\t\t\t10.GET ALL REGISTERED CUSTOMERS\n" + "\t\t\t\t\t\t\t11.GET ALL CUSTOMERS BY SHOW\n"
					+ "\t\t\t\t\t\t\t12.GET ALL SHOWS REPORT\n" + "\t\t\t\t\t\t\t13.GET THEATRE BY ID\n"
					+ "\t\t\t\t\t\t\t14.GET MOVIE BY ID\n" + "\t\t\t\t\t\t\t15.GET SHOW BY ID\n"
					+ "\t\t\t\t\t\t\t\t16:Exit ");
			System.out.println(
					"\n-------------------------------------------------------------------------------------------------------------------------------------------------------\n");
			System.out.println("Enter your Choice");
			int choice = this.getUserChoice();
			switch (choice) {
			case 1: {
				Theatre theatre = new Theatre();
				String s = c.addTheatre(theatre);
				System.out.println("Please wait .......................... ");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				if (s != null) {
					System.out.println(
							"------------------------------------****Theatre Added****-----------------------------------");
					System.out.println("Theatre : " + s);
				} else {
					System.out.println("Theatre could nOt Be Added");
				}
				break;
			}
			case 2: {
				List<Theatre> list = c.getTheatres();
				System.out.println("Please wait .......................... ");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				if (list != null) {
					System.out.println(
							"------------------------------------****Theatres Around You****-----------------------------------");
					Iterator<Theatre> itr = list.iterator();
					while (itr.hasNext()) {
						System.out.println(itr.next());
					}
				} else {
					System.out.println("No Theatres Found");
				}
				break;
			}
			case 3: {
				Theatre t1 = new Theatre();
				t1 = c.updateTheatre(t1);
				System.out.println("Please wait .......................... ");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				System.out.println(
						"------------------------------------****Theatre Updated****-----------------------------------");
				System.out.println(t1);
				break;
			}
			case 4: {
				Movie movie = new Movie();
				String cr = c.addMovie(movie);
				System.out.println("Please wait .......................... ");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				if (cr != null) {
					System.out.println(
							"------------------------------------****Movie Added****-----------------------------------");
					System.out.println("Movie = " + cr);
				} else {
					System.out.println("Movie Could Not Be Added");
				}
				break;
			}
			case 5: {
				List<Movie> m = c.getAllMovies();
				System.out.println("Please wait .......................... ");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				if (m != null) {
					System.out.println(
							"------------------------------------****Movies****-----------------------------------");
					Iterator<Movie> itr = m.iterator();
					while (itr.hasNext()) {
						System.out.println(itr.next());
					}
				} else {
					System.out.println("No Movies to Display");
				}
				break;
			}
			case 6: {
				Movie m1 = new Movie();
				m1 = c.updateMovie(m1);
				System.out.println("Please wait .......................... ");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if (m1 != null) {
					System.out.println(
							"------------------------------------****Movie Details Updated****-----------------------------------");
					System.out.println(m1);
				} else {
					System.out.println("Could NOt Update Movie Details");
				}
				break;
			}
			case 7: {
				ShowDetails st = new ShowDetails();
				String show = c.addShow(st);
				System.out.println("Please wait .......................... ");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				if (show != null) {
					System.out.println(
							"------------------------------------******Show Added******-----------------------------------");
					System.out.println("show : " + show);
				} else {
					System.out.println("Could nOt Add Show....Try Again:)");
				}
				break;
			}
			case 8: {
				List<ShowDetails> list1 = c.getAllShows();
				System.out.println("Please wait .......................... ");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				if (list1 != null) {
					System.out.println(
							"------------------------------------*******Shows*******-----------------------------------");
					Iterator<ShowDetails> itr = list1.iterator();
					while (itr.hasNext()) {
						System.out.println(itr.next());
					}
				} else {
					System.out.println("No Shows to Display");
				}
				break;
			}
			case 9: {
				ShowDetails sh = new ShowDetails();
				sh = c.updateShow(sh);
				System.out.println("Please wait .......................... ");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				if (sh != null) {
					System.out.println(
							"------------------------------------****Updated show Details****-----------------------------------");
					System.out.println(sh);
				} else {
					System.out.println("Could not Update Show");
				}
				break;
			}
			case 10: {
				List<Customer> c1 = c.getAllCustomers();
				System.out.println("Please wait .......................... ");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				if (c1 != null) {
					System.out.println(
							"------------------------------------*******Customers Registered*******-----------------------------------");
					Iterator<Customer> itr = c1.iterator();
					while (itr.hasNext()) {
						System.out.println(itr.next());
					}
				} else {
					System.out.println("No Registered Customers to Display");
				}
				break;
			}
			case 11: {
				System.out.println("Enter the Show Id To Get Customers ");
				int sId = sc.nextInt();
				List<Customer> p1 = c.getAllCustomers(sId);
				System.out.println("Please wait .......................... ");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				if (p1 != null) {
					Iterator<Customer> itr = p1.iterator();
					System.out.println(
							"------------------------------------****customers Registered****-----------------------------------");
					while (itr.hasNext()) {
						System.out.println(itr.next());
					}
				} else {
					System.out.println("No Student Registered for Course " + sId);
				}
				break;
			}
			case 12: {
				List<ShowReport> sR = c.getAllShowDetails();
				if (sR != null) {
					Iterator<ShowReport> itr = sR.iterator();
					System.out.println("Please wait .......................... ");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					System.out.println(
							"------------------------------------****show Report****-----------------------------------");
					while (itr.hasNext()) {

						System.out.println(itr.next());
					}
				} else {
					System.out.println("No shows to Report");
				}
				break;
			}
			case 13: {
				Theatre t = new Theatre();
				System.out.println("Enter Theatre Id to get Theatre");
				int theatreId = sc.nextInt();
				t = c.getTheatreById(theatreId);
				System.out.println("Please wait .......................... ");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				if (t != null) {
					System.out.println(t);
				} else {
					System.out.println("Theatre with id " + theatreId + " Not Found");
				}
				break;
			}
			case 14: {
				Movie mo = new Movie();
				System.out.println("Enter movie Id to get Movie");
				int movieId = sc.nextInt();
				mo = c.getMovieById(movieId);
				System.out.println("Please wait .......................... ");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				if (mo != null) {
					System.out.println(mo);
				} else {
					System.out.println("Movie with id " + movieId + " notFound");
				}
				break;
			}
			case 15: {
				ShowDetails sh1 = new ShowDetails();
				System.out.println("Enter showId Id to get Show");
				int shId = sc.nextInt();
				sh1 = c.getShowById(shId);
				System.out.println("Please wait .......................... ");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				if (sh1 != null) {
					System.out.println(sh1);
				} else {
					System.out.println("Show with Id " + shId + " Not Found");
				}
				break;
			}
			case 16: {
				System.out.println("Closing All Tabs");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				System.out.println("Bye............");
				this.sc.close();
				System.exit(0);
			}
			}
		} while (true);
	}

	private int getUserChoice() {
		boolean isNumber = true;
		int choice = 0;
		try {
			choice = Integer.parseInt(this.sc.nextLine());
		} catch (NumberFormatException e) {
			isNumber = false;
		}
		while (!(choice > 0 && choice <= 16 || !isNumber)) {
			System.out.println("Choice must be 1 to 16 only");
			try {
				choice = Integer.parseInt(this.sc.nextLine());
			} catch (NumberFormatException e) {
				isNumber = false;
			}
		}
		return choice;
	}

}
