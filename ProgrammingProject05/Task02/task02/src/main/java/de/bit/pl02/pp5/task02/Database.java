package de.bit.pl02.pp5.task02;
import de.bit.pl02.pp5.task02.*; 
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.stream.BaseStream;

import org.apache.commons.lang3.StringUtils;
import org.omg.CORBA.portable.InputStream;

import java.io.IOException;
import java.sql.Blob;


/** The class Database can be used to create database objects and helps the user to interact
 *  with them. The user can give a directory in the {@link #read_director(String)} method from which
 *  a table will be created with the images and the corresponding metadata. 
 *  It is possible to print the values of the created table with the method {@link #see_table()}.
 *  If the user only wants the metadata of a specific sample, then the metadata can be retrieved by specifying
 *  either the author or the title with the {@link #get_meta(String, String)} method and be saved as a .txt file.
 *  The user can add images with the {@link #updatePicture(Image, String, String)} method to the table.
 *  
 * 	Columns: 
 * 		AUTHOR			the name of the author
 * 		TITLE			the name of the title
 * 		LINK			the URL related to the image
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
	private int id = 0; // start the id with 1 for each database
	private int dir_count = 0; // count of how many directories have been added
	  
	
	/** Constructor method
	 * 
	 * @param name	the name of the database
	 */
	Database(String name) {

		this.name = name;
		try {
			this.con = this.Connect_db();
		} catch (SQLException e) {
			
			System.out.println(StringUtils.repeat("=", 20) + " ERROR " + StringUtils.repeat("=", 20)); 
			System.out.println(" Could not connect to the database using predefined method"); 
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
			String count_query = "SELECT COUNT(*) from 'IMAGES'"; 
			ResultSet r1 = smt.executeQuery(count_query); 
			//r1.next(); 
			int count = r1.getInt("COUNT(*)"); 
			System.out.println("The database currently contains " + count + " elements"); 
			int id = count; 
			
			} catch(Exception e) {
				Statement smt = con.createStatement(); 
				String sql = "CREATE TABLE IMAGES "
						    + "(ID INTEGER PRIMARY KEY NOT NULL,"
							+ "TITLE TEXT NOT NULL, "
							+ "AUTHOR TEXT NOT NULL, "
							+ "LINK TEXT NOT NULL);";
				smt.execute(sql); 
				 String sql1 = "ALTER TABLE IMAGES ADD COLUMN PICTURE blob"; 
				 smt.execute(sql1); 
				 //System.out.println(sql1);			
			}

			con = DriverManager.getConnection("jdbc:sqlite:" + this.name + ".db"); 
			return con; } catch (ClassNotFoundException e) { 
				System.out.println(StringUtils.repeat("=", 20) + " ERROR " + StringUtils.repeat("=", 20)); 
				System.out.println(" Could not find the class"); //TODO 
				return null; 
			}
		}
		
	/** Creates a table as a database
	 *@param tablename	the name of the database 
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
				//System.out.println("create tables!"); 
				stmt.execute(sql); } 
				catch (SQLException e) {
					System.out.println(sql + " got error with the query"); 
					e.printStackTrace(); 
					continue; // if there is an error means it does contain the columns etc.
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
	public ArrayList<String> insert_fields() { 	
		//System.out.println("adding the fields"); 
		// arraylist of SQL commands which can be given to the program so that the execution gets up and running. 
		ArrayList<String> commands = new ArrayList<String>();
		//TODO IMAGES
		commands.add("CREATE TABLE IF NOT EXISTS IMAGES "
				+ "(ID INTEGER PRIMARY KEY NOT NULL,"
				+ "TITLE TEXT NOT NULL, "
				+ "AUTHOR TEXT NOT NULL, "
				+ "LINK TEXT NOT NULL, PICTURE blob );"); 
		// commands.add("ALTER TABLE IMAGES PICTURE ADD COLUMN PICTURE blob;"); // add a column so that pictures can be stored there.
		
		return commands; 	
	}

	/** Selects all the facts from the images, don't actually need this
	 * 
	 * Reads in the files of the given directory.
	 * 
	 * @param dir		the path for the folder in which the metadata files are located
	 * @param tablename the name of the database
	 */
	public void readmetadata(String dir) throws IOException, SQLException, ClassNotFoundException { 	
		// Class.forName("org.sqlite.JDBC");  no need to use this
		// Connection con = DriverManager.getConnection("jdbc:sqlite:" + this.name + ".db");
		Statement smt = this.con.createStatement();
		smt.execute("SELECT * FROM IMAGES"); 
	}

	/** Prints the values of the table IMAGES of column ID, TITLE and AUTHOR.
	 *  TODO check after creating integer ID if this is still valid
	 * 
	 * @throws SQLException
	 */
	public void see_table() throws SQLException
	{ 	//System.out.println("printing"); 
		Statement smt = this.con.createStatement(); 
		ResultSet rs = smt.executeQuery("SELECT * from IMAGES"); 
	   	while (rs.next())
	   	{  //System.out.println("result set!"); 
		   String x = rs.getString("ID"); 
		   String s = rs.getString("TITLE");
		   String a = rs.getString("AUTHOR"); 
		   String l = rs.getString("LINK");
		   System.out.println("Printing table:");
		   System.out.println("ID: "+x+"\nAUTHOR: "+a+"\nTITLE: "+s+"\nLINK: "+l);   
	   	}
	   	ResultSet rs2 = smt.executeQuery("PRAGMA table_info('IMAGES')"); 
	   	while (rs2.next())
	   	{  
	   		//System.out.println("result set2!");    
	   	}
	   	smt.close(); 
	   
	}

	/** Reads the files of a directory, finds the images and its corresponding metadata
	 * Inserts the metadata of ID, TITLE and AUTHOR into the database and inserts the 
	 * image into the PICTURE blob column via the {@link #updatePicture(Image, String, String)} method.
	 * TODO check after creating integer ID that this still works
	 * 
	 * @param dir	the path of the directory 
	 * @return arr	TODO 
	 * @throws Exception if image could not be inserted into database
	 */
	public ArrayList<String> read_director(String dir) throws SQLException
	{  
		Statement smt = this.con.createStatement(); 
		File dir1 = new File(dir); 
		System.out.println("Directory: "+dir); 
    	File[] filesindir = dir1.listFiles();
    	//System.out.println("Files: \n"+filesindir); 
    	ArrayList<String> arr = new ArrayList<String>();
    	for (File f: filesindir)
    	{ 	String imgname = f.getName();
    		System.out.println("Imagename: "+imgname); 
    		if (imgname.contains(".png")|| imgname.contains(".jpg") || imgname.contains(".jpeg")){ 
    			Image img = new Image(f.getAbsolutePath(), imgname);
    			id +=1; 
    			ArrayList<String> meta = img.find_metadata(f.getAbsolutePath()); 
    			if (meta!= null) {
    			arr.add("INSERT INTO IMAGES (ID,TITLE, AUTHOR, LINK)" + "VALUES ('"+ id + "','"+ meta.get(0) + "'," + meta.get(1) +"','"+meta.get(2)+ "')"); 
    			try {
    				String author = "'"+ meta.get(1)+ "'"; 
    				String title = "'"+ meta.get(0)+ "'"; 
    				String link = "'"+ meta.get(2)+ "'";
    				//System.out.println("inserting the image metadata here!" + id + title + author ); 
    				String sql = "INSERT INTO IMAGES (ID,TITLE, AUTHOR, LINK) VALUES ("+ id + "," + title +  "," + author + "," + link + ")";
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
    	//System.out.println("inserting images");

    	return arr; 
	}
	
	/** Updates the database with the new image by using the method {@link Image#readFile(String)}
	 * to read in an image file and store it as a byte array.
     * TODO check ID
     * @param Id			the value of the id column in the database
     * @param filename	the path of the image to be stored
     */
	public void updatePicture(Image img, int Id, String path) {
	    // update sql
	    String updateSQL = "UPDATE IMAGES " + "SET PICTURE =?"
	            + "WHERE id=?";
	        try  {
	 
	        PreparedStatement pstmt = this.con.prepareStatement(updateSQL); 
	            // set parameters
	            pstmt.setBytes(1, img.readFile(path));
	            pstmt.setInt(2, Id);
	            pstmt.executeUpdate();
	            System.out.println("Stored the file in the BLOB column.");
	 
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	    }

	/** Takes a String with value of column AUTHOR and return the byte array contained
	 * in column PICTURE blob 
	 * 
	 * @param value		the String of the value of column AUTHOR or TITLE in the database
	 * @return 
	 */
	public void get_byteImage (String value, String column_name) {
		Statement stmt;
		byte[] bImage = null;
		try {
			stmt = this.con.createStatement();
			try {
				String query = "SELECT *,'"+value+"' AS "+ column_name+ " FROM IMAGES";
				ResultSet rs = stmt.executeQuery(query);  	
				System.out.println("executed:" + query);
				while (rs.next()) { 
					//Blob blob = rs.getBlob("PICTURE");
					System.out.println("executed: get binary stream ");
					String id = rs.getString("ID");
					File image = new File(id + ".png");
				    FileOutputStream fos = new FileOutputStream(image);
				    byte[] buffer = new byte[1];
				    java.io.InputStream is = rs.getBinaryStream("PICTURE");
				      while (is.read(buffer) > 0) {
				        fos.write(buffer);
				      }
				    fos.close();
					//int blobLength = (int) blob.length();
					//bImage = blob.getBytes(1, blobLength);	
					
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
				}			
		    finally {
		        if (stmt != null) { stmt.close();
		        }}
		} catch (SQLException e1) {
			e1.printStackTrace();	
		}	
		//return bImage != null ? bImage : null;
	}
	  

    /** Takes a String with value of column AUTHOR and TITLE and return the byte array contained
	 * in column PICTURE blob 
	 * 
	 * @param column_author	the String of the value of column AUTHOR 
	 * @param column_title		the String of the value of column TITLE 
	 * @return bImage			the byte array of the image
	 * 
	 * 
	 * TODO handle multiple hits, with ArrayList<byte[]> ?
     * @throws SQLException 
	 */
	public byte[] get_byteImage2(String column_author, String column_title) throws SQLException {
		Statement stmt = this.con.createStatement();
		byte[] bImage = null;
		String query = "SELECT PICTURE blob FROM TABLE IMAGES WHERE AUTHOR LIKE " 
				+ column_author + " AND TITLE LIKE"  
				+ column_title;
		ResultSet rs = stmt.executeQuery(query);
		try {
			while (rs.next()) { 
				Blob blob = rs.getBlob("PICTURE blob");
				int blobLength = (int) blob.length();
				bImage = blob.getBytes(1, blobLength);					
			}
			} catch (Exception e) {
				System.out.println(e.getMessage()); 
	    } finally {
	        if (stmt != null) { stmt.close(); }}
		return bImage != null ? bImage : null;
	}
    
	/** Takes value of AUTHOR or TITLE column and saves the
	 * corresponding metadata as a .txt file
	 * 
	 * @param value			the value of the specified column
	 * @param column_name	either AUTHOR or TITLE
	 * @param tablename		the name of the database
	 * @throws IOException
	 */
	public void get_meta(String value, String column_name) {
		Statement stmt = null;
		try {
			stmt = this.con.createStatement();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		String query = "SELECT *,'"+value+"' AS "+ column_name+ " FROM IMAGES"; 
		// System.out.println(query); 
		ResultSet rs;
		try {
			rs = stmt.executeQuery(query);
			try {
				while (rs.next()) {
					String author = rs.getString("AUTHOR"); 
					String title = rs.getString("TITLE"); 
					String link = rs.getString("LINK");	
					String metadata = "Author: " + author + "\nTitle: " + title + "\nLink: " + link;
					System.out.println("Author: " + author + "\nTitle: " + title + "\nLink: " + link);
		
					//BufferedWriter writer = new BufferedWriter(new FileWriter(author+id + ".txt", true));
				    //writer.append(metadata);
				    FileWriter writer = new FileWriter(author+id + ".txt");
				    try {
				        BufferedWriter buff = new BufferedWriter(writer);
				        try {
				                buff.append(metadata);
				            }
				        finally {
				            buff.flush();
				            buff.close();
				        }
				    } finally {
				        writer.close();}
				    }

				} catch (IOException e) {
					System.out.println(e.getMessage());
				}	
		 catch (SQLException e1) {
			e1.printStackTrace();
		 	}	  
		} catch (SQLException e2) {
			e2.printStackTrace();
		} 
	
	}
}
	
	


