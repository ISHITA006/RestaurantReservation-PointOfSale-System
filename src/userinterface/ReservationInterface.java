package userinterface;
	
import manager.ReservationManager;

import java.util.Scanner;

import entities.Reservation;
import entities.Restaurant;
import entities.Table;
import exceptionhandler.ExceptionScan;

import java.time.*;

/**
	 * This method prints the interface for the restaurant staff to choose if they want to view, create or delete reservations
*/

public class ReservationInterface {
	
	public static void reservationMainOptions() {
		int choice2;
		do {
			System.out.println("\nRESERVATIONS");
			
			System.out.println("[1] - View All Reservations");
			System.out.println("[2] - New Reservation");
			System.out.println("[3] - Remove Reservation");
			System.out.println("[4] - Clear Expired Reservations");
			System.out.println("[0] - Go Back");
			
			choice2 = ExceptionScan.nextInt(0, 5);
			
			switch(choice2) {
				case 1:
					displayReservations();
					break;
				case 2:
					CreateReservation();
					break;					
				case 3:
					cancelReservation();
					break;						
				case 4:					
					ReservationManager.clearExpiredReservations();
					System.out.println("Expired reservations cleared successfully!\n");
					break;
				case 0:
					return;
			}
		}while (true);
	}
	
	public static void CreateReservation() {
		
		ReservationManager.clearExpiredReservations();
		Scanner sc = new Scanner(System.in);
		String buff;
		
		System.out.println("Enter name: ");
		String name = sc.nextLine();
		
		System.out.println("Enter date of reservation in the format of YYYY-MM-DD: ");
		LocalDate date	= LocalDate.parse(sc.nextLine());
		LocalDate today = LocalDate.now();
		//Check that reservation date is not more than 1 month from now
		if(date.isAfter(today.plusMonths(1))) {
			System.out.println("Sorry, we only accept bookings 1 month in advance");
			return;
		}
		//Check reservation date is not in the past
		if(date.isBefore(today)) {
			System.out.println("Choose valid date!");
			return;
		}
				
		System.out.println("Enter time of arrival in format of hh:mm");
		LocalTime time = LocalTime.parse(sc.nextLine());
		//check validity of time
		while((time.isBefore(LocalTime.parse("11:00")) || time.isAfter(LocalTime.parse("20:00")))) {
			System.out.println("Sorry, the restaurant operates only from 11:00-20:00");
			System.out.println("Enter time of arrival in format of hh:mm");
			time = LocalTime.parse(sc.nextLine());
		}
		
		System.out.println("Enter number of pax:");
		int pax = sc.nextInt();
		buff = sc.nextLine();
		
		int choice=0;
		
		do{
			if(choice==1) {
				System.out.println("Enter date of reservation in the format of YYYY-MM-DD: ");
				date = LocalDate.parse(sc.nextLine());
				//Check that reservation date is not more than 1 month from now
				if(date.isAfter(today.plusMonths(1))) {
					System.out.println("Sorry, we only accept bookings 1 monnth in advance");
					return;
				}
				//Check reservation date is not in the past
				if(date.isBefore(today)) {
					System.out.println("Choose valid date!");
					return;
				}
				
				System.out.println("Enter time of arrival in format of hh:mm");
				time = LocalTime.parse(sc.nextLine());
				//check validity of session times
				if((time.isBefore(LocalTime.parse("11:00")) || time.isAfter(LocalTime.parse("20:00")))) {
					System.out.println("Sorry, the restaurant operates only from 11:00-20:00");
				}
				
			}
			
			
			for(int i=0; i<Restaurant.tables.size();i++) {
				if(Restaurant.tables.get(i).numOfSeats>=pax && Restaurant.tables.get(i).isOccupied==false && Restaurant.tables.get(i).tableReservation==null) {
					System.out.println("Enter contact number to confirm reservation: ");
					String number = sc.nextLine();
					Reservation r = new Reservation(date, time, pax, name, number, Restaurant.tables.get(i));
					manager.ReservationManager.update(Restaurant.tables.get(i).tableId, r); //check
					System.out.println("Reservation successfully created!");
					return;
				}
			}
			
			System.out.println("Sorry, we are fully booked!");
			System.out.println("Enter your choice: \n[1] Choose another date and time \n[2] Exit");
			choice = sc.nextInt();
			buff = sc.nextLine();
		} while(choice<2);
	}
	
