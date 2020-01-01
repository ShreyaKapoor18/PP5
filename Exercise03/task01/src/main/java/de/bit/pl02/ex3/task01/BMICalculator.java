package de.bit.pl02.ex3.task01;

<<<<<<< HEAD
// import de.bit.pl02.ex3.task02.*;

public class BMICalculator {
	private static double bmi; 
	public double height;
	public double weight_begin;
	public double weight_end;
	
	Person person1 = new Person();
	
	public static double BMI (Person person1) {
		bmi = (person1.getWeight() / (person1.getHeight() * person1.getHeight()));
		return bmi;
	}
	public static void main(String[] args)
	{  	
	        double height = Double.parseDouble(args[0]);
	             
	        double weight_begin = Double.parseDouble(args[1]);
	        
	        double weight_end = Double.parseDouble(args[2]);
	        
	        for (double i= weight_begin;i<=weight_end; i+=0.5) {	        	
	        	
	        	Person p = new Person();
	        	p.setHeight(height);
	        	p.setWeight(i);
	        	System.out.println(BMI(p));
	        }
	        
	       
		
	}

}
=======


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

>>>>>>> origin/master
