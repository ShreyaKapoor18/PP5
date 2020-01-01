package de.bit.pl02.pp5.task02;

import java.awt.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;

public class Database2 {
	public static void main(String[] args) throws SQLException, IOException
	{ 
		Connection con = null; 
		try {
			Class.forName("org.sqlite.JDBC"); 
			con = DriverManager.getConnection("jdbc:sqlite:testzz.db"); 
			try {
				Statement smt = con.createStatement(); 
				String sql = "CREATE TABLE IF NOT EXISTS IMAGES " +
					       "(ID TEXT PRIMARY KEY NOT NULL,"+ 
						"TITLE   TEXT NOT NULL, AUTHOR TEXT NOT NULL)";
				String presence = "SELECT count(name) FROM sqlite_master WHERE type='TABLE' AND name='IMAGES'"; 
				smt.execute(sql); 
				smt.execute(presence);				
			   ResultSet rs = smt.executeQuery("SELECT * from IMAGES"); 
				String sql1 = "ALTER TABLE IMAGES ADD COLUMN PICTURE blob"; 
				 smt.execute(sql1); 
				 System.out.println(sql1); 
				 ArrayList<String> arr = readmetadata("//Users//shreyakapoor//Desktop//PP5"); 
				 for (String element: arr)
				 { 
					smt.execute(element);  
				 }
				 System.out.println(arr); 
			}catch(Exception e) { 
				e.getMessage(); 
			}
		
		} catch (ClassNotFoundException e) { 
			e.getMessage();
		}
		
	}
	public static ArrayList readmetadata(String dir) throws IOException, SQLException, ClassNotFoundException
	{  
		File dir1 = new File(dir); 
    	File[] filesindir = dir1.listFiles();
    	ArrayList<String> arr = new ArrayList<String>();
    	if (filesindir!= null) { 
    		for (File child: filesindir )
    		{ 	String filename = child.getName(); 
    			if (filename.contains(".meta")){
    				System.out.println(filename); 
    				FileReader filer = new FileReader(child.getAbsolutePath());
    				BufferedReader buffr = new BufferedReader(filer);
    				boolean eof = false;
    				String author = "xx"; 
    				String title = "yy"; 
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
    				if ( author.length()>=2 && title.length()>=5){
    					Id = author.substring(0, 2)+title.substring(0,4)+filename.split("\\.")[0];
    				} 
    				else {
    					Id = filename.split("-")[0]; 
    				}
 
    				String sql = "INSERT INTO IMAGES (ID,TITLE, AUTHOR)" + 
    							"VALUES ("+ Id+ ","+ title + "," + author +")";
    				arr.add(sql);
    				continue; 
    				
    			} 
    		} 
    	}
    	return arr;
     
	}
	
	
	
}
