package de.bit.pl02.ex04.task01;

import java.util.ArrayList;
import java.util.Scanner;

public class List extends Student {
	ArrayList<Student> l = new ArrayList<Student>();
	public void insert(Person P)
	{ 
		Student s = new Student(); 
		s.setFirstName(P.getFirstName());
		s.setLastName(P.getLastName());
		l.add(s);  
	}
	public void insert(Student s)
	{ 
		l.add(s); 
	}
	public void print()
	{
		for (int i=0; i<l.size(); i++)
		{ 
			System.out.println(l.get(i).getFirstName()+' '+l.get(i).getLastName()+ ' '+ l.get(i).getMatriculationNumber()+' '+ l.get(i).getStudy()); 
		}
	}
	public static void main(String[] args)
	{ 
		List l = new List(); 
		Scanner si = new Scanner(System.in);
		System.out.println("Enter the first and last name with spaces"); 
		while(!si.hasNextInt()) {
		String firstName = si.next(); 
		String lastName = si.next();
		if (si.hasNextDouble())
			{ 
			Student e = new Student();
			double matriculationNumber = si.nextDouble(); 
			studyProgram s = studyProgram.valueOf(si.next());
			e.setFirstName(firstName); 
			e.setLastName(lastName); 
			e.setMatriculationNumber(matriculationNumber);
			e.setStudy(s);
			l.insert(e);  
			} 
		else 
			{
			Person p = new Person();
			p.setFirstName(firstName);
			p.setLastName(lastName);
			l.insert(p);
			}	
		}
		l.print();
		si.close(); 
	}

 }

