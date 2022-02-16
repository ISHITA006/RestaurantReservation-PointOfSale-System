package manager;
import manager.ReservationManager;

import java.util.*;

import databasescanner.databaseRW;
import entities.Restaurant;
import entities.Table;
import mainapplicationdriver.ApplicationClass;

/**
 * This class handles assigning tables to incoming guests.
 */
public class TableManager {
	
	/**
	  * This method helps customer sit at a table .
	  * @param pax the number of people that want to be seated together and get a common invoice.
	  */
	public static void seatWalkIn(int pax) {
		for(int i=0;i<entities.Restaurant.reservations.size();i++) {
			if(entities.Restaurant.reservations.get(i).numOfSeats >= pax && entities.Restaurant.reservations.get(i).isOccupied==false && entities.Restaurant.reservations.get(i).tableReservation==null) {
				//setTableStatus(entities.Restaurant.sessionReservations, entities.Restaurant.sessionReservations.get(i).tableId, true);
				System.out.println("Customer successfully seated!");
				return;
			}
		}
	}
	
	/**
	  * This method checks if the queried table has expired.
	  * @param tableID the desired table's tableID
	  * @param status the desired status of that table that should replace the current status
	  */
	public static void setTableStatus(int tableID, boolean status) {
		for(int i=0;i<Restaurant.tables.size();i++) {
			if(Restaurant.tables.get(i).tableId == tableID) {
				Restaurant.tables.get(i).isOccupied = status;
				return;
			}
		}
		
		String fileloc = ApplicationClass.finalDir + "\\tables.dat";
		databaseRW.writeTables(Restaurant.tables, fileloc);
	}
	
}
