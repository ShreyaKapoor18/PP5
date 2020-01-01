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
import java.net.*;
import java.util.Scanner; 
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
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
	 * @return options	the commandline arguments
	 */
	public static Options make_options() {
		Options options = new Options(); 
		Option location = new Option("l", "location", true, "Enter the name of the database you want to make/see");
		Option store = new Option("s", "store", true, "Enter the file directory from which you want to store the images"); 
		// TO DO: give 2 command line option values for image name and output path
		Option getImagebyAuthor = new Option("gia", "getImagebyAuthor", false, "Enter the name of the author from which you want the image and the output path it should have" );
		Option getImagebyTitle = new Option("git", "getImagebyTitle", false, "Enter the name of the title from which you want the image and the output path it should have" );
		Option getImagebyAuthorTitle = new Option("giat", "getImagebyAuthorTitle", false, "Enter the name of the author and title from which you want the image and the output path it should have" );
		// TO DO Split up into author and title 
		Option getMetabyAuthor = new Option("gma", "getMetabyAuthor", false, "Enter the name of the author of which you want to retrieve the metadata");
		Option getMetabyTitle = new Option("gmt", "getMetabyTitle", false, "Enter the name of the title of which you want to retrieve the metadata");
		//Option getMetabyAuthorTitle = new Option("gmat", "getMetabyAuthorTitle", false, "Enter the name of the author and of the title of which you want to retrieve the metadata");
		
		// Option should take two arguments separeted by ","
		getImagebyAuthor.setArgs(2);
		getImagebyAuthor.setValueSeparator(',');
		
		getImagebyTitle.setArgs(2);
		getImagebyTitle.setValueSeparator(',');
		
		getImagebyTitle.setArgs(3);
		getImagebyTitle.setValueSeparator(',');
		
		//getMetabyAuthorTitle.setArgs(2);
		//getMetabyAuthorTitle.setValueSeparator(",");

		options.addOption(makedb); 
		options.addOption(store); 
		options.addOption(getImagebyAuthor);
		options.addOption(getImagebyTitle);
		options.addOption(getImagebyAuthorTitle);
		options.addOption(getMetabyAuthor);
		options.addOption(getMetabyTitle);

		makedb.setRequired(true);
		
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
			// String dir = "/Users/sophiakrix/Documents/Life_Science_Informatics/3.Semester/ProgrammingLabII/information/PP/PP5"; // directory which you want to start with. 
			cmd = parser.parse(options, args); 	
			} 
			catch (ParseException e) { 
			System.out.println(e.getMessage()); formatter.printHelp("parsingtest", options);
			System.exit(1);
			}
		return cmd; }
	
	
	/** Create the database
	 * 
	 *
	public static void option_m() {
		String name = cmd.getOptionValue("makedb");
		Database Db = new Database(name); 
		Db.make_table();
	    } */
		
	/** Store metadata from directory 
	 * 
	 */
	public static void option_s() {
		String dir = cmd.getOptionValue("store");
		String name = cmd.getOptionValue("location");
		Database Db = new Database(name); 
		Db.make_table();
		Db.read_director(dir); 
		/*try {
			Db.see_table(); // cam be done with the help of command line parameters, whether these options shall be present or not. 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		}
		
	/** Get image by author and save as .jpg file
	 * 
	 */
	public static void option_gia() { 
		String[] imageValues = cmd.getOptionValues("getImagebyAuthor");
		String author = imageValues[0];
		String imageOutputPath = imageValues[1];
		// TODO: create sql to select image from database, convert into jpg and save as output file
		// TODO: make dbname an instance of Database
		String dbname = cmd.getOptionValue("location");
		Database Db = new Database(dbname);
		// get byte array from table 
		byte[] bytes = Db.get_byteImage(author, "AUTHOR");
		ByteImage byteImage = new ByteImage(bytes);
		// convert byte array to jpg file and save at imageOutputPath
		byteImage.byteToImage(bytes, imageOutputPath);
	}
		
	/** Get image by title and save as .jpg file
	 * 
	 */
	public static void option_git() {
		String[] imageValues = cmd.getOptionValues("getImagebyTitle");
		String title = imageValues[0];
		String imageOutputPath = imageValues[1];
		// TODO: create sql to select image from database, convert into jpg and save as output file
		// TODO: make dbname an instance of Database
		String dbname = cmd.getOptionValue("makedb");
		Database Db = new Database(dbname);
		// get byte array from table 
		byte[] bytes = Db.get_byteImage(title, "TITLE");
		ByteImage byteImage = new ByteImage(bytes);
		// convert byte array to jpg file and save at imageOutputPath
		byteImage.byteToImage(bytes, imageOutputPath);
	}
		
	/** Get image by author and title and save as .jpg file
	 * 
	 */
	public static void option_giat() {
		String[] imageValues = cmd.getOptionValues("getImagebyAuthorTitle");
		String author = imageValues[0];
		String title = imageValues[1];
		String imageOutputPath = imageValues[2];
		// TODO: create sql to select image from database, convert into jpg and save as output file
		// TODO: make dbname an instance of Database
		String dbname = cmd.getOptionValue("location");
		Database Db = new Database(dbname);
		// get byte array from table 
		byte[] bytes = Db.get_byteImage2(author, title);
		ByteImage byteImage = new ByteImage(bytes);
		// convert byte array to jpg file and save at imageOutputPath
		byteImage.byteToImage(bytes, imageOutputPath);
	}
			
	/** Get metadata by author and save as .txt file
	 * 
	 */
	public static void option_gma() {
		String author = cmd.getOptionValue("getMetabyAuthor");
		String dbname = cmd.getOptionValue("makedb");
		Database Db = new Database(dbname);
		// get meta info from table and save as txt file
		Db.get_meta(author, "AUTHOR");
	}
			
	/** Get metadata by title and save as .txt file
	 * 
	 */
	public static void option_gmt() {
		String title = cmd.getOptionValue("getMetabyTitle");
		String dbname = cmd.getOptionValue("location");
		Database Db = new Database(dbname);
		// get meta info from table and save as txt file
		Db.get_meta(title, "TITLE");
	}
		
	
	public static void main() {
	
		/** create command line options */
		Options options = CommandLineInterface.make_options();
		/** parse command line for options */
		CommandLine cmd = CommandLineInterface.parse_commandline(options, args);
		
		/** Check command line options and do corresponding methods */

		
		if (cmd.hasOption("s")){
			cli.option_s();
		}
		
		if (cmd.hasOption("gia")){
			cli.option_gia();
		}
		
		if (cmd.hasOption("git")){
			cli.option_git();
		}
		
		if (cmd.hasOption("giat")){
			cli.option_giat();
		}
		
		if (cmd.hasOption("gma")){
			cli.option_gma();
		}
		
		if (cmd.hasOption("gmt")){
			cli.option_gmt();
		}
	}
	

}



