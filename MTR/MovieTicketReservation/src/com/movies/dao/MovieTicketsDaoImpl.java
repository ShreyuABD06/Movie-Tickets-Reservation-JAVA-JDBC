package com.movies.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.movies.dao.dbutil.DbUtil;
import com.movies.domain.Customer;
import com.movies.domain.CustomerBookings;
import com.movies.domain.Movie;
import com.movies.domain.STATUS;
import com.movies.domain.ShowDetails;
import com.movies.domain.Theatre;
import com.movies.report.ShowReport;
import com.mysql.jdbc.ServerAffinityStrategy;

import static com.movies.dao.QueryConstraints.ADD_NEW_THEATRE;
import static com.movies.dao.QueryConstraints.GET_THEATRES;
import static com.movies.dao.QueryConstraints.UPDATE_THEATRE;
import static com.movies.dao.QueryConstraints.ADD_NEW_MOVIE;
import static com.movies.dao.QueryConstraints.UPDATE_MOVIE;
import static com.movies.dao.QueryConstraints.GET_MOVIES;
import static com.movies.dao.QueryConstraints.ADD_NEW_SHOW;
import static com.movies.dao.QueryConstraints.UPDATE_SHOW;
import static com.movies.dao.QueryConstraints.UPDATE_SEATSAVAILABLE;
import static com.movies.dao.QueryConstraints.UPDATE_SEATSAVAILABLE1;
import static com.movies.dao.QueryConstraints.UPDATE_TICKETS_BOOKED;
import static com.movies.dao.QueryConstraints.GET_SHOWS;
import static com.movies.dao.QueryConstraints.REGISTER_NEW_CUSTOMER;
import static com.movies.dao.QueryConstraints.UPDATE_CUSTOMER;
import static com.movies.dao.QueryConstraints.GET_CUSTOMERS;
import static com.movies.dao.QueryConstraints.REGISTER_TO_SHOW;
import static com.movies.dao.QueryConstraints.GET_THEATRE_BY_ID;

public class MovieTicketsDaoImpl implements MovieTicketsDao {

	private static MovieTicketsDaoImpl movieTicketsDaoImpl;
	private DbUtil dbUtil;

	public MovieTicketsDaoImpl() {
		dbUtil = DbUtil.obj;
	}

	public static MovieTicketsDaoImpl getInstanceMovieTicketsDaoImpl() {
		synchronized (MovieTicketsDaoImpl.class) {
			if (movieTicketsDaoImpl == null)
				movieTicketsDaoImpl = new MovieTicketsDaoImpl();
		}
		return movieTicketsDaoImpl;
	}

