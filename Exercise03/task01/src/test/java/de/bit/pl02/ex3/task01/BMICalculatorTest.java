package de.bit.pl02.ex3.task01;

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
