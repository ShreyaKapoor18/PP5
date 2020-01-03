package de.bit.pl02.pp5.task02;
import de.bit.pl02.pp5.task02.*;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
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
	
	/** image in byte array format to be stored in database column PICTURE blob */
	private byte[] blob; 
	/** the name of the image**/
	private String name; 
	/** the path of the image in the machine **/ 
	private String path;
	/** Class constructor
	 * 
	 * @param path	The location of the image in the file system
	 * @param name  The name of the image file.
	 */
	
	Image(String path, String name) {
		this.blob = readFile(path); 
		this.name = name; 
		this.path = path; 
	}

	/**Reads in an image file and converts it to its binary format
	 * so that it can be entered into the database. 
	 * Returns byte array of input image if not null
	 * 
	 * @param file	the file path of the .jpg or .png file
	 * @return bos 	the byte array of the input image
	 */
	  public byte[] readFile(String file) {
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
		String metapath = this.path.split("\\.(?=[^\\\\.]+$)")[0]+ ".meta";
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
	

