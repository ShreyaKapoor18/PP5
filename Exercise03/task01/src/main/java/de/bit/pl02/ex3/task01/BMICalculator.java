package de.bit.pl02.ex3.task01;

import de.bit.pl02.ex3.task02.*;

public class BMICalculator extends InputPerson{
	private static double bmi; 
	
	public static double BMI (InputPerson person1) {
		bmi = (person1.getWeight() / (person1.getHeight() * person1.getHeight()));
		return bmi;
	}
	public static void main(String[] args)
	{
		
	}

}
