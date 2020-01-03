package de.bit.pl02.pp5.task01;
import de.bit.pl02.pp5.task01.Metadata;
import org.apache.commons.lang3.StringUtils; 

import org.apache.commons.cli.Option; 
import org.apache.commons.cli.Options;
import org.apache.commons.cli.CommandLine; 
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter; 
import org.apache.commons.cli.ParseException;
import java.io.*; 



/** Main Class to interact with the user and receive input through command line options. 
 * 
 * 
 * @author Shreya Kapoor
 * @author Sophia Krix
 * @author Gemma van der Voort 
 */
public class CommandLineInterface {
	

	/** The command line to receive arguments from the user, static method because at once 
	 * only one command line will run from this class.  */
	static CommandLine cmd;
	
	/** Constructor method, don't need a constructor method right now because we covered 
	 * the functionality otherwise.  */
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
		Option importfile = new Option("ip", "inputfile", true, "Enter the name of the input file"); 
		Option print = new Option("p", "print", false,"If you want to print the entire information" ); 
		Option meta = new Option("m", "meta", false, "If you want to add meta information"); 
		Option inputmeta = new Option("im", "inputmeta", false, "Enter the value of author, title, database and infographic separated by a comma");
		
		inputmeta.setArgs(4);
		inputmeta.setValueSeparator(',');
		
		options.addOption(directory);
		options.addOption(importfile); 
		options.addOption(meta); 
		options.addOption(print); 
		directory.setRequired(true); // It is mandatory to specify the directory, terminate otherwise
		importfile.setRequired(true); // Mandatory to specify the input file, terminate otherwise
		
		return options;
	}
	
	/** Parses command line options for arguments
	 * 	and returns the corresponding options and values. 
	 * Exits the application if the input file is not found.
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
			} catch (ParseException e) { 
			System.out.println("The file you entered doesn't exist "+e.getMessage()); 
			formatter.printHelp("parsingtest", options);
			System.exit(1); // exit the application if the input file is not found!
			}
		return cmd; }

	/** Reads an input file and prints the content if option "p" is specified
	 * 
	 * 
	 * @param cmd	an instance of CommandLine
	 * @param name  the name of the input picture 
	 */
	public static void readfile (CommandLine cmd, String filename){
		// Assuming that the existing metadata file is in the same directory as the image
		// and differs only in the extension .meta
		String directory = cmd.getOptionValue("d");
		//String absolutePath = new File(directory).getAbsolutePath();
		System.out.println("Reading file in directory, the absolute path is: "+ directory);
		String[] tmp = filename.split("\\."); //split through the last dot 
		//System.out.println(tmp); 
		String metapath = directory + "/" + tmp[0] + ".meta";
		System.out.println(metapath); 
		FileReader filer;
		try {
			filer = new FileReader(metapath);
			BufferedReader buffr = new BufferedReader(filer);
			boolean eof = false;
			System.out.println("Printing the metadata file! \nContents:"); 
			while ((!eof) && cmd.hasOption("p")){
			String s;
			try {
				s = buffr.readLine();
				if(s == null){
				      eof = true;
				 }
				 else{
				      System.out.println(s);
				  }
				buffr.close();
				
			}catch (IOException e) {
				System.out.println(StringUtils.repeat("=", 50)); 
				System.out.println("The metadata file cannot be read anymore! Exiting the application"); 
				System.exit(0);
			}
			
			}
			
		} catch (FileNotFoundException e1) {
			System.out.println("No metafile exists in the directory you provided"); 
			System.exit(2);// TO DO, see what the exit status can be.
		}

	
	}
	/** Get input from the user about author, title and infographic as a tuple, separated by a comma.
	 * 	Saves a .txt file of the metadata. If a file does not already exist, create a new file
	 *   if a file exists add contents to that one
	 * 
	 * 
	 * @param newfile	path of the new metadata file to be saved at 
	 * @throws IOException
	 */
	public static void getMetaUser(String newfile) throws IOException {
		// improvements write in the form of a class in the file.
		
		Metadata metadata = new Metadata();
		String directory = cmd.getOptionValue("d");
		//String absolutePath = new File(directory).getAbsolutePath();
		String[] metavalues = cmd.getOptionValues("im");
		String author = metavalues[0];
		String title = metavalues[1];
		int infographic = Integer.parseInt(metavalues[2]);
		
		// Set input by user as attributes of metadata instance
		metadata.setAuthor(author);
		metadata.setTitle(title);
		metadata.setInfographic(infographic);
		
		// Write metadata to file
		String metapath = directory + "//" + newfile.split(".")[0] + ".meta";
		FileWriter os = new FileWriter(metapath); 
		os.write(metadata.toString()); 
		os.close();
	}
	
	/** Checks if a metadata file exists already, otherwise creates 
	 * a new metadata file with the same name as the image file and 
	 * stores it with an extension .meta 
	 * 
	 * @param cmd		an instance of CommandLine
	 * @param path		the path of the input file
	 * @return newfile	TODO
	 * @throws IOException
	 */
	public static File checkmetafile(CommandLine cmd, String path){
		try {
			File oldfile = new File(path); 
			readfile(cmd, path);
			return oldfile; 
			} catch (Exception e){ 
			System.out.println("No metadata file found for this filename, making a new file for the metadata"); 
			File newfile = new File(path); 
			try {
			   newfile.createNewFile();
				FileReader filer = new FileReader(newfile); 
				return newfile; 
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				System.out.println("Could not create a new metafile"); 
				System.exit(0);
			} 
			
				}
		return null;
			}


		
	
	
	public static void main(String[] args){
		// TODO Which exceptions have to be thrown?
		
		/** create command line options */
		Options options = CommandLineInterface.make_options(); // for static method of the class
		/** parse command line for options */
		CommandLine cmd = CommandLineInterface.parse_commandline(options, args); // Parse the input given by the user
			
		// Print the metadata file content if metadata file exists
		if (cmd.hasOption("p")) {
			String filename = cmd.getOptionValue("ip");
			System.out.println("File path: " + filename);
			CommandLineInterface.readfile(cmd, filename);
			}
		
		// Save the input of the user as a .txt file if metadata file does not yet exist 
		if (cmd.hasOption("im")) {
			String imagename = cmd.getOptionValue("if");
			try {
				CommandLineInterface.getMetaUser(imagename);
			} catch (IOException e) {
				System.out.println("Could not get the metadata from the user properly");
			}
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
	 
	
	


