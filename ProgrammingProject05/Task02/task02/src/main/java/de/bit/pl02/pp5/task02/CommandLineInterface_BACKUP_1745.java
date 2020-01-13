package de.bit.pl02.pp5.task02;
import de.bit.pl02.pp5.task02.*; 

import org.apache.commons.cli.Option; 
import org.apache.commons.cli.Options;
import org.apache.commons.cli.CommandLine; 
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter; 
import org.apache.commons.cli.ParseException;
import java.io.*; 
import java.sql.SQLException;


/** The class CommandLineInterface provides an interface for the user and helps to interact.
 * 	With this, it is possible to create a database on the basis of the directory given by the user.
 *  This directory should contain image files (.png, .jpg or .jpeg) and corresponding metadata files (.txt).
 *  The user can query the database by giving the name of the author or title to retrieve an image or
 *  additional metadata information.
 * 
 * 
 * @author Shreya Kapoor
 * @author Sophia Krix
 * @author Gemma van der Voort 
 */
public class CommandLineInterface {
	
	/** the command line to receive arguments from the user */
	static CommandLine cmd;
	
	/** Constructor method */
	CommandLineInterface() {

	}
	
	/** Creates command line options to make a database, store images in it
	 * and retrieve image and metadata information
	 * 
	 * @return options,	the command line arguments
	 */
	public static Options make_options() {
		Options options = new Options(); 
		Option name = new Option("n", "name", true, "Enter the name of the database you want to make/see");
		//directory
		Option directory = new Option("d", "directory", true, "Enter the file directory from which you want to store the images"); 
		Option getImagebyAuthor = new Option("gia", "getImagebyAuthor", true, "Enter the name of the author from which you want the image" );
		Option getImagebyTitle = new Option("git", "getImagebyTitle", true, "Enter the name of the title from which you want the image" ); 
		Option getMetabyAuthor = new Option("gma", "getMetabyAuthor", true, "Enter the name of the author of which you want to retrieve the metadata");
		Option getMetabyTitle = new Option("gmt", "getMetabyTitle", true, "Enter the name of the title of which you want to retrieve the metadata");
		Option print = new Option("p", "print", false, "Printing all content of the table");

		options.addOption(name); 
		options.addOption(directory); 
		options.addOption(getImagebyAuthor);
		options.addOption(getImagebyTitle);
		options.addOption(getMetabyAuthor);
		options.addOption(getMetabyTitle);
		options.addOption(print);

		name.setRequired(true);
		return options;
	}
	
	/** Parses command line options for arguments
	 * 	and returns the corresponding options and values
	 * 
	 * @param options	the command line options
	 * @param args		the values of the command line options
	 * @return cmd		list of atomic option and value tokens
	 */
	public static CommandLine parse_commandline(Options options, String[] args) {
		
		CommandLineParser parser = new DefaultParser(); 
		HelpFormatter formatter = new HelpFormatter(); 
		try { 
			cmd = parser.parse(options, args); 	
			} 
			catch (ParseException e) { 
			System.out.println(e.getMessage()); formatter.printHelp("parsingtest", options);
			System.exit(1);
			}
		return cmd; }
	
		
	/** Creates a database with the method {@link Database#make_table()}, 
	 *  reads the files of the specified directory and inserts the metadata of 
	 *  ID, TITLE and AUTHOR into the database with the method {@link Database#read_director(String)}.
	 * 
	 */		
	public static void option_s(){
		String dir = cmd.getOptionValue("directory");
		String name = cmd.getOptionValue("name");
		Database Db = new Database(name); 
		Db.make_table();
		try {
			Db.read_director(dir);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
		
	/** Retrieve an image from the database by author with the {@link Database#get_byteImage(String, String)}
	 *  and save as .png file with the specified path by the user. 
	 *  
	 */
	public static void option_gia(){ 
		String author = cmd.getOptionValue("getImagebyAuthor");
		String name = cmd.getOptionValue("name");
		Database Db = new Database(name);
		Db.get_byteImage("AUTHOR", author);
	}
		
	/** Retrieve an image from the database by title and save as .png file.
	 * 
	 */
	public static void option_git() {
		String title = cmd.getOptionValue("getImagebyTitle");
		String name = cmd.getOptionValue("name");
		Database Db = new Database(name);
		Db.get_byteImage("TITLE", title);
	}
		
	/** Get image by author and title and save as .png file
	 * @throws SQLException if SQL command could not be executed
	 */
	public static void option_giat() throws SQLException {
		String[] imageValues = cmd.getOptionValues("getImagebyAuthorTitle");
		String author = imageValues[0];
		String title = imageValues[1];
		String name = cmd.getOptionValue("name");
		Database Db = new Database(name);
		// get byte array from table 
		// convert byte array to jpg file and save at imageOutputPath
		Db.get_byteImage2(author, title);		
	}
			
	/** Get metadata by author and save as .txt file
	 * 
	 */
	public static void option_gma() {
		String author = cmd.getOptionValue("gma");
		System.out.println("Author name: "+ author); 
		String name = cmd.getOptionValue("name");
		Database Db = new Database(name);
		// get meta info from table and save as txt file
		Db.get_meta("AUTHOR", author);
	}
			
	/** Get metadata by title and save as .txt file
	 * 
	 */
	public static void option_gmt() {
		String title = cmd.getOptionValue("getMetabyTitle");
		String name = cmd.getOptionValue("name");
		Database Db = new Database(name);
		// get meta info from table and save as txt file
		Db.get_meta("TITLE", title);
	}
		
	public static void option_p() throws SQLException {
		String name = cmd.getOptionValue("name");
		Database Db = new Database(name);
		Db.see_table();

	}
	
	public static void main(String[] args) throws SQLException {
	
		/** create command line options */
		Options options = CommandLineInterface.make_options();
		/** parse command line for options */
		cmd = CommandLineInterface.parse_commandline(options, args);
		
		/** Check command line options and do corresponding methods */
		
		if (cmd.hasOption("d")){
			CommandLineInterface.option_s();
		}
		
		if (cmd.hasOption("gia")){
			CommandLineInterface.option_gia();
		}
		
		if (cmd.hasOption("git")){
			CommandLineInterface.option_git();
		}
		
		if (cmd.hasOption("giat")){
			try {
				CommandLineInterface.option_giat();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if (cmd.hasOption("gma")){
			CommandLineInterface.option_gma();
		}
		
		if (cmd.hasOption("gmt")){
			CommandLineInterface.option_gmt();
		}
		
		if (cmd.hasOption("p")) {
			CommandLineInterface.option_p();
		}
	}
	

}



