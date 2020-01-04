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
    
    /** Checks if a metadata file exists already, otherwise creates 
     * a new metadata file with the same name as the image file and 
     * stores it with an extension .meta 
     * 
     * @param cmd		an instance of CommandLine
     * @param path		the path of the input file
     * @return file		if the oldfile exists return it else return newfile
     */
    public static File checkmetafile(CommandLine cmd, String filename) {
        // Assuming that the existing metadata file is in the same directory as the image
        // and differs only in the extension .meta
        String directory = cmd.getOptionValue("d");
        //String absolutePath = new File(directory).getAbsolutePath();
        System.out.println("Reading file in directory, the absolute path is: " + directory);
        String[] tmp = filename.split("\\."); //split through the last dot 
        //System.out.println(tmp); 
        String path = directory + "/" + tmp[0] + ".meta";
        System.out.println(path);
        try {
            File oldfile = new File(path);
            readfile(cmd, oldfile);
            return oldfile;
        } catch (FileNotFoundException e) {
            System.out.println("No metadata file found for this filename, making a new file for the metadata");
            File newfile = new File(path);
            System.out.println(path);
            return newfile;
        }

    }

    /** Reads an input file and prints the content if option "p" is specified
     * 
     * 
     * @param cmd	an instance of CommandLine
     * @param name  the name of the input picture 
     * @throws FileNotFoundException 
     */
    public static void readfile(CommandLine cmd, File file) throws FileNotFoundException {

        FileReader filer;
        filer = new FileReader(file);
        BufferedReader buffr = new BufferedReader(filer);
        boolean eof = false;
        System.out.println("Printing the metadata file! \nContents:");
        try {
            String s;
            while ((!eof) && cmd.hasOption("p")) {
                s = buffr.readLine();
                if (s == null) {
                    eof = true;
                } else {
                    System.out.println(s);
                }
            }
            buffr.close();
        } catch (IOException e) {
            System.out.println(StringUtils.repeat("=", 20) + "ERROR" + StringUtils.repeat("=", 20));
            System.out.println("The metadata file cannot be read!");
            //System.exit(0); 
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
    public static void getMetaUser(String newfile) {
        // improvements write in the form of a class in the file.

        Metadata metadata = new Metadata();
        String directory = cmd.getOptionValue("d");
        //String absolutePath = new File(directory).getAbsolutePath();
        String[] metavalues = cmd.getOptionValues("im");
        String author = metavalues[0];
        String title = metavalues[1];
        String db = metavalues[2];
        int infographic = Integer.parseInt(metavalues[3]);

        // Set input by user as attributes of metadata instance
        metadata.setAuthor(author);
        metadata.setTitle(title);
        metadata.setInfographic(infographic);
        metadata.setAuthor(db);
        // Write metadata to file, split from the last line to get the meta path
        String metapath = directory + "/" + newfile.split("\\.(?=[^\\.]+$)")[0] + ".meta";
        System.out.println("metadata path" + metapath);
        FileWriter os;
        try {
            os = new FileWriter(metapath);
            os.write(metadata.toString());
            os.close();
        } catch (IOException e) {
            System.out.println(StringUtils.repeat("=", 20) + "ERROR" + StringUtils.repeat("=", 20));
            System.out.println("Could not write to the path related to the file");
        }

    }




    /** Creates command line options to make a database, store images in it
     * and retrieve image and metadata information
     * 
     * @return options	the command line options
     */
    public static Options make_options() {
        Options options = new Options();
        Option directory = new Option("d", "directory", true, "Enter the path of the directory of the metadata");
        Option importfile = new Option("ip", "inputfile", true, "Enter the name of the input file");
        Option print = new Option("p", "print", false, "If you want to print the entire information");
        Option meta = new Option("m", "meta", false, "If you want to add meta information");
        Option inputmeta = new Option("im", "inputmeta", false, "Enter the value of author, title, database and infographic separated by a comma");

        inputmeta.setArgs(4);
        inputmeta.setValueSeparator(',');

        options.addOption(directory);
        options.addOption(importfile);
        options.addOption(meta);
        options.addOption(print);
        options.addOption(inputmeta);
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
            System.out.println(StringUtils.repeat("=", 20) + "FATAL ERROR" + StringUtils.repeat("=", 20));
            System.out.println("There is an issue with parsing the options, ending the application " + e.getMessage());
            formatter.printHelp("parsingtest", options);
            System.exit(1); // exit the application if the input file is not found!
        }
        return cmd;
    }


    public static void main(String[] args) {
        /** create command line options */
        Options options = CommandLineInterface.make_options(); // for static method of the class
        /** parse command line for options */
        CommandLine cmd = CommandLineInterface.parse_commandline(options, args); // Parse the input given by the user

        /* Print the metadata file content if metadata file exists
         * if it doesn't exist then a new file will be created
         */
        if (cmd.hasOption("p")) {
            String filename = cmd.getOptionValue("ip");
            System.out.println("File name: " + filename);
            String directory = cmd.getOptionValue("d");
            File metafile = CommandLineInterface.checkmetafile(cmd, directory + '/' + filename);
        }

        // Save the input of the user as a .txt file if metadata file does not yet exist 
        if (cmd.hasOption("im") && cmd.hasOption("m")) {
            String imagename = cmd.getOptionValue("ip");
            CommandLineInterface.getMetaUser(imagename);
        }


    }
}