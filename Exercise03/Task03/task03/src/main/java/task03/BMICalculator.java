package task03;

public class BMICalculator {

	private static double BMI;
	public static double BMI(Person P)
    { 
    		BMI = P.getMass()/(P.getHeight()* P.getHeight()); 
    		return BMI; 
    	
    }
	public static void main(String[] args)
	{	if (args.length >0)
		{
		System.out.println("Enter the mass (in kg) and the height (in m)");
		Person P = new Person(); 
		System.out.println(args); 
		P.setMass(Double.parseDouble(args[0]));
		P.setHeight(Double.parseDouble(args[1])); 
		System.out.println(BMI(P)); 
		}
	} 

}

