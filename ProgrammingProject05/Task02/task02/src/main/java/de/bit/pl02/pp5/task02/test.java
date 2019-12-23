package de.bit.pl02.pp5.task02;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class test {
	public static void main(String[] args) throws IOException
	{ 	ArrayList<String> arr = new ArrayList<String>();
		File dir = new File("//Users//shreyakapoor//Desktop//PP5"); 
    	File[] filesindir = dir.listFiles();
    	if (filesindir!= null) { 
    		for (File child: filesindir )
    		{ 	if (child.getName().contains(".meta")){
    			String filename = child.getName();
    			System.out.println(child.getName());
				boolean eof = false;
				String author = "xx"; 
				String title = "yy"; 
				String Id; 
				FileReader filer = new FileReader(child.getAbsolutePath());
				BufferedReader buffr = new BufferedReader(filer);
				while ((!eof))
				  {
				  String s = buffr.readLine();
				  if(s == null){
				    eof = true;
				       }
				  else{
				    //System.out.println(s);
				    if (s.contains("Title:")){
				    	title = s.split(":")[1]; 
				    }
				    else if (s.contains("Author:")) { 
				    	author = s.split(":")[1]; 
				    }
				   
				  	}
				}
				buffr.close();
				if ( author.length()>=2 && title.length()>=5){
					Id = author.substring(0, 2)+title.substring(0,4)+filename.split("\\.")[0];
					
				} 
				else {
					Id = filename.split("[- .]")[0]; 
				}
				System.out.println(Id); 
    			}
    		} 
    	}
	} 
	
}
