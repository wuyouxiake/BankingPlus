import java.util.Scanner;
public class Validator{
	public static String getString(Scanner sc,String prompt){
		System.out.println(prompt);
		String s1=sc.next();
		String s2=sc.nextLine();
		String s=s1+s2;
		return s;
	}
	
	public static int getInt(Scanner sc, String prompt){
		int i=0;
		boolean isValid =false;
		while(isValid==false){
			System.out.println(prompt);
			if(sc.hasNextInt()){
				i=sc.nextInt();
				isValid=true;
			}
			else{
				System.out.println("Error! Invalid input "
									+"Try again.");
			}
			sc.nextLine();
		}
		return i;
	}
	public static int getInt(Scanner sc, String prompt, int min, int max){
		int i=0;
		boolean isValid=false;
		while(isValid==false){
			i=getInt(sc, prompt);
			if(i<=min){
				System.out.println("Error! Number must be greater than "+min+".");
			}else if(i>=max){
				System.out.println("Error! Nuber must be less than "+max+".");	
			}else{isValid=true;}
		}
		return i;
	}
	public static double getDouble(Scanner sc, String prompt){
		double d=0;
		boolean isValid=false;
		while(isValid==false){
			System.out.println(prompt);
			if(sc.hasNextDouble()){
				d=sc.nextDouble();
				isValid=true;
			}else{
				System.out.println("Error! Invalid decimal value!"+"   Try again!");
			}
			sc.nextLine();
			
		}
		return d;
	}
	public static double getDouble(Scanner sc, String prompt, double min, double max){
		double d=0;
		boolean isValid = false;
		while(isValid ==false){
			d=getDouble(sc, prompt);
			if(d<=min){
				System.out.println("Number must be greater than "+min+".");
			}else if(d>=max){
				System.out.println("Number must be less than "+max+".");
			}else{isValid=true;}
		}
		return d;
	}
	public static String getType(Scanner sc, String prompt){
		String d = null;
		boolean isValid = false;
		while(isValid ==false){
			System.out.println(prompt);
			d=sc.next();
			if(d.equalsIgnoreCase("d")||d.equalsIgnoreCase("dc")||d.equalsIgnoreCase("w")||d.equalsIgnoreCase("c")||d.equalsIgnoreCase("q")){
				isValid=true;
			}else{
				System.out.println("Transaction type must be c, dc, w or d");
			}
		}	
		return d;
	}
}