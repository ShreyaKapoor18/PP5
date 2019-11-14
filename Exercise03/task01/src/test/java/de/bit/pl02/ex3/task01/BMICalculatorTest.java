package de.bit.pl02.ex3.task01;

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

