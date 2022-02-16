package manager;

import java.util.ArrayList;
import java.util.Scanner;

import databasescanner.databaseRW;
import entities.MenuItem;
import entities.Order;
import entities.Restaurant;
import entities.Staff;
import entities.Table;
import mainapplicationdriver.ApplicationClass;

/**
 * This class deals with handling lists of following objects : order, menuItem, Table, Order, Staff
 */
public class OrderManager {
	private static Scanner sc = new Scanner(System.in);
	private static ArrayList<Order> orderList = Restaurant.orders;
	private static ArrayList<MenuItem> menuItemList = Restaurant.menuItems;
	private static ArrayList<Table> tableList = Restaurant.tables;
	private static ArrayList<Order> invoiceList = Restaurant.invoices;
	private static ArrayList<Staff> staffList = Restaurant.staff;
	
	/**
	 * Gets all the resaturant orders.
	 * @return Array of Restaurant Orders
	 */
	public static ArrayList<Order> getRestaurantOrders(){
		return orderList; 
	}
	
	/**
	 * Gets all the resaturant tables.
	 * @return Array of Restaurant Tables
	 */
	public static ArrayList<Table> getRestaurantTables(){
		return tableList; 
	}
	
	/**
	 * Gets all the resaturant staff.
	 * @return Array of Restaurant Staff
	 */
	public static ArrayList<Staff> getRestaurantStaff(){
		return staffList; 
	}
	
	/**
	 * Display order details
	 * @param order Prints detail of input Order object
	 */
	public static void printOrderDetails(Order order) {
		System.out.println();
		System.out.println("*********************** Order Details ***************************");
		System.out.println("Order ID \t: " + order.getName());
		System.out.println("Staff \t\t: " + order.getStaffAssigned());
		System.out.println("Table ID\t: " + order.getTable().tableId);
		System.out.println("DateTime \t: " + order.getTimestamp().toString());
		
		System.out.println("\n\t=================Items Ordered =================\t\n");
		int idx = 0;
		for(MenuItem menuItem : order.getListOfItems()) {
			System.out.println((idx++ + 1) + ". " + menuItem.getName() + " - " + menuItem.getPrice());
		}
		System.out.println();

	}
	
	/**
	 * When a new order is created, this methods add this order to the new order list.
	 * @param staff the person who is keying the order
	 * @param table the table to which the order is being assigned to 
	 */
	public static void addNewOrder(Staff staff, Table table) {
		Order newOrder = new Order(staff, table);
		newOrder = addFoodItemToOrder(newOrder);
		table.isOccupied = true;
		orderList.add(newOrder);
		entities.Restaurant.orders= orderList;
		String filepath = ApplicationClass.finalDir + "\\order.dat";
		databaseRW.writeOrders(orderList, filepath);
		
	}
	
	/**
	 * This method is used to set an additonal menu item to the order list.
	 * @param  order The desired order that needs to be modified
	 * @return  The returned order
	 */
	public static Order addFoodItemToOrder(Order order) {
		String choice = "", compare = "Y";
		do { 
			order.addFood(menuItemList);
			System.out.println("Input \"Y\" to add more food to the order, or other characters to stop adding");
			choice = sc.nextLine();
		}while (choice.toUpperCase().equals(compare));
		
		return order;
	}
	
	/**
	 * Removes Menu Items from the order
	 * @param order input Order object to be modified
	 */
	public static Order removeFoodItemFromOrder(Order order) {
		String choice = "", compare = "Y";
		do { 
			order.removeFood();
			System.out.println("Input \"Y\" to remove more food from the order, or other characters to stop adding");
			choice = sc.nextLine();
		}while (choice.toUpperCase().equals(compare));
		
		return order;
	}
	
	/**
	 * This method takes away a desired item from an existing order.
	 * @param  idx order index of the desired order to be deleted.
	 */
	public static void removeOrder(int idx) {
		String removedOrderName = orderList.get(idx).getName();
		orderList.remove(idx);
		System.out.println("Successfully removed " + removedOrderName + " from the list of current orders");
		entities.Restaurant.orders= orderList;
		String filepath = ApplicationClass.finalDir + "\\order.dat";
		databaseRW.writeOrders(orderList, filepath);
	}
	
	/**
	 * This method finalizes an order. The customer is satisfied with the service and wants to complete the transaction. The customer has no further requests and is leaving the Restaurant.
	 * @param  idx index of order in Restaurant Order which is to be completed
	 */
	public static void completeOrder(int idx, boolean isMember) {
		
		Order completedOrder = orderList.get(idx);
		String removedOrderName = completedOrder.getName();
		completedOrder.setMember(isMember);
		completedOrder.setInvoiced(true);
		Table curTable = completedOrder.getTable();
		TableManager.setTableStatus(curTable.tableId, false);
		
		orderList.remove(idx);
		invoiceList.add(completedOrder);	
		
		entities.Restaurant.orders= orderList;
		entities.Restaurant.invoices = invoiceList;
		
		
		System.out.println("\n~~~~~~~~~~Order Invoice~~~~~~~~~~\n");
		printOrderInvoice(completedOrder);
		System.out.println("\nSuccessfully completed [" + removedOrderName + "] Order");
	
		databaseRW.writeOrders(orderList, ApplicationClass.finalDir+"\\order.dat");
		databaseRW.writeOrders(invoiceList, ApplicationClass.finalDir+"\\invoices.dat");
	}
	
	public static void printOrderInvoice(Order order) {
		
		
		double discount;

		
		System.out.println();
		System.out.println("*********************** RECEIPT ***************************");
		System.out.println("Order ID \t: " + order.getName());
		System.out.println("Staff \t\t: " + order.getStaffAssigned());
		System.out.println("Table ID\t: " + order.getTable().tableId);
		System.out.println("DateTime \t: " + order.getTimestamp().toString());
		
		System.out.println("\n\t================= ITEMS ORDERED =================\t\n");
		double price = 0;
		int idx = 0;
		for(MenuItem menuItem : order.getListOfItems()) {
			System.out.println((idx++ + 1) + ". " + menuItem.getName() + " - " + menuItem.getPrice());
			price += menuItem.getPrice();
		}
		
		
		if(order.getMember()) {
			discount = 0.1;
		}
		else {
			discount = 0;
		}
		
		System.out.printf("\nSUBTOTAL\t\t:%.2f \n", price);
		System.out.printf("GST ( 7%% )\t\t:%.2f \n", price * 0.07);
		System.out.printf("SERVICE TAX ( 10 %% )\t:%.2f \n",  price * 0.10);
		System.out.printf("MEMBERSHIP DISCOUNT ( 10 %% )\t:%.2f \n",  price * discount);
		System.out.printf("GRAND TOTAL\t\t:%.2f \n", price * (1.17- discount));
		System.out.println("*********************** RECEIPT END ***********************");
		System.out.println();

	}
}
