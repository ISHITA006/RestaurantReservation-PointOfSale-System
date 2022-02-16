package userinterface;

import java.util.ArrayList;
import java.util.Scanner;

import databasescanner.databaseRW;
import entities.Order;
import entities.Restaurant;
import entities.Staff;
import entities.Table;
import exceptionhandler.ExceptionScan;
import mainapplicationdriver.ApplicationClass;
import manager.OrderManager;
import manager.TableManager;
/**
 * Boundary class that provides interface for the user to implement all the order related functionalities
 *
 *
 */
public class OrderInterface {
	/**
	 * Method that implements UI to show all the possible functionalities that can be implemented related to Orders
	 */
	public static void orderMainOptions() {
		int choice;
		do {
			System.out.println("\nORDER");
			
			System.out.println("[1] - View Current Orders");
			System.out.println("[2] - Create Orders");
			System.out.println("[3] - Modify Orders");
			System.out.println("[4] - Remove Orders");
			System.out.println("[5] - Make Payment");
			System.out.println("[0] - Go Back");
			
			choice = ExceptionScan.nextInt(0, 5);
			
			switch(choice) {
				case 1:
					viewCurrentOrderUI();
					break;
				case 2:
					addNewOrderUI();
					break;
				case 3:
					modifyOrderUI();
					break;
				case 4:
					removeOrderUI();
					break;
				case 5:
					makePaymentUI();
					break;
				case 0:
					return;
			}
		}while (true);
	}
	
	/**
	 * This method UI prints all the orders that are currently pending. These orders haven't been billed yet.
	 */
	private static void viewCurrentOrderUI() {
		int idx, choice;
		ArrayList<Order> orderList = OrderManager.getRestaurantOrders();
		
		if (orderList.size() < 1) {
			System.out.println("There are no orders currently, You must have at least 1 order to view the list of orders");
			return;
		}
		
		do {
			idx = 0;
			System.out.println("\nThese are the current orders, which order do you want more details on");
			for(Order order: orderList)
				System.out.println("[" + (idx++ + 1) + "] - " + order.getName());
			System.out.println("[" + (0) + "] - " + "Go Back");
			
			choice = ExceptionScan.nextInt(0, idx);
			
			if (choice == 0)
				break;
			
			OrderManager.printOrderDetails(orderList.get(choice - 1));
		} while(true);
		
	}
	
	/**
	 * This method prints the interface for the user to start a new order. This method will assign a random table to the user
	 */
	public static void addNewOrderUI() {
		Staff staff = selectStaffUI();
		Table table = selectTableUI();
		TableManager.setTableStatus(table.tableId, true);
		OrderManager.addNewOrder(staff, table);
	}

  /**
	 * This method prints the interface for the user to start a new order.
   * @param  tableId the unique table number of the desired table
	 */
	
	public static void addNewOrderUI(int tableId) {
		Staff staff = selectStaffUI();
		
		TableManager.setTableStatus(tableId, true);
		Table table = Restaurant.tables.get(tableId);
		OrderManager.addNewOrder(staff, table);
	}
	/**
	 * This method implements UI for the user to select tables froma  list of available tables.
	 * 
	 */
	private static Table selectTableUI() {
		int idx = 0;
		
		ArrayList<Integer> temp = new ArrayList<Integer>();
		//ArrayList<Table> tableList = OrderManager.getRestaurantTables();
		
		System.out.println("These are the list of available tables in the restaurant, please choose according to your pax ?");
		System.out.println("Choice\tTableID\tNumber of Seats");
		for (int i=0;i<14;i++) {
			try {
				if(entities.Restaurant.tables.get(i).isOccupied == false && entities.Restaurant.tables.get(i).tableReservation ==null) {
					System.out.println("[" + (idx++ + 1) + "] - " + "\t" + entities.Restaurant.tables.get(i).tableId + "\t" + entities.Restaurant.tables.get(i).numOfSeats);
					temp.add(entities.Restaurant.tables.get(i).tableId);
				}
			}
			catch(IndexOutOfBoundsException e){
				if(entities.Restaurant.tables.get(i).isOccupied == false) {
					System.out.println("[" + (idx++ + 1) + "] - " + "\t" + entities.Restaurant.tables.get(i).tableId + "\t" + entities.Restaurant.tables.get(i).numOfSeats);
					temp.add(entities.Restaurant.tables.get(i).tableId);
				}
			}
		}
		int choice = ExceptionScan.nextInt(1, idx);
		choice = temp.get(choice - 1);
		for(Table table : entities.Restaurant.tables)
			if(table.tableId == choice)
				return table;
		//return tableList.get(0);
		return null;
			
	}
	
