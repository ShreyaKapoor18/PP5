package de.bit.pl2.ex04.task01;

import junit.framework.TestCase;

public class testAList extends TestCase {
	
	// instantiate a new person to be tested
	Person tperson = new Person();
	
	public void testAppend(Person tperson) {
		AList<Person> testList = new AList<Person>();
		testList.append(tperson);
		assertTrue(testList.getSize() == 1);
		
	}
	
	public void testInsert(Person tperson) {
		AList<Person> testList = new AList<Person>();
		testList.insert(tperson);
		assertTrue(testList.getSize() == 1);
	}
	
	public void testRemove() {
		AList<Person> testList = new AList<Person>();
		Person p1 = new Person();
		Person p2 = new Person();
		testList.append(p1);
		testList.append(p2);
		testList.remove();
		assertTrue(testList.getSize() == 1);
	}
}


