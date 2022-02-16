package entities;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

import exceptionhandler.ExceptionScan;

import java.util.Date;
/**
 * Order class represents a list of menu items that the customer has ordered. It's also used to generate an order invoice
 * @author JOSHHH
 *
 */
public class Order implements Serializable {
	private static final long serialVersionUID = 4743545238689522397L;
	private ArrayList<MenuItem> listOfItems;
	private Staff staffAssigned;
	private Table table;
	private Date timestamp;
	private String name;
	public boolean member;
	private boolean invoiced;
	/**
	 * Constructor for Order
	 * @param staffAssigned Staff that keys in the specific Order
	 * @param table Table assigned to the order
	 */
	public Order(Staff staffAssigned, Table table) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		this.staffAssigned = staffAssigned;
		this.setTable(table);
		this.listOfItems = new ArrayList<MenuItem>();
		this.timestamp = new Date();
		this.setName(this.staffAssigned.toString() + ", " + formatter.format(this.timestamp));
		this.setMember(false);
		this.setInvoiced(false);
		
	}
	/**
	 * Adds a Menu Item to the Order
	 * @param menuItemToAdd MenuItem to be added to an Order instance
	 */
	public void addFood(MenuItem menuItemToAdd) {
		this.listOfItems.add(menuItemToAdd);
	}
	
	/**
	 * Facilitates the addition of MenuItems to an Order instance
	 * @param menuList Current list of Restaurant's MenuItems. Used to retrieve the list of MenuItems to add to order
	 */
	public void addFood(ArrayList<MenuItem> menuList) {
		
		int idx = 0;
		int i = 0;
		ArrayList<Integer> mapping = new ArrayList<Integer>();
		
		if (menuList.size() < 1) {
			System.out.println("There are no items in the menu currently, You must have at least 1 menu item to add");
			return;
		}
		
		System.out.println();
		System.out.println("Which food would you like to add to the [" + this.getName() + "] Order?");
		
		for(; i < menuList.size(); i++) {
			if (menuList.get(i) instanceof FoodItem) {
				System.out.println("[" + (idx++ + 1) + "] - " + menuList.get(i).getName());
				mapping.add(i);
			}
		}
		
		System.out.println();
		int choice = ExceptionScan.nextInt(1, idx);
		
		FoodItem foodToAdd = (FoodItem) menuList.get(mapping.get(choice - 1));
		this.listOfItems.add(foodToAdd);
		System.out.println(foodToAdd.getName() + " was successfully add to [" + this.getName() + "] Order");
		
	}
	/**
	 * Removes MenuItems from the Order Instance
	 */
	public void removeFood() {
		int idx = 0;
		
		if (this.listOfItems.size() < 1) {
			System.out.println("There are no items in this order, you must have at least 1 item in the order to remove");
			return;
		}
		
		System.out.println();
		System.out.println("Which food would you like to remove from the " + this.getName() + " Invoice?");
		for(MenuItem menuItem: this.listOfItems)
			System.out.println("[" + (idx++ + 1) + "] - " + menuItem.getName());
		
		System.out.println();
		int choice = 1;
		choice = ExceptionScan.nextInt(1, idx);
		
		String removedItemName = this.listOfItems.get(choice - 1).getName();
		this.listOfItems.remove(choice - 1);
		System.out.println(removedItemName + " was successfully removed from the " + this.getName() + " Invoixw");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public ArrayList<MenuItem> getListOfItems() {
		return listOfItems;
	}
	
	public Staff getStaffAssigned() {
		return staffAssigned;
	}
	
	public void setStaffAssigned(Staff staffAssigned) {
		this.staffAssigned = staffAssigned;
	}
	
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public Table getTable() {
		return table;
	}
	public void setTable(Table table) {
		this.table = table;
	}
	public boolean isInvoiced() {
		return invoiced;
	}
	public void setInvoiced(boolean invoiced) {
		this.invoiced = invoiced;
	}
	public void setMember(boolean isMember) {
		this.member = isMember;
	}
	
	public boolean getMember(){
		return this.member;
	}

}