	public static void checkIn() {
		Scanner sc = new Scanner(System.in);
		String number;
		
		System.out.println("Enter number used to confirm reservation:");
		number = sc.nextLine();
		manager.ReservationManager.reservationArrival(number);
	}
	
	public static void cancelReservation() {
		Scanner sc = new Scanner(System.in);
		LocalDate date;
		String number, buff;
		
		System.out.println("Enter date of reservation to cancel in format YYYY-MM-DD:");
		date = LocalDate.parse(sc.nextLine());
		

		System.out.println("Enter number used to confirm reservation:");
		number = sc.nextLine();
		
		boolean found = false;
		int i=0;
		for(i=0;i<Restaurant.reservations.size();i++) {
				if((Restaurant.reservations.get(i).tableReservation.getNum().equals(number)) && (Restaurant.reservations.get(i).tableReservation.rDate.isEqual(date))) {
					found= true;
					break;
				}	
		}
		if(found==false)System.out.println("Reservation not found!");
		else {
			
			manager.ReservationManager.cancelReservation(Restaurant.reservations.get(i));
			System.out.println("Reservation cancelled successfully!");
			
			}
		}

	
	public static void displayReservations() {
		Scanner sc = new Scanner(System.in);
		LocalDate date;
		String buff;
		ReservationManager.clearExpiredReservations();
		
		System.out.println("Enter your choice: \n[1] View all reservations \n[2] View Reservations for a particular date \n[0] Go Back");
		int choice = sc.nextInt();
		buff = sc.nextLine();
		
		
		switch(choice) {
		case 1:
			if(Restaurant.reservations.size()==0) {
				System.out.println("No Current Reservations!\n");
				return;
			}
			
			System.out.println("Displaying all reservations:");
			System.out.println("========================");
			int i=0;
			for(i=0; i<Restaurant.reservations.size(); i++) {
				Reservation r = Restaurant.reservations.get(i).tableReservation;
				System.out.printf("Reservation at %s, %s for %d pax.\nTable number: %d\nName: %s\nContact Number: %s\n\n", r.rTime.toString(), r.rDate.toString(), r.pax, r.table.tableId, r.getName(), r.getNum());
			}
			System.out.println("========================");
			
			break;
			
		case 2:
			
			if(Restaurant.reservations.size()==0) {
				System.out.println("No Current Reservations!");
				System.out.println();
				return;
			}
			
			System.out.println("Enter date of reservation to display in format YYYY-MM-DD:");
			date = LocalDate.parse(sc.nextLine());
			
			boolean exists = false;
			
			for(int k=0; k<Restaurant.reservations.size(); k++) {
				Reservation r = Restaurant.reservations.get(k).tableReservation;
				if(r.rDate.isEqual(date)) { exists= true; break;}
			}
			
			if(!exists) {
				System.out.println("No reservations for chosen date!\n");
				return;				
			}
			 
			System.out.println("Displaying reservations for "+ date.toString() +":");
			System.out.println("========================");
			int j=0;
			for(j=0; j<Restaurant.reservations.size(); j++) {
				Reservation r = Restaurant.reservations.get(j).tableReservation;
				if(r.rDate.isEqual(date)) {
						
						System.out.printf("Reservation at %s, %s for %d pax.\nTable number: %d\nName: %s\nContact Number: %s\n\n", r.rTime.toString(), r.rDate.toString(), r.pax, r.table.tableId, r.getName(), r.getNum());
				}
			}		
			System.out.println("========================");
			System.out.println("Reservations displayed");
			break;
		
		case 0:
			break;
			
		}
	}
			
		
}
