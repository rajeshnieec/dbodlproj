package com.odl.pack;

public class ODL {
	public static void main(String[] args) {
		System.out.println("********************Welcome to ODL********************");
		ConnUtil conn = new ConnUtil();
		conn.initConnection();
		
		QueryUtil query = new QueryUtil();
		query.initQueryManager();
		
		InputUtil input = new InputUtil();
		input.start();
	}

}
