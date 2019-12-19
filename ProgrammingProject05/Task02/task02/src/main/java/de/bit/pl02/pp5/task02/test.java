package de.bit.pl02.pp5.task02;

import java.io.File;

public class test {
	public static void main(String[] args)
	{ 
		File dir = new File("//Users//shreyakapoor//Desktop//PP5"); 
    	File[] filesindir = dir.listFiles();
    	if (filesindir!= null) { 
    		for (File child: filesindir )
    		{ 
    			System.out.println(child.getName());
    			System.out.println(child.getAbsolutePath()); 
    		} 
    	}
	} 
	
}
