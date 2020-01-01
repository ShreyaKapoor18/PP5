package de.bit.pl2.ex04.task01;

import junit.framework.TestCase;

public class testStudent extends TestCase {
	public void testStudentIO() {
		Student tStudent = new Student();
		tStudent.setstudyProgram(studyProgram.CS);
        tStudent.setmatriculationNumber(3185606);
        tStudent.setlastName("Krix");
        tStudent.setfirstName("Sophia");
        assertEquals(3185606,tStudent.getmatriculationNumber());
        assertEquals("Sophia",tStudent.getfirstName());
        assertEquals("Krix",tStudent.getlastName());
        assertEquals(studyProgram.CS,tStudent.getstudyProgram());
    	}
	}
