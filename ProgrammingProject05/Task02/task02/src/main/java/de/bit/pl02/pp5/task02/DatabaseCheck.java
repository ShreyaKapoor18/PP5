
package de.bit.pl02.pp5.task02;
import java.sql.DriverManager;
import java.util.Scanner;

	public class DatabaseCheck {
		private String name;
		DatabaseCheck(String name)
		{ 
			this.name = name; 
	 	}
		private void Connect_DB() {
			try {
				Class.forName("org.sqlite.JDBC"); 
				DriverManager.getConnection("jdbc:sqlite:" + this.name + ".db"); 
			}catch(Exception e) {
				System.out.println(e.getMessage()); 
			}
		}
		public void main(String[] args)
		{ 
			Scanner input = new Scanner(System.in); 
			DatabaseCheck database = new DatabaseCheck(name);
			input.close();
			database.Connect_DB(); 
			String s = ".png .jpeg"; 
			String[] a = s.split(".png|.jpeg"); 
			for (String x:a)
			{
				System.out.println(a);
			}
			
		}
		

	}