	@Override
	public String addTheatre(Theatre theatre) {
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = dbUtil.getConnection();
			pst = con.prepareStatement(ADD_NEW_THEATRE);
			pst.setString(1, theatre.getTheatreName());
			pst.setString(2, theatre.getLocation());
			pst.setInt(3, theatre.getSeatingCapacity());
			pst.setString(4, theatre.getPhoneNo());
			pst.setString(5, theatre.get_3d().name());
			pst.setString(6, theatre.get_3d().name());
			int isExecuted = pst.executeUpdate();
			if (isExecuted != 0)
				return theatre.getTheatreName();
		} catch (SQLException e) {
			System.out.println("While adding new Theatre : " + e);
		} finally {
			dbUtil.close(pst, con);
			System.out.println("Connections Closed");
		}
		return null;
	}

	@Override
	public List<Theatre> getTheatres() {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		Theatre theatre = null;
		List<Theatre> theatreList = new ArrayList<Theatre>();
		try {
			con = dbUtil.getConnection();
			stmt = con.createStatement();
			String view = GET_THEATRES;
			rs = stmt.executeQuery(view);
			while (rs.next()) {
				theatre = new Theatre();
				theatre.setTheatreId(rs.getInt(1));
				theatre.setTheatreName(rs.getString(2));
				theatre.setLocation(rs.getString(3));
				theatre.setSeatingCapacity(rs.getInt(4));
				theatre.setPhoneNo(rs.getString(5));
				theatre.set_3d(STATUS.valueOf(rs.getString(6)));
				theatre.setiMax(STATUS.valueOf(rs.getString(7)));
				theatreList.add(theatre);
			}
		} catch (SQLException e) {
			System.out.println("While Getting Theatres " + e);
		} finally {
			dbUtil.close(stmt, rs, con);
			System.out.println("Connectins Closed");
		}
		return theatreList;
	}

	@Override
	public Theatre updateTheatre(Theatre updatedValue) {
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = dbUtil.getConnection();
			String query = UPDATE_THEATRE;
			pst = con.prepareStatement(query);
			pst.setInt(1, updatedValue.getSeatingCapacity());
			pst.setInt(2, updatedValue.getTheatreId());
			pst.executeUpdate();
			con.commit();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			dbUtil.close(pst, con);
			System.out.println("connections Closed");
		}
		return updatedValue;
	}

	@Override
	public String addMovie(Movie movie) {
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = dbUtil.getConnection();
			pst = con.prepareStatement(ADD_NEW_MOVIE);
			pst.setString(1, movie.getMovieName());
			pst.setString(2, movie.getDirector());
			pst.setString(3, movie.getHero());
			pst.setString(4, movie.getHeroine());
			pst.setString(5, movie.getLanguage());
			pst.setDate(6, movie.getReleaseDate());
			pst.setFloat(7, movie.getRating());

			int isExecuted = pst.executeUpdate();
			if (isExecuted != 0)
				return movie.getMovieName();
		} catch (SQLException e) {
			System.out.println("While adding new Movie : " + e);
		} finally {
			dbUtil.close(pst, con);
			System.out.println("connections Closed");
		}
		return null;
	}

	@Override
	public Movie updateMovie(Movie movie) {
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = dbUtil.getConnection();
			String query = UPDATE_MOVIE;
			pst = con.prepareStatement(query);
			pst.setObject(1, movie.getReleaseDate());
			pst.setInt(2, movie.getMovieId());
			pst.executeUpdate();
		} catch (SQLException e) {
			System.out.println("While Updating Movie " + e);
		} finally {
			dbUtil.close(pst, con);
			System.out.println("COnnections Closed");
		}
		return movie;
	}

	@Override
	public List<Movie> getAllMovies() {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		Movie movie = null;
		List<Movie> movieList = new ArrayList<Movie>();
		try {
			con = dbUtil.getConnection();
			stmt = con.createStatement();
			String view = GET_MOVIES;
			rs = stmt.executeQuery(view);
			while (rs.next()) {
				movie = new Movie();
				movie.setMovieId(rs.getInt(1));
				movie.setMovieName(rs.getString(2));
				movie.setDirector(rs.getString(3));
				movie.setHero(rs.getString(4));
				movie.setHeroine(rs.getString(5));
				movie.setLanguage(rs.getString(6));
				movie.setReleaseDate(rs.getDate(7));
				movie.setRating(rs.getFloat(8));
				movieList.add(movie);
			}
		} catch (SQLException e) {
			System.out.println("While Getting Movies " + e);
		} finally {
			dbUtil.close(stmt, rs, con);
			System.out.println("Connections Closed");
		}
		return movieList;
	}

	@Override
	public String addShow(ShowDetails show) {
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = dbUtil.getConnection();
			pst = con.prepareStatement(ADD_NEW_SHOW);
			pst.setString(1, show.getShowName());
			pst.setInt(2, show.getMovieId());
			pst.setInt(3, show.getTheatreId());
			pst.setDate(4, show.getDate());
			pst.setTime(5, show.getShowTime());
			pst.setDouble(6, show.getTicketCost());
			pst.setInt(7, show.getSeatsAvailable());
			int isExecuted = pst.executeUpdate();
			if (isExecuted != 0)
				return show.getShowName();
		} catch (SQLException e) {
			System.out.println("While adding new show : " + e);
		} finally {
			dbUtil.close(pst, con);
			System.out.println("connections Closed");
		}
		return null;
	}

	@Override
	public ShowDetails updateShow(ShowDetails show) {
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = dbUtil.getConnection();
			String query = UPDATE_SHOW;
			pst = con.prepareStatement(query);
			pst.setDouble(1,show.getTicketCost());
			pst.setDate(2,show.getDate());
			pst.setTime(3,show.getShowTime());
			pst.setInt(4, show.getSeatsAvailable());
			pst.setInt(5, show.getShowId());
			pst.executeUpdate();
		} catch (SQLException e) {
			System.out.println("While Updating Movie " + e);
		} finally {
			dbUtil.close(pst, con);
			System.out.println("COnnections Closed");
		}
		return show;
	}

	@Override
	public List<ShowDetails> getAllShows() {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		ShowDetails show = null;
		List<ShowDetails> showList = new ArrayList<ShowDetails>();
		try {
			con = dbUtil.getConnection();
			stmt = con.createStatement();
			String view = GET_SHOWS;
			rs = stmt.executeQuery(view);
			while (rs.next()) {
				show = new ShowDetails();
				show.setShowId(rs.getInt(1));
				show.setShowName(rs.getString(2));
				show.setMovieId(rs.getInt(3));
				show.setTheatreId(rs.getInt(4));
				show.setDate(rs.getDate(5));
				show.setShowTime(rs.getTime(6));
				show.setTicketCost(rs.getDouble(7));
				show.setSeatsAvailable(rs.getInt(8));
				showList.add(show);
			}
		} catch (SQLException e) {
			System.out.println("While Getting Shows " + e);
		} finally {
			dbUtil.close(stmt, rs, con);
			System.out.println("Connectins Closed");
		}
		return showList;
	}

	@Override
	public String registerCustomer(Customer customer) {
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = dbUtil.getConnection();
			pst = con.prepareStatement(REGISTER_NEW_CUSTOMER);
			pst.setString(1, customer.getName());
			pst.setString(2, customer.getPhoneNo());
			pst.setString(3, customer.getEmail());
			int isExecuted = pst.executeUpdate();
			if (isExecuted != 0)
				return customer.getName();
		} catch (SQLException e) {
			System.out.println("While adding new customer : " + e);
		} finally {
			dbUtil.close(pst, con);
			
		}
		return null;
	}
