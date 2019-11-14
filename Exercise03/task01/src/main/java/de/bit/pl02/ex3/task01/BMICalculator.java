package de.bit.pl02.ex3.task01;

public class BMICalculator extends Person{
	private static double bmi; 
	
	public static double BMI (Person person1) {
		bmi = (person1.getWeight() / (person1.getHeight() * person1.getHeight()));
		return bmi;
	}
	public static void main(String[] args)
	{
		
	}

}
