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
 * 	With this, it is possible to create a database on the basis of the directory given by the user at {@link #directory}.
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
		// TODO
	}
	
	/** Creates command line options to make a database, store images in it
	 * and retrieve image and metadata information
	 * 
	 * @return options	the command line arguments
	 */
	public static Options make_options() {
		Options options = new Options(); 
		Option name = new Option("n", "name", true, "Enter the name of the database you want to make/see");
		//directory
		Option directory = new Option("d", "directory", true, "Enter the file directory from which you want to store the images"); 
		// TO DO: give 2 command line option values for image name and output path
		Option getImagebyAuthor = new Option("gia", "getImagebyAuthor", true, "Enter the name of the author from which you want the image" );
		Option getImagebyTitle = new Option("git", "getImagebyTitle", true, "Enter the name of the title from which you want the image" );
		Option getImagebyAuthorTitle = new Option("giat", "getImagebyAuthorTitle", true, "Enter the name of the author and title from which you want the image" );
		// TO DO Split up into author and title 
		Option getMetabyAuthor = new Option("gma", "getMetabyAuthor", true, "Enter the name of the author of which you want to retrieve the metadata");
		Option getMetabyTitle = new Option("gmt", "getMetabyTitle", true, "Enter the name of the title of which you want to retrieve the metadata");
		//Option getMetabyAuthorTitle = new Option("gmat", "getMetabyAuthorTitle", false, "Enter the name of the author and of the title of which you want to retrieve the metadata");
		Option print = new Option("p", "print", false, "Printing all content of the table");
		// Option should take two arguments separeted by ","
		//getImagebyAuthor.setArgs(2);
		//getImagebyAuthor.setValueSeparator(',');
		
		//getImagebyTitle.setArgs(2);
		//getImagebyTitle.setValueSeparator(',');
		
		getImagebyTitle.setArgs(2);
		getImagebyTitle.setValueSeparator(',');
		
		//getMetabyAuthorTitle.setArgs(2);
		//getMetabyAuthorTitle.setValueSeparator(",");

		options.addOption(name); 
		options.addOption(directory); 
		options.addOption(getImagebyAuthor);
		options.addOption(getImagebyTitle);
		options.addOption(getImagebyAuthorTitle);
		options.addOption(getMetabyAuthor);
		options.addOption(getMetabyTitle);
		options.addOption(print);

		name.setRequired(true);
		//directory.setRequired(true); We wouldn't want to insert a directory in the database everytume
		
		
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
	 * @throws SQLException 
	 */		
	public static void option_s(){
		String dir = cmd.getOptionValue("directory");
		String name = cmd.getOptionValue("name");
		Database Db = new Database(name); 
		Db.make_table();
		try {
			Db.read_director(dir);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
		
	/** Retrieve an image from the database by author with the {@link Database#get_byteImage(String, String)}
	 *  and save as .jpg file with the specified path by the user.
	 * @throws IOException 
	 *  
	 */
	public static void option_gia() throws IOException { 
		//String[] imageValues = cmd.getOptionValues("getImagebyAuthor");
		String author = cmd.getOptionValue("getImagebyAuthor");
		//String imageOutputPath = imageValues[1];
		// TODO: create sql to select image from database, convert into jpg and save as output file
		// TODO: make dbname an instance of Database
		String name = cmd.getOptionValue("name");
		Database Db = new Database(name);
		// get byte array from table 
		//byte[] bytes = 
		Db.get_byteImage("AUTHOR", author);
		//ByteImage byteImage = new ByteImage(bytes);
		// convert byte array to jpg file and save at imageOutputPath
		//byteImage.byteToImage(bytes, imageOutputPath);
	}
		
	/** Retrieve an image from the database by title and save as .jpg file.
	 * 
	 */
	public static void option_git() {
		//String[] imageValues = cmd.getOptionValues("getImagebyTitle");
		String title = cmd.getOptionValue("getImagebyTitle");
		//String imageOutputPath = imageValues[1];
		// TODO: create sql to select image from database, convert into jpg and save as output file
		// TODO: make dbname an instance of Database
		String name = cmd.getOptionValue("name");
		Database Db = new Database(name);
		// get byte array from table 
		//byte[] bytes = 
		Db.get_byteImage("TITLE", title);
		//ByteImage byteImage = new ByteImage(bytes);
		// convert byte array to jpg file and save at imageOutputPath
		/*try {
			byteImage.byteToImage(bytes, imageOutputPath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
		
	/** Get image by author and title and save as .jpg file
	 * @throws SQLException 
	 * 
	 */
	public static void option_giat() throws SQLException {
		String[] imageValues = cmd.getOptionValues("getImagebyAuthorTitle");
		String author = imageValues[0];
		String title = imageValues[1];
		//String imageOutputPath = imageValues[2];
		// TODO: create sql to select image from database, convert into jpg and save as output file
		// TODO: make dbname an instance of Database
		String name = cmd.getOptionValue("name");
		Database Db = new Database(name);
		// get byte array from table 
		Db.get_byteImage2(author, title);
		
		// convert byte array to jpg file and save at imageOutputPath
		
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
			try {
				CommandLineInterface.option_gia();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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