	/**
	 * This method implements the UI for the user to select a staff that can serve the customer.
	 * @return
	 */
	private static Staff selectStaffUI() {
		int idx = 0;
		
		ArrayList<Staff> staffList = OrderManager.getRestaurantStaff();
		System.out.println("Which Staff is keying this order ?");
		for (Staff staff : staffList)
			System.out.println("[" + (idx++ + 1) + "] - " + staff.toString());
		
		int choice = ExceptionScan.nextInt(1, idx);
		
		return staffList.get(choice - 1);
		
	}
	/**
	 * This method provides the UI which prints the functionalities that are assosciated with changing current order information.
	 */
	private static void modifyOrderUI() {
		int idx, choice;
		
		idx = 0;
		ArrayList<Order> orderList = OrderManager.getRestaurantOrders();
		
		if (orderList.size() < 1) {
			System.out.println("There are no orders currently, You must have at least 1 order to modify an order");
			return;
		}
		
		System.out.println("\nThese are the current orders, which do you want to modify ?");
		for(Order order: orderList)
			System.out.println("[" + (idx++ + 1) + "] - " + order.getName());
		System.out.println("[" + (0) + "] - " + "Back");
		
		choice = ExceptionScan.nextInt(0, idx);
		
		if (choice == 0)
			return;
		
		System.out.println("\nDo you want to add or remove items to the order");
		System.out.println("[1] - Add");
		System.out.println("[2] - Remove");
		System.out.println("[0] - Back");
		int choice_1;
		
		
		
		choice_1 = ExceptionScan.nextInt(0, 2);		
		switch(choice_1) {
			case 1:
				Order updatedOrder = OrderManager.addFoodItemToOrder(orderList.get(choice - 1));
				orderList.set(choice-1, updatedOrder);		
				entities.Restaurant.orders= orderList;
				databaseRW.writeOrders(orderList, ApplicationClass.finalDir + "\\order.dat");	
				break;
			case 2:
				Order updateOrder = OrderManager.removeFoodItemFromOrder(orderList.get(choice - 1));
				orderList.set(choice-1, updateOrder);
				entities.Restaurant.orders= orderList;
				databaseRW.writeOrders(orderList, ApplicationClass.finalDir + "\\order.dat");
				break;
			case 0:
				break;
			}

		
	}
	
	/**
	 * This method implements the UI for the user to cancel an order.
	 */
	private static void removeOrderUI() {
		int idx = 0;
		ArrayList<Order> orderList = OrderManager.getRestaurantOrders();
		
		if (orderList.size() < 1) {
			System.out.println("There are no orders currently, You must have at least 1 order to remove an order");
			return;
		}
		System.out.println("Which Order you wanna remove?");
		for (Order order : orderList)
			System.out.println("[" + (idx++ + 1) + "] - " + order.getName());
		System.out.println("[" + (0) + "] - Back");
		
		int choice = ExceptionScan.nextInt(0, idx);
		if (choice == 0)
			return;
		
		OrderManager.removeOrder(choice - 1);
		
	}
	
	/**
	 * This method implements a UI to make a payment. It basically finalizes an order as billed and adds to the order invoice.
	 */
	private static void makePaymentUI() {
		int idx = 0;
		ArrayList<Order> orderList = OrderManager.getRestaurantOrders();
		
		if (orderList.size() < 1) {
			System.out.println("There are no orders currently, You must have at least 1 order to complete an order");
			return;
		}
		
		System.out.println("Which of the following is the order being paid for?");
		for (Order order : orderList)
			System.out.println("[" + (idx++ + 1) + "] - " + order.getName());
		System.out.println("[" + (0) + "] - Back");
		
		int choice = ExceptionScan.nextInt(0, idx);
		if (choice == 0)
			return;
		
		String mem;
		do {
		System.out.println("Is the customer making payment a restaurant member?\nEnter(Y/N)");
		mem = new Scanner(System.in).next();
		if(mem.equals("Y")) {
			System.out.println("Enter the contact number of the member: ");
			String contact = new Scanner(System.in).next();
			boolean isMember = manager.MemberManager.checkMember(contact);
			if(isMember) {
				OrderManager.completeOrder(choice - 1, true);
				return;
			}
			else {
				System.out.println("No member registered with this contact number!");
			}
		}
		}while(!mem.equals("N"));
		
		OrderManager.completeOrder(choice - 1, false);

	}
}
