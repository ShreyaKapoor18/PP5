package de.bit.pl02.pp5.task02;
import de.bit.pl02.pp5.task02.*; 
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.CommandLine; 



/** A class which can create database objects and helps to interact 
 *  with them. 
 * 	Columns: 
 * 		AUTHOR			the name of the author
 * 		TITLE			the name of the title
 * 		PICUTRE blob	the image as a byte array
 * 
 * @author Shreya Kapoor
 * @author Sophia Krix
 * @author Gemma van der Voort 
 * 
 * @since 1.8.0_231 // JKD Version
 */

public class Database {

	/** the name of the database */
	private String name;
	private int error_code; // giving an error when something goes wrong with the database. 
	private Connection con; 
	private static int id; 
	private static int dir_count; // count of how many directories have been added
	  
	
	/** Constructor method
	 * 
	 * @param name	the name of the database
	 */
	Database(String name) {

		this.name = name;
		try {
			this.con = this.Connect_db();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	/** Establishes a connection to the SQLite database
	 * 
	 * @return con connection to the database
	 */
	public Connection Connect_db() throws SQLException { 
		// connecting to the database whether existing or not existing!
		Connection con = null; 
		try {
			Class.forName("org.sqlite.JDBC"); 
			con = DriverManager.getConnection("jdbc:sqlite:" + this.name + ".db"); 
			try {
			// Check if TABLE Images is already present 
			Statement smt = con.createStatement(); 
			String presence = "SELECT * FROM sqlite_master WHERE type='TABLE' AND name='IMAGES'"; 
			boolean present = smt.execute(presence);
			
			   
			   ResultSet rs = smt.executeQuery("SELECT * from IMAGES"); 
			   	while (rs.next())
			   	{ 
				   String x = rs.getString("ID"); 
				   String s = rs.getString("TITLE");
				   System.out.println(x+" "+s);   
			   	}
			} catch(Exception e) {
				Statement smt = con.createStatement(); 
				String sql = "CREATE TABLE IMAGES " +
						       "(ID TEXT PRIMARY KEY NOT NULL,"+ 
							"TITLE   TEXT NOT NULL, AUTHOR TEXT NOT NULL)";
				smt.execute(sql); 
				 String sql1 = "ALTER TABLE IMAGES ADD COLUMN PICTURE blob"; 
				 smt.execute(sql1); 
				 System.out.println(sql1); 
				 //readmetadata("//Users//shreyakapoor//Desktop//PP5"); 
				
			}

			con = DriverManager.getConnection("jdbc:sqlite:" + this.name + ".db"); 
			return con; } catch (ClassNotFoundException e) { 
				e.printStackTrace();
				return null; 
			}
		}
		
	/** Creates a table as a database
	 *  TODO is this complete?
	 */
	public void make_table()
	{ 
		try {
			Statement stmt = this.con.createStatement(); // try to query with the help of certain statements.
			ArrayList<String> sqls = this.insert_fields(); 
			for (String sql: sqls) { 
				try { 
				// See if these are getting added or not.
				System.out.println("create tables!"); 
				stmt.execute(sql); } 
				catch (SQLException e) {
					//System.out.println(sql + " got error with the query"); 
					continue; // if there is an erro means it does contain the columns etc.
				}
			stmt.close(); 
			}
		} catch(Exception e){
			e.printStackTrace();
			System.out.println(e.getMessage()); 
		}
	}
	
	/** Creates SQL commands to create a table with columns ID, AUTHOR, TITLE, PICTURE blob
	 * 
	 * @return commands	the SQL commands 
	 */
	public ArrayList<String> insert_fields()
	{ 	System.out.println("adding the fields"); 
		// arraylist of SQL commands which can be given to the program so that the execution gets up and running. 
		ArrayList<String> commands = new ArrayList<String>();
		commands.add("CREATE TABLE IF NOT EXISTS IMAGES " +"(ID TEXT PRIMARY KEY NOT NULL,"+ "TITLE   TEXT NOT NULL, AUTHOR TEXT NOT NULL)"); 
		commands.add("ALTER TABLE IMAGES PICTURE ADD IF NOT EXISTS COLUMN PICTURE blob"); // add a column so that pictures can be stored there.
		
		return commands; 	
	}

	/**
	 * Reads in the files of the given directory.
	 * Creates an unique ID consisting of Author, Title and Filename
	 * (if the metadata files contain the String "Title" or "Author, 
	 * their value is used for the ID).
	 * Inserts the values of ID, Title and Author into the TABLE Images 
	 * 
	 * @param dir is the path for the folder in which the metadata files are located
	 * @param smt is an instance of Connection.createStatement() 
	 */

	public void readmetadata(String dir) throws IOException, SQLException, ClassNotFoundException { 	
		Class.forName("org.sqlite.JDBC"); 
	
		Connection con = DriverManager.getConnection("jdbc:sqlite:" + this.name + ".db");
		Statement smt = con.createStatement();
		smt.execute("SELECT * FROM IMAGES"); 
	}

	/** Prints the values of the table IMAGES
	 *  of column ID, TITLE and AUTHOR
	 * 
	 * @throws SQLException
	 */
	public void see_table() throws SQLException
	{ 	System.out.println("printing"); 
		Statement smt = this.con.createStatement(); 
		ResultSet rs = smt.executeQuery("SELECT * from IMAGES"); 
	   	while (rs.next())
	   	{  System.out.println("result set!"); 
		   String x = rs.getString("ID"); 
		   String s = rs.getString("TITLE");
		   String a = rs.getString("AUTHOR"); 
		   System.out.println(x+" "+s);   
	   	}
	   	ResultSet rs2 = smt.executeQuery("PRAGMA table_info('IMAGES')"); 
	   	while (rs2.next())
	   	{  //System.out.println("result set2!");    
	   	}
	   	smt.close(); 
	   
	}

	/** Reads the files of a directory, finds the images and its corresponding metadata
	 * Inserts the metadata of ID, TITLE and AUTHOR into the database
	 * 
	 * @param dir	the path of the directory 
	 * @return arr	TODO 
	 * @throws Exception if image could not be inserted into database
	 */
	public ArrayList<String> read_director(String dir) throws SQLException
	{  
		Statement smt = this.con.createStatement(); 
		File dir1 = new File(dir); 
		System.out.println(dir); 
    	File[] filesindir = dir1.listFiles();
    	System.out.println(filesindir); 
    	ArrayList<String> arr = new ArrayList<String>();
    	for (File f: filesindir)
    	{ 	String imgname = f.getName();
    		System.out.println(imgname); 
    		if (imgname.contains(".png")|| imgname.contains(".jpg") || imgname.contains(".jpeg")){ 
    			Image img = new Image(f.getAbsolutePath(), imgname); 
    			ArrayList<String> meta = img.find_metadata(f.getAbsolutePath()); 
    			if (meta!= null) {
    			arr.add("INSERT INTO IMAGES (ID,TITLE, AUTHOR)" + "VALUES ('"+ meta.get(0)+ "','"+ meta.get(1) + "'," + meta.get(2) +"')"); 
    			try {
    				String id = "'"+ meta.get(0)+ "'"; 
    				String author = "'"+ meta.get(2)+ "'"; 
    				String title = "'"+ meta.get(1)+ "'"; 
    				System.out.println("inserting the image metadata here!" + id + title + author ); 
    		
    				String sql = "INSERT INTO IMAGES (ID,TITLE, AUTHOR) VALUES ("+ id + "," + title +  "," + author + ")";
    				smt.execute(sql);
    				updatePicture(img, id, f.getAbsolutePath()); 
    				System.out.println(sql); 
    			}catch (Exception e) {
    				System.out.println(e.getMessage()); 
    				//continue; // only this particular image could not be inserted into the database.
    			}
    		 }
    	  }
    	}
    	System.out.println("inserting images");

    	return arr; 
	}
	
	/** Updates the database with the new image
	   * 
	   * @param Id			the value of the id column in the database
	   * @param filename	the path of the image to be stored
	   */
	    public void updatePicture(Image img,String Id, String path) {
	        // update sql
	        String updateSQL = "UPDATE IMAGES " + "SET PICTURE =?"
	                + "WHERE id=?";
	        try  {
	 
            PreparedStatement pstmt = this.con.prepareStatement(updateSQL); 
	            // set parameters
	            pstmt.setBytes(1, img.readFile(path));
	            pstmt.setString(2, Id);
	 
	            pstmt.executeUpdate();
	            System.out.println("Stored the file in the BLOB column.");
	 
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	    }

	/** Takes a String with value of column AUTHOR and return the blob contained
	 * in column PICTURE blob 
	 * 
	 * @param value		the String of the value of column AUTHOR or TITLE in the database
	 */
	public void get_byteImage(String value, String column_name) {
		Statement stmt;
		try {
			stmt = this.con.createStatement();
			try {
				String query = "SELECT PICTURE blob FROM TABLE IMAGES WHERE "
						+ column_name +
						" LIKE " + value;
				ResultSet rs = stmt.executeQuery(query);
				InputStream bImage = rs.getBinaryStream("PICTURE blob");	
				// TODO Handle multiple hits 
				//while (rs.next()) {
			} catch (Exception e) {
				System.out.println(e.getMessage()); }			
		    finally {
		        if (stmt != null) { stmt.close(); }}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}	
		
		// TODO Why can it not return, because the datatype has an issue!		
		
	}
	
	
	    
    /** Takes a String with value of column AUTHOR and TITLE and return the byte array contained
	 * in column PICTURE blob as a generator 
	 * 
	 * @param column_author	the String of the value of column AUTHOR 
	 * @param column_title		the String of the value of column TITLE 
	 * @return bImage			the byte array of the image
	 * 
	 * 
	 * TODO handle multiple hits, with ArrayList<byte[]> ?
     * @throws SQLException 
	 */
	public InputStream get_byteImage2(String column_author, String column_title) throws SQLException {
		Statement stmt = this.con.createStatement();
		String query = "SELECT PICTURE blob FROM TABLE IMAGES WHERE AUTHOR LIKE " 
				+ column_author + " AND TITLE LIKE"  
				+ column_title;
		ResultSet rs = stmt.executeQuery(query);
		try {
			InputStream bImage = rs.getBinaryStream("PICTURE blob");
			return bImage;
			} catch (Exception e) {
				System.out.println(e.getMessage()); 
	    } finally {
	        if (stmt != null) { stmt.close(); }}
		return null;
	}

	    
	/** Takes value of AUTHOR or TITLE column and saves the
	 * corresponding metadata as a .txt file
	 * 
	 * @param value			the value of the specified column
	 * @param column_name	either AUTHOR or TITLE
	 * @throws IOException
	 */
	public void get_meta(String value, String column_name) {
		Statement stmt = null;
		try {
			stmt = this.con.createStatement();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		// create temporary database without PICTURE blob column
		String query1 = "SELECT * INTO TempTable FROM IMAGES";
		String query2 = "ALTER TABLE TempTable";
		String query3 = "DROP COLUMN PICTURE blob";
		String query = "SELECT * FROM TempTable WHERE " +
				column_name + " LIKE " + value;
		//String query5 = "DROP TABLE TempTable";
		ResultSet rs;
		try {
			rs = stmt.executeQuery(query);
			try {
				while (rs.next()) {
					String author = rs.getString("AUTHOR"); 
					String title = rs.getString("TITLE"); 
					String id = author.substring(0,3)+title.substring(0,3);
					String metadata = "Author: " + author + "\nTitle: " + title;
					
					BufferedWriter writer = new BufferedWriter(new FileWriter(id + ".txt", true));
				    writer.append(metadata); }
				} catch (IOException e) {
					System.out.println(e.getMessage());
			}
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		  
	}

	
	/** Main method
	 * TODO instance of CLI, get athor title from user use as input for Database query
	 * @param args	
	 * 
	 */
	public static void main(String[] args) throws SQLException
	{ 
		CommandLineInterface cli = new CommandLineInterface();
		/** create command line options */
		Options options = cli.make_options();
		/** parse command line for options */
		CommandLine cmd = cli.parse_commandline(options, args);
		
		/** Check command line options and do corresponding methods */
		if (cmd.hasOption("m")){
			cli.option_m();
		}
		
		if (cmd.hasOption("s")){
			cli.option_s();
		}
		
		if (cmd.hasOption("gia")){
			cli.option_gia();
		}
		
		if (cmd.hasOption("git")){
			cli.option_git();
		}
		
		if (cmd.hasOption("giat")){
			cli.option_giat();
		}
		
		if (cmd.hasOption("gma")){
			cli.option_gma();
		}
		
		if (cmd.hasOption("gmt")){
			cli.option_gmt();
		}
		
		/** Previous main method
		Scanner input = new Scanner(System.in); 
		System.out.println("Enter the name of the database you want to make/see"); 
		String name = input.nextLine(); 
		Database Db = new Database(name); 
		Db.make_table(); 
		//get the path of the directory where you want to insert all the images from!
		Scanner input2 = new Scanner(System.in); 
		System.out.println("Enter the file directory from which you want the images"); // also need to connect this to restful API
		String dir = input2.nextLine(); 
		input2.close();
		Db.read_director(dir); 
		try {
			Db.see_table(); // cam be done with the help of command line parameters, whether these options shall be present or not. 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}

}
