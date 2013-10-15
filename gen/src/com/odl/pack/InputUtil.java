package com.odl.pack;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class InputUtil {
	QueryUtil qMan = new QueryUtil();
	Scanner in = new Scanner(System.in);

	public void start(){
		handleMainMenu();
	}

	public void handleMainMenu(){
		//		while(true){
		displayMainMenu();

		int choice = Integer.valueOf(in.next());

		switch(choice){
		case 0:
			System.out.println("Thanks for using ODL");
			qMan.closeAll();
			break;
		case 1:
			createNewPatientAccount();
			break;
		case 2:

			break;
		case 3:
			loginAsPatient();
			break;
		default:
			System.out.println("Wrong choice");
			handleMainMenu();
			break;
		}
		//		}
		//		System.out.println("Out of the loop");
	}


	public void displayMainMenu(){
		System.out.println("Your options:");
		System.out.println("0: Exit");
		System.out.println("1: Create New Account");
		System.out.println("2: Log in as Health Professional");
		System.out.println("3: Log in as Patient");
		System.out.println("Enter a choice(0-3):");
	}

	public void createNewPatientAccount(){
		//		boolean res = false;
		//		do{
		String input = "INSERT INTO Patients VALUES( ";
		System.out.println("Enter a valid username:");
		String uname = in.next();
		ResultSet rs = qMan.runQuery("select uname from Patients where uname='"+uname+"'");

		try {
			if(rs.next()== false){
				input= input + "\'" + uname + "\'" + ",";

				System.out.println("Enter a valid password:");
				input = input + "\'" + in.next() + "\'" + ",";

				System.out.println("Enter Your Name:");
				input = input + "\'" + in.next() + "\'" + ",";

				System.out.println("Enter DOB(MM-DD-YY):");
				input = input + "\'" + in.next() + "\'" + ",";

				System.out.println("Enter Gender(M/F):");
				input = input + "\'" + in.next() + "\'" + ",";

				System.out.println("Enter Address:");
				input = input + "\'" + in.next() + "\'" + ",'General')";

				System.out.println(input);
				int result= qMan.runUpdate(input);
				if(result == 1){
					System.out.println("Account created successfully.");
					//						break;
				}
				else{
					System.out.println("Error occurred in creating a new row.");
					handleMainMenu();
				}
			}
			else{
				System.out.println("Username already exists. Please try a different username.");
				handleMainMenu();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//		}while(res == false);
	}

	public void loginAsPatient(){

		System.out.println("Enter username:");
		String uname = in.next();

		System.out.println("Enter password:");
		String password = in.next();

		boolean res = authenticateUser(uname, password);		
		if(res == false){
			System.out.println("Username or password wrong.");
			handleMainMenu();
		}
		else{
			displayPatientMainMenu();

			int choice = Integer.valueOf(in.next());

			switch(choice){
			case 0:
				handleMainMenu();
			}


		}
	}

	public void displayPatientMainMenu(){
		System.out.println("Your options:");
		System.out.println("0: Log out");
		System.out.println("1: Enter Data");
		System.out.println("2: View Data");
		System.out.println("3: Clear Alerts");
		System.out.println("4: Connections");
		System.out.println("Enter a choice(0-4):");
	}

	public boolean authenticateUser(String uname, String password){
		String query = "select uname from Patients where uname='" +uname+"'" +" AND password=" +"'"+password+"'";
		ResultSet rs = qMan.runQuery(query);
		try {
			return rs.next();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
