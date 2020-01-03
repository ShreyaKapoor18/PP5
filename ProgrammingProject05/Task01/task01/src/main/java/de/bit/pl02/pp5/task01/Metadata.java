package de.bit.pl02.pp5.task01;
import java.io.Serializable;

/** This calss is used so that an object can be written and 
 * extracted from text files. This makes the retrieval of the 
 * metadata standardised.
 * 
 * @author Shreya Kapoor 
 *
 *
 */

public class Metadata implements Serializable {
	private static final long serialVersionUID = 1L;
	private String title; 
	private String author; 
	private String database;
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

