package de.bit.pl2.ex04.task01;

public class Student extends Person {

	private int matriculationNumber;	
	private studyProgram sProgram;
		
	
	// setter methods 
	public void setmatriculationNumber(int matriculationNumber ) {
		this.matriculationNumber = matriculationNumber; }
	public void setstudyProgram(studyProgram sProgram) {
		this.sProgram = sProgram;}
	
	// getter methods 
	public int getmatriculationNumber() { return this.matriculationNumber; }
	public studyProgram getstudyProgram() { return this.sProgram; }
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
