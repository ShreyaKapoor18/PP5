package de.bit.pl2.ex05.task01;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

public class BBD {

	Options options = new Options();
	
	Option inputFile = new Option("f", "filepath", false, "file path of file to be parsed");
	options.addOption(inputFile);
	Option inputExpression= new Option("e", "regularexpression", false, "regular expression to be checked");
	options.addOption(inputExpression);

	
	
}
