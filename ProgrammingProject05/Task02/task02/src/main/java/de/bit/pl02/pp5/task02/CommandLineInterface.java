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

/**
 * The class CommandLineInterface provides an interface for the user and helps
 * to interact. With this, it is possible to create a database on the basis of
 * the directory given by the user. This directory should contain image files
 * (.png, .jpg or .jpeg) and corresponding metadata files (.txt). The user can
 * query the database by giving the name of the author or title to retrieve an
 * image or additional metadata information.
 * 
 * 
 * @author Shreya Kapoor
 * @author Sophia Krix
 * @author Gemma van der Voort
 */
public class CommandLineInterface {

	/** the command line to receive arguments from the user */
	static CommandLine cmd;

	/**
	 * Creates command line options to make a database, store images in it and
	 * retrieve image and metadata information
	 * 
	 * @return options, the command line arguments
	 */
	public static Options make_options() {
		// create the command line options
		Options options = new Options();
		Option name = new Option("n", "name", true,
				"Enter the path and name of the database you want to make/see separated by a comma");
		// directory
		Option directory = new Option("d", "directory", true,
				"Enter the file directory from which you want to store the images");
		Option getImagebyAuthor = new Option("gia", "getImagebyAuthor", true,
				"Enter the name of the author from which you want the image and the outputpath where to save it at");
		Option getImagebyTitle = new Option("git", "getImagebyTitle", true,
				"Enter the name of the title from which you want the image and the outputpath where to save it at");
		Option getMetabyAuthor = new Option("gma", "getMetabyAuthor", true,
				"Enter the name of the author of which you want to retrieve the metadata and the outputpath where to save it at");
		Option getMetabyTitle = new Option("gmt", "getMetabyTitle", true,
				"Enter the name of the title of which you want to retrieve the metadata and the outputpath where to save it at");
		Option print = new Option("p", "print", false, "Printing all content of the table");

		// enable multiple argument options separated by ','
		name.setArgs(2);
		name.setValueSeparator(',');
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

	/**
	 * Parses command line options for arguments and returns the corresponding
	 * options and values
	 * 
	 * @param options the command line options
	 * @param args    the values of the command line options
	 * @return cmd list of atomic option and value tokens
	 */
	public static CommandLine parse_commandline(Options options, String[] args) {
		// parse command line
		CommandLineParser parser = new DefaultParser();
		HelpFormatter formatter = new HelpFormatter();
		try {
			// list of options and their values
			cmd = parser.parse(options, args);
		} catch (ParseException e) {
			System.out.println(e.getMessage());
			formatter.printHelp("parsingtest", options);
			System.exit(1);
		}
		return cmd;
	}

	/**
	 * Creates a database with the method {@link Database#make_table()}, reads the
	 * files of the specified directory and inserts the metadata of ID, TITLE and
	 * AUTHOR into the database with the method
	 * {@link Database#read_director(String)}.
	 * 
	 * @param Db	the name of the database you want to connect to 
	 */
	public static void option_s(Database Db) {
		String dir = cmd.getOptionValue("directory");
		String name = cmd.getOptionValue("name");
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

	/**
	 * Retrieve an image from the database by author with the
	 * {@link Database#get_byteImage(String, String, String)} and save as .png file
	 * with the specified path by the user.
	 * 
	 * @param Db	the name of the database you want to connect to
	 */
	public static void option_gia(Database Db) {
		// split option values given by the author about the author and the output path
		String[] optionvalues = cmd.getOptionValues("getImagebyAuthor");
		String author = optionvalues[0];
		String outputpath = optionvalues[1];
		System.out.println("outputpath" + outputpath + "\n");
		// store image of specified title in the output path
		Db.get_byteImage("AUTHOR", author, outputpath);
	}

	/**
	 * Retrieve an image from the database by title and save as .png file.
	 * 
	 * @param Db	the name of the database you want to connect to
	 */
	public static void option_git(Database Db) {
		String[] optionvalues = cmd.getOptionValues("getImagebyTitle");
		String title = optionvalues[0];
		String outputpath = optionvalues[1];
		Db.get_byteImage("TITLE", title, outputpath);
		// store image of specified title in the output path
	}

	/**
	 * Get metadata by author and save as .txt file
	 * 
	 * @param Db	the name of the database you want to connect to
	 */
	public static void option_gma(Database Db) {
		String[] optionvalues = cmd.getOptionValues("gma");
		String author = optionvalues[0];
		String outputpath = optionvalues[1];
		System.out.println("Author name: " + author);
		// get meta info from table and save as txt file
		Db.get_meta("AUTHOR", author, outputpath);
	}

	/**
	 * Get metadata by title and save as .txt file
	 * 
	 * @param Db	the name of the database you want to connect to
	 */
	public static void option_gmt(Database Db) {
		String[] optionvalues = cmd.getOptionValues("getMetabyTitle");
		String title = optionvalues[0];
		String outputpath = optionvalues[1];
		// get meta info from table and save as txt file
		Db.get_meta("TITLE", title, outputpath);
	}

	/**
	 * Print the contents of the database
	 * 
	 * @param Db			the name of the database you want to connect to
	 * @throws SQLException if the commands can not be executed
	 */
	public static void option_p(Database Db) throws SQLException {
		String name = cmd.getOptionValue("name");
		Db.see_table();
	}
	
	/** Check if directory has already been provided so that 
	 * no duplicates occur.
	 * @throws IOException if input or output error occurs
	 * 
	 */
	public static void avoid_duplicates() throws IOException {
		String directory = cmd.getOptionValue("d");
		String name = cmd.getOptionValue("n");
		// write directory and name of database to file to be checked later
		File directories = new File("~/gitlab/group-03-descartes/ProgrammingProject05/Task02/task02/directories.txt");
		if (directories.exists()) {
		// FileWriter writer = new FileWriter("~/gitlab/group-03-descartes/ProgrammingProject05/Task02/task02/directories.txt");
			// if file is present
			BufferedReader br = new BufferedReader(new FileReader(directories)); 	  
			String st; 
			while ((st = br.readLine()) != null)  {
				String directory_present = st.split(",")[0];
				String name_present = st.split(",")[1];
				if (!directory_present.equals(directory) & !name_present.equals(name)) {
				//TODO
				}
			}
		} else {
			try {
				// write metadata to file
				BufferedWriter buff = new BufferedWriter(writer);
				String present = directory+","+name;
				try {
					buff.append(present);
				} finally {
					buff.flush();
					buff.close();
				}
		} catch (IOException e) {
		System.out.println(e.getMessage());}
		finally {
			try {
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}}
		}
	}
		
	

	public static void main(String[] args) throws SQLException {

		/** create command line options */
		Options options = CommandLineInterface.make_options();
		/** parse command line for options */
		cmd = CommandLineInterface.parse_commandline(options, args);

		if (cmd.hasOption("name")) {
			System.out.println("Connecting to the database: ");
			System.out.println("if the database doesnt exist new one will be created");
			String[] namevalues = cmd.getOptionValues("n");
			Database Db = new Database(namevalues[0], namevalues[1]);

			// Check if directory has already been passed 
			if (cmd.hasOption("d") & cmd.hasOption("n")) {
				try {
					CommandLineInterface.avoid_duplicates();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			/** Check command line options and do corresponding methods */
			if (cmd.hasOption("d")) {
				System.out.println("Executing Store method");
				CommandLineInterface.option_s(Db);
			}

			if (cmd.hasOption("gia")) {
				CommandLineInterface.option_gia(Db);
			}

			if (cmd.hasOption("git")) {
				CommandLineInterface.option_git(Db);
			}

			if (cmd.hasOption("gma")) {
				CommandLineInterface.option_gma(Db);
			}

			if (cmd.hasOption("gmt")) {
				CommandLineInterface.option_gmt(Db);
			}

			if (cmd.hasOption("p")) {
				CommandLineInterface.option_p(Db);
			}
		}
	}

	/**
	 * Adds name of a database created by the user to the list of allowed databases,
	 * in file allowedDBs.txt in the task02 folder. Only those databases are allowed
	 * to be queried and added to by the API users. This functionality is intended
	 * to prevent API users from making their own databases due to typo's, whereby
	 * they clutter the server that this application is running on and upload their
	 * images to the wrong database.
	 * 
	 * @param name	the name of the database
	 */
	public static void addDbToAllowedDb(String name) {
		BufferedWriter output;
		try {
			// appends the new database name and a newline to allowedDBs.txt.
			output = new BufferedWriter(new FileWriter("allowedDBs.txt", true));
			output.append(name);
			output.newLine();
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
