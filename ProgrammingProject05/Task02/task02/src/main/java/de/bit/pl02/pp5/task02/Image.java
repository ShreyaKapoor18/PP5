package de.bit.pl02.pp5.task02;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/** Image is the class of images used to read in .jpg, .jpeg or .png files
 *  and to store these in a byte array in a database. These byte arrays can 
 *  be converted back again into .jpg files and stored locally.
 * 
 * @author Shreya Kapoor
 * @author Sophia Krix
 * @author Gemma van der Voort 
 * 
 * @since 1.8.0_231 // JKD Version
 * 
 */
public class Image {
	
	// TODO put attributes first? 
	
<<<<<<< HEAD



	/**Reads in an image file and converts it to its binary format
	 * Returns byte array of input image if not null
	 * 
	 * @param file	the file path of the .jpg or .png file
	 * @return bos 	the byte array of the input image
	 */
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

	/** Establishes a connection to the SQLite database file images2 
	 * via the Java Database Connectivity (JDBC)
	 * 
	 * @return conn	the connection to the database
	 */
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
	   
	  /** Updates the database with the new image
	   * 
	   * @param Id			the value of the id column in the database
	   * @param filename	the path of the image to be stored
	   */
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
	    
	   /** Main method
	    *  
	    * ???
	    * 
	    * @param args
	    */
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

=======

	/** image in byte array format to be stored in database column PICTURE blob */
	private byte[] blob; 
	/** TODO ??? */
	private String name; 
	/** Name of the file path */
	private String path; 
	
	/** Class constructor
	 * 
	 * @param path	the name of the file path of the image
	 * @param name	??? TODO 
	 */
	Image(String path, String name)
	{
		this.blob = readFile(path); 
		this.name = name; 
		this.path = path; 
	}
	
	/** Reads in all .meta files in a folder with specified path
	 *  and returns the meta information found about author and title
	 *  together with the constructed id in a String array.
	 * 
	 * we consider that we follow the same path for metadata files and the actual image files 
	 * so we can just strip off the . in the filename and then add .meta infront of it in order to get the
	 * related to a particular image.
	 * 
	 * @param path	the file path of the metadata 
	 * @return meta a String array with the metadata about Id, title and author
	 */
	ArrayList<String> find_metadata(String path) {
		ArrayList<String> meta = new ArrayList<String>(); 
		String metapath = this.path.split("\\.")[0]+ ".meta";
		try { 
			FileReader filer = new FileReader(metapath);
			BufferedReader buffr = new BufferedReader(filer);
			boolean eof = false;
			String author = "xx"; 
			String title = "yyzz"; 
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
			    	title = s.split(":")[1]; }
			    else if (s.contains("Author:")) { 
			    	author = s.split(":")[1]; }
			   }
			}
			if ( author.length()>=2 && title.length()>=5){
				Id = author.substring(0, 2)+title.substring(0,4)+this.name.split("\\.(?=[^\\.]+$)")[0];
			} 
			else {
				Id = this.name.split("\\.(?=[^\\.]+$)")[0]; // dot followed by a number of non fos
			}
			meta.add(Id);
			meta.add(title); 
			meta.add(author); 
		
			} catch (Exception e) {
			/* if there existed none of such files then probably some 
			 default values shall be set and passed on in the given fields.
			 */
			meta.add(this.name.split("\\.(?=[^\\.]+$)")[0]); 
			meta.add("xx"); 
			meta.add("yyzz"); 
		}
		return meta;
	}
	
	
}
	
>>>>>>> c08156299ec19e025ebbd1ad89c7996d3ad330fe

}
