package de.bit.pl02.pp5.task02;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;

public class Database {
	public static void main(String[] args) throws SQLException
	{ 
		Connection con = null; 
		try {
			Class.forName("org.sqlite.JDBC"); 
			con = DriverManager.getConnection("jdbc:sqlite:images2.db"); 
			try {
			Statement smt = con.createStatement(); 
			String presence = "SELECT * FROM sqlite_master WHERE type='TABLE' AND name='IMAGES'"; 
			boolean present = smt.execute(presence);
			if (!present) {
			String sql = "CREATE TABLE IMAGES " +
				       "(ID TEXT PRIMARY KEY NOT NULL,"+ 
					"TITLE   TEXT NOT NULL, AUTHOR TEXT NOT NULL)";
		   smt.execute(sql); 
		   String sql1 = "ALTER TABLE IMAGES ADD COLUMN PICTURE blob"; 
		   smt.execute(sql1); 
		   readmetadata("//Users//shreyakapoor//Desktop//PP5", smt); 
			} 
		  
		   //String sql = "INSERT INTO IMAGES (ID,TITLE)" + 
		//		   "VALUES ('1xyz', 'Paul McArthur');"; 
		  // smt.execute(sql); 
		// look for a method to put metadata in these columns using the metadata file. 
		   
		   ResultSet rs = smt.executeQuery("SELECT * from IMAGES"); 
		   while (rs.next())
		   { 
			   String x = rs.getString("ID"); 
			   String s = rs.getString("TITLE");
			   System.out.println(x+" "+s); 
			   
			   
					   
		   }
		}catch(Exception e) {
			e.printStackTrace();
		}
		} catch (ClassNotFoundException e) { 
			e.printStackTrace();
		}
		
	}
	public static void readmetadata(String dir, Statement smt) throws IOException, SQLException
	{ 
		File dir1 = new File(dir); 
    	File[] filesindir = dir1.listFiles();
    	if (filesindir!= null) { 
    		for (File child: filesindir )
    		{ 	String filename = child.getName(); 
    			if (filename.contains(".meta")){
    				FileReader filer = new FileReader(child.getAbsolutePath());
    				BufferedReader buffr = new BufferedReader(filer);
    				boolean eof = false;
    				String author = null; 
    				String title = null; 
    				String Id; 
    				while ((!eof))
    				  {
    				  String s = buffr.readLine();
    				  if(s == null){
    				    eof = true;
    				       }
    				  else{
    				    System.out.println(s);
    				    if (s.contains("Title:")){
    				    	title = s.split(":")[1]; 
    				    }
    				    else if (s.contains("Author:")) { 
    				    	author = s.split(":")[1]; 
    				    }
    				   
    				  	}
    				}
    				buffr.close();
    				Id = author.substring(0, 5)+title.substring(0,4)+filename.split("\\.")[0]; 
    				String sql = "INSERT INTO IMAGES (ID,TITLE, AUTHOR)" + 
    							"VALUES (" + Id+ ","+ title + "," + author +")";
    				System.out.println(sql); 
    				smt.execute(sql); 
    				
    				
    			} 
    		} 
    	}
	}
	
	
}
