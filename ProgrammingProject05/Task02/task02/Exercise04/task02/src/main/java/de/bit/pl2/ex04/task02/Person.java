package de.bit.pl2.ex04.task02;

public class Person {
	
	public String firstName;
	public String lastName;
	private String matriculationNumber;
	private String studyProgram;
	
	public Person(String firstName, String lastName, String matriculationNumber, String studyProgram) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.matriculationNumber = matriculationNumber;
		this.studyProgram = studyProgram;
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getMatriculationNumber() {
		return matriculationNumber;
	}
	public void setMatriculationNumber(String matriculationNumber) {
		this.matriculationNumber = matriculationNumber;
	}
	public String getStudyProgram() {
		return studyProgram;
	}
	public void setStudyProgram(String studyProgram) {
		this.studyProgram = studyProgram;
	}
	
}

