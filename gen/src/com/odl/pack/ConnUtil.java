package com.odl.pack;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConnUtil {
	final String jdbcURL = "jdbc:oracle:thin:@ora.csc.ncsu.edu:1521:orcl";
	static Connection conn = null;

	void initConnection(){
		try {
			// Load the driver. This creates an instance of the driver
			// and calls the registerDriver method to make Oracle Thin
			// driver available to clients.
			Class.forName("oracle.jdbc.driver.OracleDriver");

			String user = "vsharma4";	// For example, "jsmith vsharma4"
			String passwd = "001086501";	// Your 9 digit student ID number 001086501

			// Get a connection from the first driver in the
			// DriverManager list that recognizes the URL jdbcURL
			conn = DriverManager.getConnection(jdbcURL, user, passwd);
			
			//Create a default health Professional account

		} catch(Throwable ex) {
			ex.printStackTrace();
		}
	}

	public Connection getConn() {
		return conn;
	}

	void closeConnection(Connection conn) {
		if(conn != null) {
			try { conn.close(); } catch(Throwable ex) {}
		}
	}

	void closeStatement(Statement st) {
		if(st != null) {
			try { st.close(); } catch(Throwable ex) {}
		}
	}

	void closeResultSet(ResultSet rs) {
		if(rs != null) {
			try { rs.close(); } catch(Throwable ex) {}
		}
	}
}
