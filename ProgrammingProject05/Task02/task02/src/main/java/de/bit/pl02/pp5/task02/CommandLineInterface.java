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

public class CommandLineInterface {
	
	public static void main(String[] args) throws IOException {
	Options options = new Options(); 
	Option makedb = new Option("m", "makedb", true, "Enter the name of the database you want to make/see");
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
	getImagebyAuthor.setValueSeparator(",");
	
	getImagebyTitle.setArgs(2);
	getImagebyTitle.setValueSeparator(",");
	
	getImagebyTitle.setArgs(3);
	getImagebyTitle.setValueSeparator(",");
	
	//getMetabyAuthorTitle.setArgs(2);
	//getMetabyAuthorTitle.setValueSeparator(",");
	
	
	options.addOption(makedb); 
	options.addOption(store); 
	options.addOption(getImage); 
	options.addOptino(getMeta);
	input.setRequired(true);
	CommandLineParser parser = new DefaultParser(); 
	HelpFormatter formatter = new HelpFormatter(); 
	CommandLine cmd;
	try {
		// String dir = "/Users/sophiakrix/Documents/Life_Science_Informatics/3.Semester/ProgrammingLabII/information/PP/PP5"; // directory which you want to start with. 
		cmd = parser.parse(options, args); 
		
		// Create the database 
		if (cmd.hasOption("m") ) {
			String name = cmd.getOptionValue("makedb");
			Database Db = new Database(name); 
			Db.make_table();
		    }
		} 
		catch (ParseException e) { 
		System.out.println(e.getMessage()); formatter.printHelp("parsingtest", options);
		System.exit(1);
		return; }
	
		// Store metadata from directory 
		// TO DO okay to use value of makedb here?
		if (cmd.hasOption("s")){
			String dir = cmd.getOptionValue("store");
			String name = cmd.getOptionValue("makedb");
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
		
		// Get image by author 
		if (cmd.hasOption("gia")) {
			String[] imageValues = cmd.getOptionValues("getImagebyAuthor");
			String author = imageValues[0];
			String imageOutputPath = imageValues[1];
			// TODO: create sql to select image from database, convert into jpg and save as output file
			// TODO: make dbname an instance of Database
			String dbname = cmd.getOptionValue("makedb");
			Database Db = new Database(dbname);
			// get byte array from table 
			byte[] bytes = Db.get_byteImage(author, "AUTHOR");
			ByteImage byteImage = new ByteImage(bytes);
			// convert byte array to jpg file and save at imageOutputPath
			byteImage.byteToImage(bytes, imageOutputPath);
		}
		
		// Get image by title 
			if (cmd.hasOption("git")) {
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
			
		// Get image by author and title
			if (cmd.hasOption("giat")) {
				String[] imageValues = cmd.getOptionValues("getImagebyAuthorTitle");
				String author = imageValues[0];
				String title = imageValues[1];
				String imageOutputPath = imageValues[2];
				// TODO: create sql to select image from database, convert into jpg and save as output file
				// TODO: make dbname an instance of Database
				String dbname = cmd.getOptionValue("makedb");
				Database Db = new Database(dbname);
				// get byte array from table 
				byte[] bytes = Db.get_byteImage2(author, title);
				ByteImage byteImage = new ByteImage(bytes);
				// convert byte array to jpg file and save at imageOutputPath
				byteImage.byteToImage(bytes, imageOutputPath);
			}
			
		// Get metadata by author
			if (cmd.hasOption("gma")) {
				String author = cmd.getOptionValue("getMetabyAuthor");
				String dbname = cmd.getOptionValue("makedb");
				Database Db = new Database(dbname);
				// get meta info from table and save as txt file
				Db.get_meta(author, "AUTHOR");
			}
			
		// Get metadata by title
			if (cmd.hasOption("gmt")) {
				String title = cmd.getOptionValue("getMetabyTitle");
				String dbname = cmd.getOptionValue("makedb");
				Database Db = new Database(dbname);
				// get meta info from table and save as txt file
				Db.get_meta(title, "TITLE");
			}
		
	}
	

}



