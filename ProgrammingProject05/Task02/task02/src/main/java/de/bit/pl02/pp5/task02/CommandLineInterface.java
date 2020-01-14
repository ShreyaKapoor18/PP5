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
		
	/** Creates command line options to make a database, store images in it
	 * and retrieve image and metadata information
	 * 
	 * @return options,	the command line arguments
	 */
	public static Options make_options() {
		// create the command line options
		Options options = new Options(); 
		Option name = new Option("n", "name", true, "Enter the name of the database you want to make/see");
		Option directory = new Option("d", "directory", true, "Enter the file directory from which you want to store the images"); 
		Option getImagebyAuthor = new Option("gia", "getImagebyAuthor", true, "Enter the name of the author from which you want the image and the outputpath where to save it at" );
		Option getImagebyTitle = new Option("git", "getImagebyTitle", true, "Enter the name of the title from which you want the image and the outputpath where to save it at" ); 
		Option getMetabyAuthor = new Option("gma", "getMetabyAuthor", true, "Enter the name of the author of which you want to retrieve the metadata and the outputpath where to save it at");
		Option getMetabyTitle = new Option("gmt", "getMetabyTitle", true, "Enter the name of the title of which you want to retrieve the metadata and the outputpath where to save it at");
		Option print = new Option("p", "print", false, "Printing all content of the table");

		// enable multiple argument options separated by ','
		getImagebyAuthor.setArgs(2);
		getImagebyAuthor.setValueSeparator(',');
		
		getImagebyTitle.setArgs(2);
		getImagebyTitle.setValueSeparator(',');
		
		getMetabyAuthor.setArgs(2);
		getMetabyAuthor.setValueSeparator(',');
		
		getMetabyTitle.setArgs(2);
		getMetabyTitle.setValueSeparator(',');
		
		// add options 
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
		// parse command line
		CommandLineParser parser = new DefaultParser(); 
		HelpFormatter formatter = new HelpFormatter(); 
		try { 
			// list of options and their values
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
	public static void option_s() {
		String dir = cmd.getOptionValue("directory");
		String name = cmd.getOptionValue("name");
		Database Db = new Database(name); 
		// create table IMAGES 
		Db.make_table();
		try {
			// reads files of given directory and inserts metadata and the image 
			// into the table
			Db.read_director(dir);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
		
	/** Retrieve an image from the database by author with the {@link Database#get_byteImage(String, String, String)}
	 *  and save as .png file with the specified path by the user. 
	 *  
	 */
	public static void option_gia(){ 
		// split option values given by the author about the author and the output path
		String[] optionvalues = cmd.getOptionValues("getImagebyAuthor");
		String author = optionvalues[0];
		String outputpath = optionvalues[1];
		System.out.println("outputpath"+outputpath+"\n");
		String name = cmd.getOptionValue("name");
		Database Db = new Database(name);
		// store image of specified author in the output path
		Db.get_byteImage("AUTHOR", author, outputpath); 
	}
		
	/** Retrieve an image from the database by title and save as .png file.
	 * 
	 */
	public static void option_git() {
		// split option values about title and output path
		String[] optionvalues = cmd.getOptionValues("getImagebyTitle");
		String title = optionvalues[0];
		String outputpath = optionvalues[1];
		String name = cmd.getOptionValue("name");
		Database Db = new Database(name);
		// store image of specified title in the output path
		Db.get_byteImage("TITLE", title, outputpath);
	}
		
			
	/** Get metadata by author and save as .txt file
	 * 
	 */
	public static void option_gma() {
		String[] optionvalues = cmd.getOptionValues("gma");
		String author = optionvalues[0];
		String outputpath = optionvalues[1];
		System.out.println("Author name: "+ author); 
		String name = cmd.getOptionValue("name");
		Database Db = new Database(name);
		// get meta info from table and save as txt file
		Db.get_meta("AUTHOR", author, outputpath);
	}
			
	/** Get metadata by title and save as .txt file
	 * 
	 */
	public static void option_gmt() {
		String[] optionvalues = cmd.getOptionValues("getMetabyTitle");
		String title = optionvalues[0];
		String outputpath = optionvalues[1];
		String name = cmd.getOptionValue("name");
		Database Db = new Database(name);
		// get meta info from table and save as txt file
		Db.get_meta("TITLE", title, outputpath);
	}
		
	public static void option_p() throws SQLException {
		String name = cmd.getOptionValue("name");
		Database Db = new Database(name);
		// print table
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



