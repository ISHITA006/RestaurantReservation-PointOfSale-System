package userinterface;

import java.util.Scanner;

import databasescanner.databaseRW;
import entities.FoodItem;
import entities.MenuItem;
import entities.SetPackage;

import java.util.ArrayList;

import exceptionhandler.ExceptionScan;
import mainapplicationdriver.ApplicationClass;
import manager.MenuIManager;
/**
 * Boundary Class that facilitates actions between users and the MenuItem Objects
 *
 */
public class MenuInterface {
	private static Scanner sc = new Scanner(System.in);
	/**
	 * This method provides the interface print for the user to perform various ations related to the menu system
	 */
	public static void menuItemMainOptions() {
		int choice;
		do {
			System.out.println("\nMENU");
			
			System.out.println("[1] - View menu");
			System.out.println("[2] - Add new food item to menu");
			System.out.println("[3] - Add new Set Package to menu");
			System.out.println("[4] - Remove item from menu");
			System.out.println("[5] - Update item from menu");
			System.out.println("[0] - Go Back");
			
			choice = ExceptionScan.nextInt(0, 5);
			
			switch(choice) {
				case 1:
					viewMenuItemUI();
					break;
				case 2:
					addNewFoodItemUI();
					break;
				case 3:
					addNewSetPackageUI();
					break;
				case 4:
					removeMenuItemUI();
					break;
				case 5:
					updateMenuItemUI();
					break;
				case 0:
					return;
			}
		}while (true);
	}
	
	/**
	 * This methods implements the UI that prints the current menu of the resaturant on the console
	 */
	public static void viewMenuItemUI() {
		int choice, idx;
		ArrayList<MenuItem> menuItemList = MenuIManager.getRestaurantMenu();
		
		if (menuItemList.size() < 1) {
			System.out.println("There are no items in the menu, You must have at least 1 item in the menu to view the menu");
			return;
		}
		
		do {
			idx = 0;
			System.out.println("\nThese are the items on the menu");
			for(MenuItem menuItem : menuItemList)
				System.out.println("[" + (idx++ + 1) + "] - " + menuItem.getName());
			System.out.println("[" + (0) + "] - " + "Back");
			
			System.out.println("\nChoose menu item to see details: ");
			choice = ExceptionScan.nextInt(0, idx);
			
			if (choice == 0)
				break;
			
			MenuItem selectedItem = menuItemList.get(choice - 1);
			if (selectedItem instanceof entities.FoodItem)
				MenuIManager.printFoodItemDetails((entities.FoodItem) selectedItem);
			else if (selectedItem instanceof entities.SetPackage)
				MenuIManager.printSetPackageDetails((entities.SetPackage) selectedItem);
			else
				System.out.println("DEBUGGERINO");
		} while(true);
		
	}
	/**
	 * This method provides the print of the interface on the console to add new food items to the restaurant menu
	 */
	private static void addNewFoodItemUI() {
		System.out.println();
		
		System.out.println("What is the name of the new Food Item?");
		String name = sc.nextLine();
		
		System.out.println("What is the description of the new Food Item?");
		String description = sc.nextLine();
		
		System.out.println("What is the price of the new Food Item?");
		double price;
		price = exceptionhandler.ExceptionScan.nextDouble();

		
		FoodItem.FoodType foodType = returnFoodItemCourseType();
		MenuIManager.addNewFoodItem(name, description, price, foodType);
		
	}
	/**
	 * This method provides the print of the interface on the console to add promotional set package items to the restaurant menu.
	 */
	private static void addNewSetPackageUI() {		
		System.out.println("What is the name of the new Set Package?");
		String name = sc.nextLine();
		
		System.out.println("What is the description of the new Set Package?");
		String description = sc.nextLine();
		
		System.out.println("What is the price of the new Set Package?");
		double price = exceptionhandler.ExceptionScan.nextDouble();

		
		MenuIManager.addNewSetPackage(name, description, price);
	}
	
	/**
	 * This method implements the UI in case the user wants to remove a set package or food item from the menu
	 */
	private static void removeMenuItemUI() {
		int idx = 0, choice;
		ArrayList<MenuItem> menuItemList = MenuIManager.getRestaurantMenu();
		
		
		if (menuItemList.size() < 1) {
			System.out.println("There are no items in the menu, You must have at least 1 item in the menu to remove");
			return;
		}
		
		System.out.println("Which Menu Item do you wanna remove?");
		for(MenuItem menuItem : menuItemList) 
			System.out.println("[" + (idx++ + 1) + "] - " + menuItem.getName());
		System.out.println("[" + (0) + "] - Back");
		
		choice = ExceptionScan.nextInt(0, idx);
		if (choice == 0)
			return;
		
		MenuIManager.removeMenuItem(choice - 1);
		
	}
	
