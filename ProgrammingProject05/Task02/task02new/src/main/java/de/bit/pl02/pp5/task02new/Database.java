package de.bit.pl02.pp5.task02new;
import de.bit.pl02.pp5.task02new.Image; 
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

public class Database {
	private String name;
	private int error_code; // giving an error when something goes wrong with the database. 
	private Connection con; 
	Database(String name)
	{ // this constructor method asks the user for the name of the database.
		this.name = name;
		try {
			this.con = this.Connect_db();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	public Connection Connect_db() throws SQLException
	{ // connecting to the database whether existing or not existing!
		Connection con = null; 
		try {
			Class.forName("org.sqlite.JDBC"); 
			con = DriverManager.getConnection("jdbc:sqlite:" + this.name + ".db"); 
			return con; 
			
		} catch (ClassNotFoundException e) { 
			e.printStackTrace();
			return null; 
		}
	}
	public void make_table()
	// TO DO handle if table already exists 
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
	public ArrayList<String> insert_fields()
	{ 	System.out.println("adding the fields"); 
		// arraylist of SQL commands which can be given to the program so that the execution gets up and running. 
		ArrayList<String> commands = new ArrayList<String>();
		commands.add("CREATE TABLE IF NOT EXISTS IMAGES " +"(ID TEXT PRIMARY KEY NOT NULL,"+ "TITLE   TEXT NOT NULL, AUTHOR TEXT NOT NULL)"); 
		commands.add("ALTER TABLE IMAGES PICTURE ADD COLUMN PICTURE blob"); // add a column so that pictures can be stored there.
		
		return commands; 
		
	}
	
	
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
	
	/** Takes a String with value of column AUTHOR and return the byte array contained
	 * in column PICTURE blob as a generator 
	 * 
	 * @params value is the String of the value of column AUTHOR or TITLE in the database
	 * @throws SQLException
	 * 
	 * TODO handle multiple hits, with ArrayList<byte[]> ?
	 */
	public byte[] get_byteImage(String value, String column_name) {
		Statement stmt = this.con.createStatement();
		String query = "SELECT PICTURE blob FROM TABLE IMAGES WHERE "
				+ column_name +
				" LIKE " + value;
		ResultSet rs = smt.executeQuery(query);
		// TODO Handle multiple hits 
		//while (rs.next()) {
		try {
			byte[] bImage = rs.getBinaryStream("PICTURE blob");
			return bImage;
		 catch (SQLException e ) {
	        JDBCTutorialUtilities.printSQLException(e);
	    } finally {
	        if (stmt != null) { stmt.close(); }
	}
	    
    /** Takes a String with value of column AUTHOR and TITLE and return the byte array contained
	 * in column PICTURE blob as a generator 
	 * 
	 * @params column_author is the String of the value of column AUTHOR 
	 * @params column_title is the String of the value of column TITLE 
	 * @throws SQLException
	 * 
	 * TODO handle multiple hits, with ArrayList<byte[]> ?
	 */
	public byte[] get_byteImage2(String column_author, String column_title) {
		Statement stmt = this.con.createStatement();
		String query = "SELECT PICTURE blob FROM TABLE IMAGES WHERE AUTHOR LIKE " 
				+ column_author + " AND TITLE LIKE"  
				+ column_title;
		ResultSet rs = stmt.executeQuery(query);
		try {
			byte[] bImage = rs.getBinaryStream("PICTURE blob");
			return bImage;
		 catch (SQLException e ) {
	        JDBCTutorialUtilities.printSQLException(e);
	    } finally {
	        if (stmt != null) { stmt.close(); }
	}

	    
	/** Takes value of AUTHOR or TITLE column and saves the
	 * corresponding metadata as a .txt file
	 * 
	 * @params value is the value of the specified column
	 * @column_name is either AUTHOR or TITLE
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
		while (rs.next())
	   	{  String author = rs.getString("AUTHOR"); 
		   String title = rs.getString("TITLE");   
	   	}
		String id = author.substring(0,3)+title.substring(0,3);
		String metadata = "Author: " + author + "\nTitle: " + title;
		FileUtils.writeStringToFile(new File(id+".txt"), metadata);
	}
	    
	/** Reads the files of a directory, finds the images and its corresponding metadata
	 * Inserts the metadata of ID, TITLE and AUTHOR into the database
	 * 
	 * @params dir is the path of the directory 
	 * @throws Exception if image could not be inserted into database
	 */
	public ArrayList<String> read_director(String dir) throws SQLException
	{  
		Statement smt = this.con.createStatement(); 
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
	public static void main(String[] args) throws SQLException
	{ 
		// TO DO command line interface instance 
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
