package de.bit.pl02.ex3.task01; 

public class BMICalculator extends Person {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static double BMI;
	public static double BMI(Person P)
    { 
    		BMI = P.getMass()/(P.getHeight()* P.getHeight()); 
    		return BMI; 
    	
    }
	public static void main(String[] args)
	{
		
	}
	

}
