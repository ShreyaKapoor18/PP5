package de.bit.pl02.pp5.task02;
import de.bit.pl02.pp5.task02.Image; 
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;


/** A class of a database to store images in binary format and their meta information.
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
<<<<<<< HEAD
<<<<<<< HEAD
	
	public static void main(String[] args) throws SQLException
=======
	public static void main(String[] args) throws SQLException, IOException
>>>>>>> 9d279c02eb75411f54caf72eead3fa50e65f435f
	{ 
=======
	/** the name of the database */
	private String name;
	/** TODO (giving an error when something goes wrong with the database) */
	private int error_code;  
	/** Connection to the SQLite database file*/
	private Connection con;  
	
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
	public Connection Connect_db() throws SQLException
	{ // connecting to the database whether existing or not existing!
>>>>>>> c08156299ec19e025ebbd1ad89c7996d3ad330fe
		Connection con = null; 
		try {
			
			Class.forName("org.sqlite.JDBC"); 
<<<<<<< HEAD
			con = DriverManager.getConnection("jdbc:sqlite:test.db"); 
			try {
<<<<<<< HEAD
				
			// Check if TABLE Images is already present 
			Statement smt = con.createStatement(); 
			String presence = "SELECT * FROM sqlite_master WHERE type='TABLE' AND name='IMAGES'"; 
			boolean present = smt.execute(presence);
			
			// Create Table Images with columns 
			if (!present) {
			String sql = "CREATE TABLE IMAGES " +
				       "(ID TEXT PRIMARY KEY NOT NULL,"+ 
				       "TITLE   TEXT NOT NULL, " + 
				       "AUTHOR TEXT NOT NULL)";
		   smt.execute(sql); 
		   
		   // Add Picture column of type blob to table 
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
=======
				Statement smt = con.createStatement(); 
				String presence = "SELECT count(name) FROM sqlite_master WHERE type='TABLE' AND name='IMAGES'"; 
				smt.execute(presence);				
			// look for a method to put metadata in these columns using the metadata file. 
>>>>>>> 9d279c02eb75411f54caf72eead3fa50e65f435f
			   
			   ResultSet rs = smt.executeQuery("SELECT * from IMAGES"); 
			   	while (rs.next())
			   	{ 
				   String x = rs.getString("ID"); 
				   String s = rs.getString("TITLE");
				   System.out.println(x+" "+s);   
			   	}
			}catch(Exception e) {
				Statement smt = con.createStatement(); 
				String sql = "CREATE TABLE IMAGES " +
						       "(ID TEXT PRIMARY KEY NOT NULL,"+ 
							"TITLE   TEXT NOT NULL, AUTHOR TEXT NOT NULL)";
				smt.execute(sql); 
				 String sql1 = "ALTER TABLE IMAGES ADD COLUMN PICTURE blob"; 
				 smt.execute(sql1); 
				 System.out.println(sql1); 
				 readmetadata("//Users//shreyakapoor//Desktop//PP5"); 
				e.printStackTrace();
			}
=======
			con = DriverManager.getConnection("jdbc:sqlite:" + this.name + ".db"); 
			return con; 
			
>>>>>>> c08156299ec19e025ebbd1ad89c7996d3ad330fe
		} catch (ClassNotFoundException e) { 
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
					continue; // if there is an erro means it does contain the columns etc.
				}
			stmt.close(); 
			}
		}catch(Exception e){
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
		commands.add("ALTER TABLE IMAGES PICTURE ADD COLUMN PICTURE blob"); // add a column so that pictures can be stored there.
		
		return commands; 
		
	}
