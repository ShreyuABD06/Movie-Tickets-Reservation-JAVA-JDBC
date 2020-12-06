package com.movies.dao;

public interface QueryConstraints {
	String ADD_NEW_THEATRE = "insert into theatre(theatreName,location,seatingCapacity,phoneNo,_3d,iMax) values(?,?,?,?,?,?)";
	String GET_THEATRES = "select theatreId,theatreName,location,seatingCapacity,phoneNo,_3d,iMax from theatre";
	String UPDATE_THEATRE = "update theatre set seatingCapacity=? where theatreId=?";
	String ADD_NEW_MOVIE = "insert into movie(movieName,director,hero,heroine,language,releaseDate,rating)values(?,?,?,?,?,?,?)";
	String UPDATE_MOVIE = "update movie set releaseDate=? where movieid=?";
	String GET_MOVIES = "select movieId,movieName,director,hero,heroine,language,releaseDate,rating from movie";
	String ADD_NEW_SHOW = "insert into shows(showName,movieId,theatreId,date,showTime,ticketCost,seatsAvailable) values(?,?,?,?,?,?,?)";
	String GET_SHOWS = "select showId,showName,movieId,theatreId,date,showTime,ticketCost,seatsAvailable from shows";
	String UPDATE_SHOW = "update shows set ticketCost=?,date=?,showTime=?,seatsAvailable=? where showId=?";
	String UPDATE_SEATSAVAILABLE = "update shows set seatsAvailable=seatsAvailable-? where showId=?";
	String UPDATE_SEATSAVAILABLE1 = "update shows set seatsAvailable=seatsAvailable+? where showId=?";
	String UPDATE_TICKETS_BOOKED = "update customerBookings set noOfTickets=noOfTickets-? where showId=? and customerId=?";
	String REGISTER_NEW_CUSTOMER= "insert into customer(name,phoneNo,email) values(?,?,?)";
	String UPDATE_CUSTOMER = "update customer set email=? where customerId=?";
	String GET_CUSTOMERS = "select customerId,name,phoneNo,email from customer";
	String GET_STUDENTS_BY_COURSEID = " select st.stuid,st.name,st.qualification,st.email,"
			+ "st.contry from student st inner join student_course s on st.stuid=s.stuCourId inner join "
			+ "course c on s.stuCourId=c.courid where c.courid=courseId";
	String GET_THEATRE_BY_ID = "select * from theatre where theatreId=?";
	
	String REGISTER_TO_SHOW = "insert into customerBookings(showId,customerId,noOfTickets) values(?,?,?)";
	

}
