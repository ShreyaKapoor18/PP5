package de.bit.pl02.ex03.task02;

import java.util.Scanner;

import de.bit.pl02.ex03.task02.Person;

public class BMICalculator{
	   
    public static double calculateBmi(Person person)
    {
        return person.getMass() / Math.pow(person.getHeight(), 2);
    }
   
    public static void main(String args[])
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Namaste, please type your mass(in kg), press Enter and then your height(in m)!");
       
        float mass = scanner.nextFloat();
        float height = scanner.nextFloat();
        Person P = new Person(mass, height);
       
        double bmi = BMICalculator.calculateBmi(P);
        System.out.println("Your BMI is: "); System.out.println(bmi);
        if (scanner.hasNext()) {
            scanner.close();
        }
    }
}