package de.bit.pl02.ex3.task02;

import java.util.Scanner;


public class InputPerson {
	
	private double weight;
	private double height;
	
	
	public static void main(String[] args) 
	{
		InputPerson inputperson = new InputPerson();
				
		Scanner in = new Scanner(System.in); 
        
    	System.out.println("Enter weight (in kg):");
        double weight = in.nextDouble();
        inputperson.setWeight(weight);
        
        System.out.println("Enter height (in meters):");
        double height = in.nextDouble();
        inputperson.setHeight(height);
        
        in.close();
        
        System.out.println("This person has weight = " + weight + " and height = " + height);

	}

	public void setWeight(double weight) { this.weight = weight; }
	
	public void setHeight(double height) { this.height = height; } 
	
	public double getWeight() { return this.weight; }
	
	public double getHeight() { return this.height; }
	
	
		


}
