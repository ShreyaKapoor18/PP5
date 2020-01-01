package de.bit.pl02.ex3.task01;

<<<<<<< HEAD
import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

public class BMICalculatorTest {

	
	@Test
	public void testBMICalculator() {
		double weight1 = 80;
		double height1 = 1.87;
		
		Person testperson =  new Person();
		testperson.setHeight(height1);
		testperson.setWeight(weight1);
		
		double result = BMICalculator.BMI(testperson);
		// Set decimal precision
	    double bd = new BigDecimal(String.valueOf(result)).setScale(5, BigDecimal.ROUND_HALF_UP).doubleValue();
		
		System.out.println(bd);
		assertTrue(bd == 22.87741);
		
		
	}
	}

=======
import java.text.DecimalFormat;

import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class BMICalculatorTest {

	@Test
	public final void testBMI() {
		float height = (float) 1.87; 
		float mass = 80;  
		double bmi_ex = 22.87741;
		Person p2 = new Person(); 
		p2.setHeight(height); 
		p2.setMass(mass);
		double bm = BMICalculator.BMI(p2); 
		DecimalFormat dec = new DecimalFormat("#0.00000");
		System.out.println(dec.format(bm));
		double a = Double.parseDouble(dec.format(bm));
		assertTrue(a== bmi_ex);

	}

}
>>>>>>> origin/master
