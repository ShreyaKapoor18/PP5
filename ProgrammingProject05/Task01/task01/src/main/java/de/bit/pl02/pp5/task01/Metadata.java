package de.bit.pl02.pp5.task01;
import java.io.Serializable;

/** This class is used so that an object can be written and 
 * extracted from text files. This makes the retrieval of the 
 * metadata standardised.
 * 
 * @author Shreya Kapoor 
 *
 *
 */

public class Metadata implements Serializable {
	private static final long serialVersionUID = 1L;
	/** Title of the image/article related to it **/
	private String title; 
	/** Name of the author related to the image or article **/
	private String author; 
	/** The database from where the image came **/
	private String database;
	/** What is the information about **/ 
	private int infographic; 
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getDatabase() {
		return database;
	}
	public void setDatabase(String database) {
		this.database = database;
	}
	public int getInfographic() {
		return infographic;
	}
	public void setInfographic(int infographic) {
		this.infographic = infographic;
	} 
	
	@Override
	public String toString() {
		return "Title:" + title + "\nAuthor:" + author + "\nDatabase: " + database + "\nInfographic";
	}
	
	
	
}

