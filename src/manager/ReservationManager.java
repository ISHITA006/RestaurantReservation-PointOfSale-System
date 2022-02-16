package manager;
import java.util.ArrayList; 
import java.util.Arrays;

import databasescanner.databaseRW;
import entities.Reservation;
import entities.Restaurant;
import entities.Table;

import java.io.File;
import java.time.*;

/**
 * This class deals with managing reservations creation and deletion.
 */

public class ReservationManager {
	
	/**
  * This method checks if the queried table has expired.
  * @param  reservationTable the queried table that you need to check reservation expiry status for 
  */
	public static void checkReservationExpiry(Table reservationTable) {

		if((reservationTable.tableReservation.rDate.isBefore(LocalDate.now()))){
			cancelReservation(reservationTable);
		}
		else if((reservationTable.tableReservation.rDate.isEqual(LocalDate.now()))) {
			if((reservationTable.tableReservation.rTime.plusMinutes(30)).isBefore(LocalTime.now())) {
			cancelReservation(reservationTable);	
			//Free up table for walk-in customers
	    }
		}
	}
	
	/**
	  * This method clears all reservations that have expired at running time
	  */
	public static void clearExpiredReservations() {
		
		if(Restaurant.reservations.size()==0) {
			return;
		}
		for(int i=0; i<Restaurant.reservations.size(); i++) {
			checkReservationExpiry(Restaurant.reservations.get(i));
		}
		//System.out.println("Expired reservations removed!");
	}
	
	/**
	  * This method is to seat a customer who has a reseravation.
	  * @param  number contact numnber you need to check the reservation for. 

	  */
	public static void reservationArrival(String number) {
		int i=0;
		for(i=0;i<Restaurant.reservations.size();i++) {
				if(Restaurant.reservations.get(i).tableReservation.getNum().equals(number)) {
					int tableID = Restaurant.reservations.get(i).tableId;
					cancelReservation(Restaurant.reservations.get(i));
					System.out.println("Customer successfuilly seated!");
					userinterface.OrderInterface.addNewOrderUI(tableID);
					return;
				}
			}
		
		System.out.println("Reservation does not exist");
	}
	
	
	/**
	  * This method updates a table and reservation object. 
	  * @param  tableID the desired tableID
	  * @param  r the edited request
	  */
	public static void update(int tableID, Reservation r) {

			
		int tableCount =0;
		for(tableCount=0; tableCount<Restaurant.tables.size(); tableCount++) {
			if(Restaurant.tables.get(tableCount).tableId== tableID) {
				Restaurant.tables.get(tableCount).tableReservation= r;
				break;}
		}
		
		
		boolean exists = false;
		for(int i=0; i<Restaurant.reservations.size(); i++) {
			//if reservation for that table already exists, then update/ overrite
			if(Restaurant.reservations.get(i).tableId == tableID) {
				Restaurant.reservations.get(i).tableReservation= r;
				exists= true; 
				break;
			}
		}
		
		if(exists==false) {
			//push new reservation into the array
			Restaurant.reservations.add(Restaurant.tables.get(tableCount));
		}
		
		databasescanner.databaseRW.write(Restaurant.reservations.toArray(), mainapplicationdriver.ApplicationClass.finalDir + "\\reservations.dat");
		databasescanner.databaseRW.write(Restaurant.tables.toArray(), mainapplicationdriver.ApplicationClass.finalDir+"\\tables.dat");
	}
	
	
	/**
	  * This method is used to cancel a reservation.
	  * @param  reservationTable the table that needs to become free now that there is no reservation.
	  */
	public static void cancelReservation(Table reservationTable) {
		
	
		for(int i=0;i<Restaurant.reservations.size();i++) {
			if(Restaurant.reservations.get(i).tableId == reservationTable.tableId) {
				Restaurant.reservations.remove(i);
			}
		}
			
		
		for(int j=0; j<Restaurant.tables.size(); j++) {
			if(Restaurant.tables.get(j).tableId == reservationTable.tableId) {
				Restaurant.tables.get(j).tableReservation = null;
				break;
			}
	}
		
		databasescanner.databaseRW.write(Restaurant.reservations.toArray(), mainapplicationdriver.ApplicationClass.finalDir+"\\reservations.dat");
		databasescanner.databaseRW.write(Restaurant.tables.toArray(), mainapplicationdriver.ApplicationClass.finalDir+"\\tables.dat");
		
	}
}
