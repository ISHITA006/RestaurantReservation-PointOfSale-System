package userinterface;
import java.util.Scanner;

import manager.TableManager;
import userinterface.ReservationInterface;

/**
 * Boundary Class that facilitates seating a customer who has just arrived at the restaurant
 *
 */
public class CustomerToSeatInterface {
/**
 * Method that implements the userinterface to seat the guest.
 *
 */
	public static void seat() {
		int choice, pax;
		String number;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter choice: \n[1] Walk-in \n[2] Reservation");
		choice = sc.nextInt();
		switch(choice) {
			case 1:
				userinterface.OrderInterface.addNewOrderUI();
				break;
			case 2:
				userinterface.ReservationInterface.checkIn();				
		}
	}
}
