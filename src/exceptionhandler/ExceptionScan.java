package exceptionhandler;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Exception Scan class builds on the Scanner class to only take appropriate input from the user. 
 * It notifies the user on wrong input entry and queries again.
 *
 */
public class ExceptionScan {
	private static Scanner sc = new Scanner(System.in);
	/**
	   * This method modifies the Scanner object's .nextInt method to only take input in a given integer range.
		 * Therefore, it does both type check and error error checking
		 * It also displays an error message and reminds the user to take in special input 
		 * @param  lower the LB of the number depending on the interface scenario
		 * @param  upper the UP of the number depending on the interface scenario
		 * @return a valid integer number entry
	 */
	public static int nextInt(int lower,int upper) {
		int choice = upper;
		boolean checkInt;
		
		do{ 
			if (choice > upper || choice < lower)
				System.out.println("You keyed in " + choice + ", Please select between " + lower + " - " + upper);
			checkInt = false;
			do{ 
				try{
					choice = sc.nextInt();
					checkInt = true;
				}
				catch(InputMismatchException e){
					System.out.println("Please input an integer, and not strings / characters");
					sc.nextLine();
				}
			}while(!checkInt);
		}while(choice > upper || choice < lower);
		
		return choice;
	}

	/**
	 * This method modifies the Scanner object's .nextDouble method
	 * It does both type check and error error checking
	 * It also displays an error message and reminds the user to take in special input 
	 * @return User input's double
	 */
	public static double nextDouble() {
		double choice = 0.00;
		boolean checkDouble = false;
		
		do{
			try{
				choice = sc.nextDouble();
				checkDouble = true;
			}
			catch(InputMismatchException e){
				System.out.println("Please input in a valid double");
				sc.nextLine();
			}
		}while(!checkDouble);
		
		return choice;
		}
}


