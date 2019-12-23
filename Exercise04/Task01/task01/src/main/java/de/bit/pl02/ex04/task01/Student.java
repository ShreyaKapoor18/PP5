package de.bit.pl02.ex04.task01;

import java.util.ArrayList;
import java.util.Scanner;



public class Student extends Person{
	private double matriculationNumber; 
	public enum studyProgram{
		CompSec, CS, CSE 
	}
	private studyProgram study; 
	public static void main(String args[])
	{
		ArrayList<Student> l = new ArrayList<Student>();
		 
		Scanner si = new Scanner(System.in);
		System.out.println("Enter the first and last name with spaces"); 
		while(!si.hasNextInt()) {
		Student e = new Student();
		String firstName = si.next(); 
		String lastName = si.next(); 
		e.setFirstName(firstName); 
		e.setLastName(lastName); 
		l.add(e); } 
		si.close(); 
		System.out.println(l.size());
		for (int i = 0; i< l.size(); i++)
		{ 
			System.out.println(l.get(i).getFirstName()+" " +l.get(i).getLastName());
			
			
		}
	}
	public double getMatriculationNumber() {
		return matriculationNumber;
	}
	public void setMatriculationNumber(double matriculationNumber) {
		this.matriculationNumber = matriculationNumber;
	}
	public studyProgram getStudy() {
		return study;
	}
	public void setStudy(studyProgram study) {
		this.study = study;
	}
	

	

}
