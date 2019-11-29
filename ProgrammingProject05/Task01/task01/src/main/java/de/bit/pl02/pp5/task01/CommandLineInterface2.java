package de.bit.pl02.pp5.task01;
import org.apache.commons.cli.Option; 
import org.apache.commons.cli.Options;
import org.apache.commons.cli.CommandLine; 
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter; 
import org.apache.commons.cli.ParseException;
import java.io.*; 
import java.net.*; 
public class CommandLineInterface2 {
	
	public static void main(String[] args) throws IOException {
	Options options = new Options(); 
	Option input = new Option("i", "import", true, "Enter the name of the input file"); 
	Option input2 = new Option("p", "print", false,"If you want to print the entire information" ); 
	Option input3 = new Option("m", "meta", true, "If you want to print the meta information"); 
	options.addOption(input); 
	options.addOption(input2); 
	options.addOption(input3); 
	input.setRequired(true);
	CommandLineParser parser = new DefaultParser(); 
	HelpFormatter formatter = new HelpFormatter(); 
	CommandLine cmd;
	try {
	cmd = parser.parse(options, args);
	String filename = cmd.getOptionValue("import", ""); 
	String metainfo = cmd.getOptionValue("meta", "");
	System.out.println("filename "+filename); 
	FileReader filer = new FileReader("/Users/shreyakapoor/Desktop/PP5/"+filename.substring(0,filename.length()-4)+".meta");
	System.out.println("/Users/shreyakapoor/Desktop/PP5/"+filename.substring(0,filename.length()-4)+".meta");
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
	    if (link){
	    	URL url = new URL(s); 
	    	BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
	    	String line;
	    	int count = 0; 
	        while ((line = reader.readLine()) != null && count <= 10)
	        {
	          System.out.println(line);
	          count +=1; 
	        }
	        reader.close(); 

	       }
	    }
	  }
	} catch (ParseException e) { System.out.println(e.getMessage()); formatter.printHelp("parsingtest", options);
	System.exit(1);
	return; }
	} 
	 
	
	
}
