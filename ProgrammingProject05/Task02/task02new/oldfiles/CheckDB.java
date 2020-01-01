package de.bit.pl02.pp5.task02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class CheckDB {
	private String name;
	
	CheckDB(String name)
	{ // constructor for setting up the database. 
		this.name = name; 
	}
	public static void main(String[] args) throws SQLException
	{ 
		Scanner input = new Scanner (System.in); 
		String name = input.nextLine(); 
		input.close(); 
		CheckDB DB = new CheckDB(name);
		DB.connect_DB(); 
	}
	private void connect_DB() throws SQLException {
		// TODO Auto-generated method stub
		Connection con = null; 
		try {
			Class.forName("org.sqlite.JDBC"); 
			con = DriverManager.getConnection("jdbc:sqlite:"+ this.name + ".db"); 
			System.out.println("Reached a connection"); 
			try {
				Statement smt = con.createStatement(); 
				String presence = "SELECT * FROM 'IMAGES'"; 
				System.out.println("Querying"); 
				ResultSet rs = smt.executeQuery(presence); 	
				while (rs.next())
				{ 	System.out.println("part of result set"); 
					System.out.println(rs.getString("ID")); 
				}
				rs.close(); 
				smt.close(); 
				System.out.println("Reached the end"); 
			}catch(Exception e) { 
				e.getMessage(); 
			}
		
		} catch (ClassNotFoundException e) { 
			e.getMessage();
		}
	}

}
