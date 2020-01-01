package de.bit.pl02.pp5.task01;
import de.bit.pl02.pp5.task01.Metadata;

import org.apache.commons.cli.Option; 
import org.apache.commons.cli.Options;
import org.apache.commons.cli.CommandLine; 
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter; 
import org.apache.commons.cli.ParseException;
import java.io.*; 



/** Class to interact with the user and receive input upon command line options
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
	 * TODO had to set option import to importfile, otherwise I get an error (key word?)
	 * @return options	the command line options
	 */
	public static Options make_options() {
		Options options = new Options(); 
		Option directory = new Option("d", "directory", true, "Enter the path of the directory of the metadata");
		Option importfile = new Option("if", "importfile", true, "Enter the name of the input file"); 
		Option print = new Option("p", "print", false,"If you want to print the entire information" ); 
		Option meta = new Option("m", "meta", false, "If you want to add meta information"); 
		Option inputmeta = new Option("im", "inputmeta", false, "Enter the value of author, title and infographic separated by a comma");
		
		inputmeta.setArgs(3);
		inputmeta.setValueSeparator(',');
		
		options.addOption(directory);
		options.addOption(importfile); 
		options.addOption(meta); 
		options.addOption(print); 
		directory.setRequired(true);
		importfile.setRequired(true);
		
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

	/** Reads an input file and prints the content if option "p" is specified
	 * 
	 * 
	 * @param cmd	an instance of CommandLine
	 * @param path	the path of the input file 
	 * @throws IOException
	 */
	public static void readfile (CommandLine cmd, String path) throws IOException{
		// Assuming that the existing metadata file is in the same directory as the image
		// and differs only in the extension .meta
		String directory = cmd.getOptionValue("d");
		System.out.println("Reading file in directory "+ directory);
		String[] tmp = path.split(".");
		for (String a : tmp) {
            System.out.println(a); } 
		String metapath = directory + "/" + path.split(".")[0] + ".meta";
		FileReader filer = new FileReader(metapath);
		BufferedReader buffr = new BufferedReader(filer);
		boolean eof = false;
		while ((!eof) && cmd.hasOption("p"))
		  {
		  String s = buffr.readLine();
		  if(s == null){
		    eof = true;
		       }
		  else{
		    System.out.println(s);
		    boolean link = s.contains("https:");
		  	}
		}
		buffr.close();
		}
	
	/** Get input from the user about author, title and infographic as a tuple, separated by a comma.
	 * 	Saves a .txt file of the metadata.
	 * 
	 * 
	 * @param newfile	path of the new metadata file to be saved at 
	 * @throws IOException
	 */
	public static void getMetaUser(String newfile) throws IOException {
		/* if a file does not already exist, create a new file
		  if a file exists add contents to that one
	 	*/
		// improvements write in the form of a class in the file.
		
		Metadata metadata = new Metadata();
		String directory = cmd.getOptionValue("d");
		String[] metavalues = cmd.getOptionValues("im");
		String author = metavalues[0];
		String title = metavalues[1];
		int infographic = Integer.parseInt(metavalues[2]);
		
		// Set input by user as attributes of metadata instance
		metadata.setAuthor(author);
		metadata.setTitle(title);
		metadata.setInfographic(infographic);
		
		// Write metadata to file
		String metapath = directory + "/" + newfile.split(".")[0] + ".meta";
		FileWriter os = new FileWriter(metapath); 
		os.write("Author: " + author + "\n");
		os.write("Title: " + title + "\n");
		os.write("Infographic: " + infographic + "\n");

		os.close();
	}
	
	/** TODO ?
	 * 
	 * @param cmd		an instance of CommandLine
	 * @param path		the path of the input file
	 * @return newfile	TODO
	 * @throws IOException
	 */
	public static File checkmetafile(CommandLine cmd, String path) throws IOException{
		try {
		File oldfile = new File(path); 
		readfile(cmd, path);
		return oldfile; 
			} 
		catch (FileNotFoundException e){ 
		System.out.println("No metadata file found for this filename, making a new file for the metadata"); 
		File newfile = new File(path); 
		newfile.createNewFile(); 
		FileReader filer = new FileReader(newfile); 
		filer.close();
		return newfile; 
			}
		}	
	
	
	public static void main(String[] args) throws IOException {
		// TODO Which exceptions have to be thrown?
		
		/** create command line options */
		Options options = CommandLineInterface.make_options();
		/** parse command line for options */
		CommandLine cmd = CommandLineInterface.parse_commandline(options, args);
			
		// Print the metadata file content if metadata file exists
		if (cmd.hasOption("p")) {
			String path = cmd.getOptionValue("if");
			System.out.println("File path: " + path);
			CommandLineInterface.readfile(cmd, path);
			}
		
		// Save the input of the user as a .txt file if metadata file does not yet exist 
		if (cmd.hasOption("im")) {
			String imagename = cmd.getOptionValue("if");
			CommandLineInterface.getMetaUser(imagename);
			}
		

		}
	
	/* Previous main method

	String filename = cmd.getOptionValue("import", ""); 
	System.out.println(filename); 
	String[] arr = filename.split("\\."); 
	String path = dir+ arr[0] + ".meta"; 
	
	if (cmd.hasOption("p") ) 
		{ File metafile =  checkmetafile(cmd, path) ; 
		  if (cmd.hasOption("m"))
			{
				getmeta(metafile); 
			}
	     }
	} 
	
	catch (ParseException e) { 
	System.out.println(e.getMessage()); formatter.printHelp("parsingtest", options);
	System.exit(1);
	return; }
	} */
}
	 
	
	


