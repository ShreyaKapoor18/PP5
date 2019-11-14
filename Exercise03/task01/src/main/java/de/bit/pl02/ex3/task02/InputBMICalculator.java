package de.bit.pl02.ex3.task02;

import de.bit.pl02.ex3.task01.*;

public class InputBMICalculator {
	

	public static void main (String[] args) {
		InputPerson person = new InputPerson();
		InputPerson.main(args);
		
		//Calculate BMI of instance person
		double result = BMICalculator.BMI(person);	
		System.out.println("Your BMI is" + result);
	}
	
	
	
	
	

}
