package com.odl.pack;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class QueryUtil {

	Statement stmt = null;
	ResultSet rs = null;
	static Connection conn = null;
	ConnUtil conMan = new ConnUtil();

	public void initQueryManager(){
		conn = conMan.getConn();
	}

	public ResultSet runQuery(String query){
		try {
			stmt = conn.createStatement();
			ResultSet res = stmt.executeQuery(query);
			return res;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public int runUpdate(String Query){
		try {
			stmt = conn.createStatement();
			int res = stmt.executeUpdate(Query);
			return res;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	public void closeAll(){
		conMan.closeResultSet(rs);
		conMan.closeStatement(stmt);
		conMan.closeConnection(conn);
	}
}
