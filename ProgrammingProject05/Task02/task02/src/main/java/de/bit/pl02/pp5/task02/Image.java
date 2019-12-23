package de.bit.pl02.pp5.task02;

import java.io.*;
import java.sql.*;

public class Image {
	   private byte[] readFile(String file) {
	        ByteArrayOutputStream bos = null;
	        try {
	            File f = new File(file);
	            FileInputStream fis = new FileInputStream(f);
	            byte[] buffer = new byte[1024];
	            bos = new ByteArrayOutputStream();
	            for (int len; (len = fis.read(buffer)) != -1;) {
	                bos.write(buffer, 0, len);
	            }
	        } catch (FileNotFoundException e) {
	            System.err.println(e.getMessage());
	        } catch (IOException e2) {
	            System.err.println(e2.getMessage());
	        }
	        return bos != null ? bos.toByteArray() : null;
	    }
	   private Connection connect() {
	        // SQLite connection string
	        String url = "jdbc:sqlite:images2.db";
	        Connection conn = null;
	        try {
	            conn = DriverManager.getConnection(url);
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	        return conn;
	    }
	    public void updatePicture(String Id, String filename) {
	        // update sql
	        String updateSQL = "UPDATE IMAGES " + "SET PICTURE =?"
	                + "WHERE id=?";
	 
	        try  {
	        	Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(updateSQL); 
	            // set parameters
	            pstmt.setBytes(1, readFile(filename));
	            pstmt.setString(2, Id);
	 
	            pstmt.executeUpdate();
	            System.out.println("Stored the file in the BLOB column.");
	 
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	    }
	    public static void main(String[] args) {
	    	File dir = new File("//Users//shreyakapoor//Desktop//PP5"); 
	    	File[] filesindir = dir.listFiles();
	    	if (filesindir!= null) { 
	    		for (File child: filesindir )
	    		{ 	String filename = child.getName(); 
	    			if (filename.contains("jpg") || filename.contains("png") || filename.contains("jpeg")){
	    				Image app = new Image();
	    				// how to put the id of the images!
	    				app.updatePicture(filename, child.getAbsolutePath());
	    			} 
	    		} 
	    	} 
	    }


}
