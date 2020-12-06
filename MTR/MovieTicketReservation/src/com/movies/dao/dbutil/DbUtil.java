package com.movies.dao.dbutil;

import java.io.File;
import java.io.FileInputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public enum DbUtil {
	obj;
	private Properties properties = null;
	{
		properties = new Properties();

		try (FileInputStream fis = new FileInputStream(new File("db.properties"))) {
			properties.load(fis);
		} catch (Exception e) {
			System.out.println("While loading property file :" + e);
		}
	}

	public Connection getConnection() {
		Connection con = null;
		try {
			con = DriverManager.getConnection(properties.getProperty("url"), properties);
		} catch (SQLException e) {
			System.out.println("While connecting with database :" + e);
		}
		return con;
	}

	public void close(Connection con) {
		try {
			if (con != null)
				con.close();
		} catch (SQLException e) {
			System.out.println("While closing connection :" + e);
		}
	}

	public void close(Statement st, Connection con) {
		try {
			if (st != null)
				st.close();
			if (con != null)
				con.close();
		} catch (SQLException e) {
			System.out.println("While closing connection :" + e);
		}
	}

	public void close(Statement st, ResultSet rs, Connection con) {
		try {
			if (rs != null)
				rs.close();
			if (st != null)
				st.close();
			if (con != null)
				con.close();
		} catch (SQLException e) {
			System.out.println("While closing connection :" + e);
		}
	}

	public void close(PreparedStatement pst, Connection con) {
		try {
			if (pst != null)
				pst.close();
			if (con != null)
				con.close();
		} catch (SQLException e) {
			System.out.println("While closing connection :" + e);
		}
	}

	public void close(PreparedStatement pst, ResultSet rs, Connection con) {
		try {
			if (rs != null)
				rs.close();
			if (pst != null)
				pst.close();
			if (con != null)
				con.close();
		} catch (SQLException e) {
			System.out.println("While closing connection :" + e);
		}
	}

	public void close(CallableStatement cst, Connection con) {
		try {
			if (cst != null)
				cst.close();
			if (con != null)
				con.close();
		} catch (SQLException e) {
			System.out.println("While closing connection :" + e);
		}
	}

	public void close(CallableStatement cst, ResultSet rs, Connection con) {
		try {
			if (rs != null)
				rs.close();
			if (cst != null)
				cst.close();
			if (con != null)
				con.close();
		} catch (SQLException e) {
			System.out.println("While closing connection :" + e);
		}
	}
}