//	public int registerCustomer1(Customer customer) {
//		Connection con = null;
//		PreparedStatement pst = null;
//		try {
//			con = dbUtil.getConnection();
//			pst = con.prepareStatement(REGISTER_NEW_CUSTOMER);
//			pst.setString(1, customer.getName());
//			pst.setString(2, customer.getPhoneNo());
//			pst.setString(3, customer.getEmail());
//			int isExecuted = pst.executeUpdate();
//			if (isExecuted != 0)
//				return customer.getCustomerId();
//		} catch (SQLException e) {
//			System.out.println("While adding new customer : " + e);
//		} finally {
//			dbUtil.close(pst, con);
//			
//		}
//		return 0;
//	}

	@Override
	public Customer updateCustomer(Customer customer) {
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = dbUtil.getConnection();
			String query = UPDATE_CUSTOMER;
			pst = con.prepareStatement(query);
			pst.setString(1, customer.getEmail());
			pst.setInt(2, customer.getCustomerId());
			pst.executeUpdate();
			con.commit();
		} catch (SQLException e) {
			System.out.println("While Updating Customer " + e);
		} finally {
			dbUtil.close(pst, con);
			System.out.println("Connections Closed");
		}
		return customer;
	}

	@Override
	public List<Customer> getAllCustomers() {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		Customer customer = null;
		List<Customer> customerList = new ArrayList<Customer>();
		try {
			con = dbUtil.getConnection();
			stmt = con.createStatement();
			String view = GET_CUSTOMERS;
			rs = stmt.executeQuery(view);
			while (rs.next()) {
				customer = new Customer();
				customer.setCustomerId(rs.getInt(1));
				customer.setName(rs.getString(2));
				customer.setPhoneNo(rs.getString(3));
				customer.setEmail(rs.getString(4));

				customerList.add(customer);
			}
		} catch (SQLException e) {
			System.out.println("While Getting All Customers " + e);
		} finally {
			dbUtil.close(stmt, rs, con);
			System.out.println("Connections Closed");
		}
		return customerList;
	}

	@Override
	public List<Customer> getAllCustomers(int showId) {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		// PreparedStatement pst = null;
		Customer cst = null;
		List<Customer> cstList = new ArrayList<Customer>();
		try {
			con = dbUtil.getConnection();
			stmt = con.createStatement();
			// String view = GET_STUDENTS_BY_COURSEID;
			String view = " select c.customerId,c.name,c.phoneNo,c.email from customer c"
					+ " inner join CustomerBookings b on c.customerId=b.customerId "
					+ "inner join shows s on b.showId=s.showId " + "where s.showId=" + showId;
			// // pst.setInt(1, courseId);
			rs = stmt.executeQuery(view);
			while (rs.next()) {
				cst = new Customer();
				cst.setCustomerId(rs.getInt(1));
				cst.setName(rs.getString(2));
				cst.setPhoneNo(rs.getString(3));
				cst.setEmail(rs.getString(4));

				cstList.add(cst);

			}
		} catch (SQLException e) {
			System.out.println("While Getting Students For A Course " + e);
		} finally {
			dbUtil.close(stmt, rs, con);
			System.out.println("Connections Closed");
		}
		return cstList;
	}

	@Override
	public boolean registerCustomerForShow(int customerId, int showId, int noOfTickets) {
		Connection con = null;
		PreparedStatement pst = null;
		CustomerBookings cst1 = null;
		try {
			con = dbUtil.getConnection();
			cst1 = new CustomerBookings();
			String query = REGISTER_TO_SHOW;
			pst = con.prepareStatement(query);
			pst.setInt(2, customerId);
			pst.setInt(1, showId);
			pst.setInt(3, noOfTickets);
			int isExcecuted = pst.executeUpdate();
			if (isExcecuted != 0)
				return true;
		} catch (SQLException e) {
			System.out.println("While Registering for Show : " + e);
		} finally {
			dbUtil.close(pst, con);
			System.out.println("connections Closed");
		}
		return false;
	}

	@Override
	public List<ShowReport> getAllShowDetails() {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		ShowReport sRp = null;
		List<ShowReport> reportList = new ArrayList<ShowReport>();
		try {
			con = dbUtil.getConnection();
			stmt = con.createStatement();
			// String view = GET_COURSE_REPORT;
			String view = " select s.showId,c.name,m.movieName,t.theatreName,t.location,s.date,s.showTime,b.noOfTickets,b.noOfTickets*s.ticketCost from customer c "
					+ "inner join customerBookings b on c.customerId=b.customerId "
					+ "inner join shows s on b.showId=s.showId " + "inner join theatre t on s.theatreId=t.theatreId "
					+ "inner join movie m on s.movieId=m.movieId";
			rs = stmt.executeQuery(view);
			while (rs.next()) {
				sRp = new ShowReport();
				sRp.setrId(rs.getInt(1));
				sRp.setCustomerName(rs.getString(2));
				sRp.setMovieName(rs.getString(3));
				sRp.setTheatreName(rs.getString(4));
				sRp.setLocation(rs.getString(5));
				sRp.setDate(rs.getDate(6));
				sRp.setShowTime(rs.getTime(7));
				sRp.setNoOfTickets(rs.getInt(8));
				sRp.setCost(rs.getDouble(9));
				reportList.add(sRp);
			}
		} catch (SQLException e) {
			System.out.println("While Getting show Report " + e);
		} finally {
			dbUtil.close(stmt, rs, con);
			System.out.println("Connection Closed");
		}
		return reportList;

	}

	@Override
	public Theatre getTheatreById(int theatreId) {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		Theatre theatre = null;
		try {
			con = dbUtil.getConnection();
			// String sql = GET_THEATRE_BY_ID;
			// pst = con.prepareStatement(sql);
			// pst.setInt(1, instId);
			// rs = pst.executeQuery();
			String sql = "select * from theatre where theatreId=" + theatreId;// Not
																				// working
																				// with
																				// Query
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				theatre = new Theatre();
				theatre.setTheatreId(rs.getInt(1));
				theatre.setTheatreName(rs.getString(2));
				theatre.setLocation(rs.getString(3));
				theatre.setSeatingCapacity(rs.getInt(4));
				theatre.setPhoneNo(rs.getString(5));
				theatre.set_3d(STATUS.valueOf(rs.getString(6)));
				theatre.setiMax(STATUS.valueOf(rs.getString(7)));

			}
		} catch (SQLException e) {
			System.out.println("While Getting Theatre By Id " + e);
		} finally {
			dbUtil.close(stmt, rs, con);
			System.out.println("Connections closed");
		}
		return theatre;
	}

	@Override
	public Movie getMovieById(int movieId) {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		// PreparedStatement pst = null;
		Movie movie = null;
		try {
			con = dbUtil.getConnection();
			String sql = "select * from movie where movieId=" + movieId;
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				movie = new Movie();
				movie.setMovieId(rs.getInt(1));
				movie.setMovieName(rs.getString(2));
				movie.setDirector(rs.getString(3));
				movie.setHero(rs.getString(4));
				movie.setHeroine(rs.getString(5));
				movie.setLanguage(rs.getString(6));
				movie.setReleaseDate(rs.getDate(7));
				movie.setRating(rs.getFloat(8));
			}
		} catch (SQLException e) {
			System.out.println("While getting Movie By id " + e);
		} finally {
			dbUtil.close(stmt, rs, con);
			System.out.println("Connections Closed");
		}
		return movie;
	}

	@Override
	public ShowDetails getShowById(int showId) {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		// PreparedStatement pst = null;
		ShowDetails show = null;
		try {
			con = dbUtil.getConnection();
			String sql = "select * from shows where showId=" + showId;// Not
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				show = new ShowDetails();
				show.setShowId(rs.getInt(1));
				show.setShowName(rs.getString(2));
				show.setMovieId(rs.getInt(3));
				show.setTheatreId(rs.getInt(4));
				show.setDate(rs.getDate(5));
				show.setShowTime(rs.getTime(6));
				show.setTicketCost(rs.getDouble(7));
				show.setSeatsAvailable(rs.getInt(8));
			}
		} catch (SQLException e) {
			System.out.println("While Getting Show By Id " + e);
		} finally {
			dbUtil.close(stmt, rs, con);
			System.out.println("Connection closed");
		}
		return show;
	}

	@Override
	public ShowDetails findShow(int movieId, int theatreId) {
		Connection con = null;
		// Statement stmt = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		ShowDetails show = null;
		try {
			con = dbUtil.getConnection();
			String sql = "select * from shows where movieId= ? and theatreId=?";// Not
																				// with
																				// Query
			pst = con.prepareStatement(sql);
			pst.setInt(1, movieId);
			pst.setInt(2, theatreId);
			rs = pst.executeQuery();
			while (rs.next()) {
				show = new ShowDetails();
				show.setShowId(rs.getInt(1));
				show.setShowName(rs.getString(2));
				show.setMovieId(rs.getInt(3));
				show.setTheatreId(rs.getInt(4));
				show.setDate(rs.getDate(5));
				show.setShowTime(rs.getTime(6));
				show.setTicketCost(rs.getDouble(7));
			}
		} catch (SQLException e) {
			System.out.println("While Finding show " + e);
		} finally {
			dbUtil.close(pst, rs, con);
			System.out.println("Connection Closed");
		}
		return show;
	}

	@Override
	public Customer getCustomerById(int id) {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		// PreparedStatement pst = null;
		Customer customer = null;
		try {
			con = dbUtil.getConnection();
			String sql = "select * from customer where customerId=" + id;
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				customer = new Customer();
				customer.setCustomerId(rs.getInt(1));
				customer.setName(rs.getString(2));
				customer.setPhoneNo(rs.getString(3));
				customer.setEmail(rs.getString(4));
			}
		} catch (SQLException e) {
			System.out.println("While getting Movie By id " + e);
		} finally {
			dbUtil.close(stmt, rs, con);
			System.out.println("Connections Closed");
		}
		return customer;
	}

	@Override
	public List<ShowReport> getAllShowDetails(int showId, int id) {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		ShowReport sRp = null;
		List<ShowReport> reportList = new ArrayList<ShowReport>();
		try {
			con = dbUtil.getConnection();
			stmt = con.createStatement();
			// String view = GET_COURSE_REPORT;
			String view = " select s.showId,c.name,m.movieName,t.theatreName,t.location,s.date,s.showTime,b.noOfTickets,b.noOfTickets*s.ticketCost from customer c "
					+ "inner join customerBookings b on c.customerId=b.customerId "
					+ "inner join shows s on b.showId=s.showId " + "inner join theatre t on s.theatreId=t.theatreId "
					+ "inner join movie m on s.movieId=m.movieId where c.customerId=" + id;
			rs = stmt.executeQuery(view);
			while (rs.next()) {
				sRp = new ShowReport();
				sRp.setrId(rs.getInt(1));
				sRp.setCustomerName(rs.getString(2));
				sRp.setMovieName(rs.getString(3));
				sRp.setTheatreName(rs.getString(4));
				sRp.setLocation(rs.getString(5));
				sRp.setDate(rs.getDate(6));
				sRp.setShowTime(rs.getTime(7));
				sRp.setNoOfTickets(rs.getInt(8));
				sRp.setCost(rs.getDouble(9));
				reportList.add(sRp);
			}
		} catch (SQLException e) {
			System.out.println("While Getting Bill " + e);
		} finally {
			dbUtil.close(stmt, rs, con);
			System.out.println("Connection Closed");
		}
		return reportList;
	}

	@Override
	public int deleteFromBookings(int id1, int shid1) {
		Connection con = null;
		PreparedStatement pst = null;
		int k = 0;
		;
		try {
			con = dbUtil.getConnection();
			String query = "delete from customerBookings where customerId=? and showId=?";
			pst = con.prepareStatement(query);
			pst.setInt(1, id1);
			pst.setInt(2, shid1);
			k = pst.executeUpdate();
			// con.commit();

		} catch (SQLException e) {
			System.out.println("While cancelling Booking " + e);
		} finally {
			dbUtil.close(pst, con);
			System.out.println("Connections Closed");
		}
		if (k > 0) {
			return k;
		} else {
			return 0;
		}
	}

	public double getTicketCostById(int progId) {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		ShowDetails prog = null;
		double s = 0;
		try {
			con = dbUtil.getConnection();
			stmt = con.createStatement();
			String view = "select ticketCost from shows where showId=" + progId;
			rs = stmt.executeQuery(view);
			while (rs.next()) {
				prog = new ShowDetails();
				prog.setTicketCost(rs.getDouble(1));
				return rs.getDouble(1);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			dbUtil.close(stmt, rs, con);

		}
		return 0;

	}

	 @Override
	 public ShowDetails updateSeatsvailable(ShowDetails sh,int id,int noOfTickets) {
	 Connection con = null;
	 PreparedStatement pst = null;
	 try {
	 con = dbUtil.getConnection();
	 String query = UPDATE_SEATSAVAILABLE;
	 pst = con.prepareStatement(query);
	 pst.setInt(1,noOfTickets);
	 pst.setInt(2, sh.getShowId());
	 pst.executeUpdate();
	 } catch (SQLException e) {
	 System.out.println("While Updating Seats Available " + e);
	 } finally {
	 dbUtil.close(pst, con);
	
	 }
	 return sh;
	 }

	 public ShowDetails updateSeatsvailable1(ShowDetails sh,int id,int noOfTickets) {
		 Connection con = null;
		 PreparedStatement pst = null;
		 try {
		 con = dbUtil.getConnection();
		 String query = UPDATE_SEATSAVAILABLE1;
		 pst = con.prepareStatement(query);
		 pst.setInt(1,noOfTickets);
		 pst.setInt(2, sh.getShowId());
		 pst.executeUpdate();
		 } catch (SQLException e) {
		 System.out.println("While Updating Seats Available " + e);
		 } finally {
		 dbUtil.close(pst, con);
		
		 }
		 return sh;
		 }

	@Override
	public int getNoOfTicketsBooked(int id1, int shid1) {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		CustomerBookings prog = null;
		double s = 0;
		try {
			con = dbUtil.getConnection();
			stmt = con.createStatement();
			String view = "select noOfTickets from customerBookings where showId=" + shid1+" and customerId="+id1;
			rs = stmt.executeQuery(view);
			while (rs.next()) {
				prog = new CustomerBookings();
				prog.setNoOfTickets(rs.getInt(1));;
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			dbUtil.close(stmt, rs, con);

		}
		return 0;
	}

	@Override
	public CustomerBookings updateCustomerBookings(CustomerBookings cb, int shid1, int id1, int noOfTickets) {
		 Connection con = null;
		 PreparedStatement pst = null;
		 try {
		 con = dbUtil.getConnection();
		 String query = UPDATE_TICKETS_BOOKED;
		 pst = con.prepareStatement(query);
		 pst.setInt(1,noOfTickets);
		 pst.setInt(2, cb.getShowId());
		 pst.setInt(3, cb.getCustomerId());
		 pst.executeUpdate();
		 } catch (SQLException e) {
		 System.out.println("While Updating Tickets Booked " + e);
		 } finally {
		 dbUtil.close(pst, con);
		
		 }
		 return cb;
	}

	@Override
	public Boolean validateAdmin(String username, String password) {
		Connection con =null;
		PreparedStatement pst = null;
		ResultSet rs=null;
		try {
			con = dbUtil.getConnection();
			String sql = "select * from admin where userName=? and password=? ";
			pst = con.prepareStatement(sql);
			pst.setString(1, username);
			pst.setString(2, password);
			rs = pst.executeQuery();
			while (rs.next()) {
				return true;
			}
		} catch (SQLException s) {
			System.out.println(s.getMessage());
		}
		return false;
	}
}