<<<<<<< HEAD
<<<<<<< HEAD
	
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
	public static void readmetadata(String dir, Statement smt) throws IOException, SQLException
	{ 
=======
	public static void readmetadata(String dir) throws IOException, SQLException, ClassNotFoundException
	{ 	Class.forName("org.sqlite.JDBC"); 
		Connection con = DriverManager.getConnection("jdbc:sqlite:test.db");
		Statement smt = con.createStatement();
		smt.execute("SELECT * FROM IMAGES"); 
>>>>>>> 9d279c02eb75411f54caf72eead3fa50e65f435f
=======

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
>>>>>>> c08156299ec19e025ebbd1ad89c7996d3ad330fe
		File dir1 = new File(dir); 
    	File[] filesindir = dir1.listFiles();
    	ArrayList<String> arr = new ArrayList<String>();
    	for (File f: filesindir)
    	{ 	String imgname = f.getName();
    		if (imgname.contains(".png")|| imgname.contains(".jpg") || imgname.contains(".jpeg")){ 
    			Image img = new Image(f.getAbsolutePath(), imgname); 
    			ArrayList<String> meta = img.find_metadata(f.getAbsolutePath()); 
    			arr.add("INSERT INTO IMAGES (ID,TITLE, AUTHOR)" + "VALUES ('"+ meta.get(0)+ "','"+ meta.get(1) + "'," + meta.get(2) +"')"); 
    			try {
    				String id = "'"+ meta.get(0)+ "'"; 
    				String author = "'"+ meta.get(2)+ "'"; 
    				String title = "'"+ meta.get(1)+ "'"; 
    				System.out.println("inserting the image metadata here!" + id + title + author ); 
    		
    				String sql = "INSERT INTO IMAGES (ID,TITLE, AUTHOR) VALUES ("+ id + "," + title +  "," + author + ")";
    				smt.execute(sql);
    				System.out.println(sql); 
    			}catch (Exception e) {
    				System.out.println(e.getMessage()); 
    				//continue; // only this particular image could not be inserted into the database.
    			}
    			}
    	}
    	System.out.println("inserting images");

    	return arr; 
	}
	
	/** Takes a String with value of column AUTHOR and return the blob contained
	 * in column PICTURE blob 
	 * 
	 * @param value		the String of the value of column AUTHOR or TITLE in the database
	 * @return bImage	blob of input image
	 * @throws SQLException
	 * 
	 * TODO handle multiple hits, with ArrayList<byte[]> ?
	 */
	public InputStream get_byteImage(String value, String column_name) {
		Statement stmt = this.con.createStatement();
		String query = "SELECT PICTURE blob FROM TABLE IMAGES WHERE "
				+ column_name +
				" LIKE " + value;
		ResultSet rs = stmt.executeQuery(query);
		// TODO Handle multiple hits 
		//while (rs.next()) {
		try {
			InputStream bImage = rs.getBinaryStream("PICTURE blob");
			return bImage;
			}catch (Exception e) {
			System.out.println(e.getMessage()); 
	    } finally {
	        if (stmt != null) { stmt.close(); }}
	}
	    
    /** Takes a String with value of column AUTHOR and TITLE and return the byte array contained
	 * in column PICTURE blob as a generator 
	 * 
	 * @param column_author	the String of the value of column AUTHOR 
	 * @param column_title		the String of the value of column TITLE 
	 * @return bImage			the byte array of the image
	 * @throws SQLException
	 * 
	 * 
	 * TODO handle multiple hits, with ArrayList<byte[]> ?
	 */
	public InputStream get_byteImage2(String column_author, String column_title) {
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
	}

	    
	/** Takes value of AUTHOR or TITLE column and saves the
	 * corresponding metadata as a .txt file
	 * 
	 * @param value			the value of the specified column
	 * @param column_name	either AUTHOR or TITLE
	 * @throws IOException
	 */
	public void get_meta(String value, String column_name) {
		Statement stmt = this.con.createStatement();
		// create temporary database without PICTURE blob column
		String query1 = "SELECT * INTO TempTable FROM IMAGES";
		String query2 = "ALTER TABLE TempTable";
		String query3 = "DROP COLUMN PICTURE blob";
		String query = "SELECT * FROM TempTable WHERE " +
				column_name + " LIKE " + value;
		//String query5 = "DROP TABLE TempTable";
		ResultSet rs = stmt.executeQuery(query);
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
		  
	}

	
	/** Main method
	 * TODO 
	 * @param args	
	 * 
	 */
	public static void main(String[] args) throws SQLException
	{ 
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
		}
	}

}
