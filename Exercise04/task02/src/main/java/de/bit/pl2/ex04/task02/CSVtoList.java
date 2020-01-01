package de.bit.pl2.ex04.task02;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;


public class CSVtoList {

	public static void main(String[] args) {
		
		//BufferedReader br = null;
		String inputFile = "/Users/sophiakrix/gitlab/group-03-descartes/Exercise04/task02/Exercise04.csv";
		// String inputFile = args[0];		//	Takes an input argument from the CLI to the list t
        String delimiter = ",";
        
        ArrayList<ArrayList<String>> records = new ArrayList<ArrayList<String>>();
		
        System.out.println("Before Sorting:");
        
		try {
			// create buffered reader and read in file 
        	BufferedReader br = new BufferedReader(new FileReader(inputFile));
            String line;
            // split details of each line
            while ((line = br.readLine()) != null) {
                String[] values = line.split(delimiter);
                ArrayList<String> values_array = new ArrayList<String>(Arrays.asList(values));
                records.add(values_array);
                
            }
            
            // remove header
            records.remove(0);
            
            // Sort list
            //ArrayList<List<Person>> listPersons = sortList(records);
            
            // Write to CSV
            //writeCSV(listPersons, inputFile);
        }
		
		catch (IOException e) {
			System.out.println("File does not exist");	// Warn that wrong path may have been used
		}

		// printing list of String arrays in the ArrayList
		for (List<String> strArr : records) {
			System.out.println(strArr);
			
		//System.out.println(records);
		}
		
		System.out.println("After Sorting:");
		// SORTING 
		ArrayList<ArrayList<String>> sortiert = selectionsort(records);

		for (int i = 0; i < sortiert.size(); i++) {
			System.out.print(sortiert.get(i) + ", ");
		}

		}
		
		// SELECTION SORT 
		// sortieren is nested ArrayList to be sorted according to the first inner element
		public static ArrayList<ArrayList<String>> selectionsort(ArrayList<ArrayList<String>> sortieren) {
			for (int i = 0; i < sortieren.size() - 1; i++) {
				for (int j = i + 1; j < sortieren.size(); j++) {
					if (sortieren.get(i).get(0).compareTo(sortieren.get(j).get(0)) > 0) {
						ArrayList<String> temp = sortieren.get(i);
						sortieren.set(i,sortieren.get(j));
						sortieren.set(j,temp);
					}
				}
			}
	
			return sortieren;
			
	        	
	      
			
		}
	}
