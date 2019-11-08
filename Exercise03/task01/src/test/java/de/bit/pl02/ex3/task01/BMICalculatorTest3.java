package de.bit.pl02.ex3.task01;

import static org.junit.Assert.*;
import java.text.DecimalFormat;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class BMICalculatorTest3 {



	
	@Test
	public void test() {
		float height = (float) 1.87; 
		float mass = 80;  
		double bmi_ex = 22.87741; 
		Person p2 = new Person(); 
			// TODO Auto-generated method stub
		p2.setHeight(height); 
		p2.setMass(mass);
		double bm = BMICalculator.BMI(p2); 
		DecimalFormat dec = new DecimalFormat("#0.00000");
		System.out.println(dec.format(bm));
		double a = Double.parseDouble(dec.format(bm));
		assertTrue(a== bmi_ex); 
		
	}




	
}