	/**
	 * UI provided to user to facilitate the updating of details of MenuItems in Restaurant Menu
	 */
	private static void updateMenuItemUI() {
		int idx = 0, choice;
		ArrayList<MenuItem> menuItemList = MenuIManager.getRestaurantMenu();
		
		if (menuItemList.size() < 1) {
			System.out.println("There are no items in the menu, You must have at least 1 item in the menu to remove");
			return;
		}
		
		System.out.println("Which Menu Item do you wanna update?");

		for(MenuItem menuItem : menuItemList)
			System.out.println("[" + (idx++ + 1) + "] - " + menuItem.getName());
		System.out.println("[" + (0) + "] - " + "Back");
		
		choice = ExceptionScan.nextInt(0, idx);
		
		if (choice == 0)
			return;
		
		if (menuItemList.get(choice - 1) instanceof FoodItem)
			updateFoodItemUI(choice - 1);
		else if (menuItemList.get(choice - 1) instanceof SetPackage) 
			updateSetPackageUI(choice - 1);
		else 
			System.out.println("DEBUGGERINO");
		
		return;
	}
	
	/**
	 * Method that implements the UI to update details about already registered food items on the menu
	 * @param idx index number of the desired food item to be udpated.
	 */
	private static void updateFoodItemUI(int idx) {
		System.out.println("What do you want to update about this item ?");
		
		System.out.println("[1] - Name");
		System.out.println("[2] - Description");
		System.out.println("[3] - Price");
		System.out.println("[4] - Food Type");
		System.out.println("[0] - Back");
		
		int choice = ExceptionScan.nextInt(0, 4);
		
		switch(choice) {
			case 1:
				System.out.println("What do you want to update the name to?");
				MenuIManager.updateMenuItemName(idx, sc.nextLine());
				break;
			case 2:
				System.out.println("What do you want to update the description to?");
				MenuIManager.updateMenuItemDescription(idx, sc.nextLine());
				break;
			case 3:
				System.out.println("What do you want to update the price to?");
				double updatePrice = 0.00;
				updatePrice = ExceptionScan.nextDouble();
				MenuIManager.updateMenuItemPrice(idx, updatePrice);
				break;
			case 4:
				MenuIManager.updateFoodItemType(idx, returnFoodItemCourseType());
				break;
			case 0:
				break;
		}
		
		return;
		
	}
	/**
	 * UI provided to user to faciliate the updating of a specific SetPackage's details
	 * @param idx index of SetPackage in Restaurant menu to be updated
	 */
	private static void updateSetPackageUI(int idx) {
		System.out.println("What do you want to update about this item ?");
		ArrayList<MenuItem> menuItemList = MenuIManager.getRestaurantMenu();
		
		System.out.println("[1] - Name");
		System.out.println("[2] - Description");
		System.out.println("[3] - Price");
		System.out.println("[4] - Add Food Items");
		System.out.println("[5] - Remove Food Items");
		System.out.println("[0] - Back");
		
		int choice = ExceptionScan.nextInt(0, 5);
		
		switch(choice) {
			case 1:
				System.out.println("What do you want to update the name to?");
				MenuIManager.updateMenuItemName(idx, sc.nextLine());
				break;
			case 2:
				System.out.println("What do you want to update the description to?");
				MenuIManager.updateMenuItemDescription(idx, sc.nextLine());
				break;
			case 3:
				System.out.println("What do you want to update the price to?");
				double updatePrice = 0.00;
				updatePrice = ExceptionScan.nextDouble();
				MenuIManager.updateMenuItemPrice(idx, updatePrice);
				break;
			case 4:
				MenuIManager.addFoodItemToSetPackage((SetPackage) menuItemList.get(idx));
				break;
			case 5:
				MenuIManager.removeFoodItemFromSetPackage((SetPackage) menuItemList.get(idx));
				break;
			case 0:
				break;
		}
		
		String finalPath = ApplicationClass.finalDir + "\\menu.dat";
		databaseRW.writeMenu(menuItemList, finalPath);
		
		return;
		
	}
	
	/**
	 * UI method to set the type of the food item.
	 * @return FoodType that is required by caller method
	 */
	private static FoodItem.FoodType returnFoodItemCourseType(){
		System.out.println("Which of the following Food Type do you want the Food Item to be?\n");
		
		int idx = 0;
		for(FoodItem.FoodType foodTypeLoop : FoodItem.FoodType.values())
			System.out.println("[" + (idx++ + 1) + "] - " + foodTypeLoop);
		
		int choice = ExceptionScan.nextInt(1, idx);
			
		return FoodItem.FoodType.values()[choice - 1];
	}
}
