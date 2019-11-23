package de.bit.pl02.ex3.task01;



public class BMICalculator {
	/**
	 * 
	 */
	private static double BMI;
	public static double BMI(Person P)
    { 
    		BMI = P.getMass()/(P.getHeight()* P.getHeight()); 
    		return BMI; 
    	
    }
	public static void main(String[] args)
	{
		System.out.println("Enter the mass (in kg) and the height (in m)");
		Person P = new Person(); 
		P.setMass(Double.parseDouble(args[0]));
		P.setHeight(Double.parseDouble(args[1])); 
		System.out.println(BMI(P)); 
	}
}

