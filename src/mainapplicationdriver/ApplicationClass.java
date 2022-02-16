package mainapplicationdriver;
import exceptionhandler.ExceptionScan;
import userinterface.*;

import java.nio.file.Paths;

import java.time.LocalTime;

import databasescanner.databaseRW;
import entities.Reservation;
import entities.Restaurant;
import entities.Table;


/**
 * Main Application Class for the Restaurant. This is the main interface which calls sub-interface classes.
 * 
 *
 */

public class ApplicationClass {
	
	/**
	 * userDirectory gets us the current working directory in string format
	 */
	public static String userDirectory = Paths.get("").toAbsolutePath().toString();
	
	/**
	 * This is the addition to the current directory path that helps us access all data files used.
	 */
	public static String finalDir = userDirectory + "\\src";
	
	/**
	 * This is the main function that drives the RRPOS.
	 * @param args default
	 */
	public static void main(String[] args) {
		int choice = 1;
		Restaurant.initRestaurant();
		
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("Welcome to our Restaurant!");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
		
		do {
			System.out.println("Enter your choice ?\n");
			
			System.out.println("[1] - Menu");
			System.out.println("[2] - Order");
			System.out.println("[3] - Reservation");
			System.out.println("[4] - Seat Customer");
			System.out.println("[5] - Sales Report");
			System.out.println("[6] - Members");
			System.out.println("[7] - View Available Tables");
			System.out.println("[0] - Go Back");
			
			choice = ExceptionScan.nextInt(0, 11);
			
			switch(choice) {
				case 1:
					userinterface.MenuInterface.menuItemMainOptions();
					break;
				case 2:
					userinterface.OrderInterface.orderMainOptions();
					break;
				case 3:
					userinterface.ReservationInterface.reservationMainOptions();
					break;
				case 4:
					userinterface.CustomerToSeatInterface.seat();
					break;
				case 5:
					userinterface.SalesReportInterface.salesReportMainOptions();
					break;
				case 6:
					userinterface.MemberInterface.memberMainOptions();
					break;					
				case 7:
					
					manager.ReservationManager.clearExpiredReservations();
					System.out.println("Displaying available tables:");
					System.out.println("============================");
					for(int i=0;i<14;i++) {
						if(entities.Restaurant.tables.get(i).isOccupied == false && entities.Restaurant.tables.get(i).tableReservation == null) {
							Table temp = entities.Restaurant.tables.get(i);
							System.out.printf("\nTable ID:%d\nPax:%d\n", temp.tableId, temp.numOfSeats);
						}
					}
					System.out.println("============================");
					
					break;
				case 11:
					userinterface.MemberInterface.memberMainOptions();
					break;
				case 0:
					System.out.println("APPLICATION TERMINATED");
					
					databaseRW.write(entities.Restaurant.tables.toArray(), finalDir+"\\tables.dat");
					databaseRW.write(Restaurant.staff.toArray(), finalDir+"\\staff.dat");
					databaseRW.write(Restaurant.orders.toArray(), finalDir+"\\order.dat");
					databaseRW.write(Restaurant.invoices.toArray(),finalDir+"\\invoices.dat");
					databaseRW.write(Restaurant.menuItems.toArray(), finalDir+"\\menu.dat");
					return;
			}
		}while (true);
	}

}
