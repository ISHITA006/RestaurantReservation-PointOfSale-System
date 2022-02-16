package manager;

import java.util.ArrayList;
import java.util.Scanner;

import databasescanner.databaseRW;
import entities.FoodItem;
import entities.MenuItem;
import entities.Restaurant;
import entities.SetPackage;
import mainapplicationdriver.ApplicationClass;
import userinterface.MenuInterface;

/**
 * Menu item Manager class that manages creation, removal and modifications to Menu 
 */

public class MenuIManager {
	private static ArrayList<MenuItem> menuItemList = Restaurant.menuItems;
	private static Scanner sc = new Scanner(System.in);
	
	/**
	 * This method obtains the current menu of the restaurant and returns it
	 * @return Menu of the restaurant
	 */
	public static ArrayList<MenuItem> getRestaurantMenu(){
		return menuItemList; 
	}
	
	/**
	 * This is a sub userinterface class which prints the details of the food items ordered.
	 * @param food Food whose details is to be printed
	 */
	public static void printFoodItemDetails(FoodItem food) {
		System.out.println();
		System.out.println("Food Name: " + food.getName());
		System.out.println("Food Description: " + food.getDescription());
		System.out.println("Food Price: " + food.getPrice());
		System.out.println("Food course type: " + food.getType());
	}
	
	/**
	 * This method prints the details of the promotional set packages. 
	 * @param setPackage the queried promotional set package
	 */
	public static void printSetPackageDetails(SetPackage setPackage) {
		System.out.println();
		System.out.println("Set Package Name: " + setPackage.getName());
		System.out.println("Set Package Description: " + setPackage.getDescription());
		System.out.println("Set Package Price: " + setPackage.getPrice());
		
		int idx = 0;
		System.out.println("The " + setPackage.getName() + " contains these food items");
		for(FoodItem foodItem : setPackage.getFoodList())
			System.out.println("[" + (idx++ + 1) + "] - " + foodItem.getName());
		
	}
	
	/**
	 * This method is used to introduce a new food to the restaurant menu
	 * @param name Food name
	 * @param description Food details and any necessary information.
	 * @param price Food price excluding GST and discounts.
	 * @param foodType Identifies whether the food is a MAIN COURSE/ DESSERT/ DRINK
	 */
	public static void addNewFoodItem(String name, String description, double price, FoodItem.FoodType foodType) {
		FoodItem newFood;
		newFood = new FoodItem(name, description, price, foodType);
		menuItemList.add((MenuItem) newFood);
		System.out.println();
		System.out.println("Food " + newFood.getName() + " has been added to the Food Menu");
//		FoodItem[] newItems = new FoodItem[1];
//		newItems[0] = newFood;
		String finalPath = ApplicationClass.finalDir + "\\menu.dat";
		databaseRW.writeMenu(menuItemList, finalPath);
	}
	
	/**
	 * This method is used to introduce a new promotional set package to the restaurant menu
	 * @param name Set Package name
	 * @param description Set Pacakge's food item details
	 * @param price Set Package price
	 */
	public static void addNewSetPackage(String name, String description, double price) {
		SetPackage newSetPackage;
		newSetPackage = new SetPackage(name, description, price);
		newSetPackage = addFoodItemToSetPackage(newSetPackage);
		menuItemList.add((MenuItem) newSetPackage);
		System.out.println();
		System.out.println("Package " + newSetPackage.getName() + " has been added to the Package Menu");
		String finalPath = ApplicationClass.finalDir + "\\menu.dat";
		databaseRW.writeMenu(menuItemList, finalPath);
		
	}
	
	/**
	 * This method is used to add a food item to an existing promtional set package
	 * @param setPackage desired promotional set package
	 * @return setPackage edited promotional set package
	 */
	public static SetPackage addFoodItemToSetPackage(SetPackage setPackage) {
		String choice = "", compare = "Y";
		do { 
			setPackage.addFood(menuItemList);
			System.out.println("Input \"Y\" to add more food to the Set Package, or other characters to stop adding");
			choice = sc.nextLine();
		}while (choice.toUpperCase().equals(compare));
		
		return setPackage;
	}
	
	/**
	 * This meethod is used to remove an existing food item from an existing set package. 
	 * @param setPackage SetPackage to be removed.
	 */
	public static void removeFoodItemFromSetPackage(SetPackage setPackage) {
		String choice = "", compare = "Y";
		
		do{
			setPackage.removeFood();
			System.out.println("Input \"Y\" to remove food to the Set Package, or other characters to stop removing");
			choice = sc.nextLine();
		}while (choice.toUpperCase().equals(compare));
		
		
		return;
	}
	
	/**
	 * This method is used to take away existing menu items from the current menu.
	 * @param idx the menu item index.
	 */
	public static void removeMenuItem(int idx) {
		try{
			String removedMenuItemName = menuItemList.get(idx).getName();
			menuItemList.remove(idx);
			System.out.println("Successfully removed " + removedMenuItemName + " from the menu");
		}
		catch(IndexOutOfBoundsException e) {
			System.out.println("Removal of Food Item failed, invalid index");
		}
		
		String finalPath = ApplicationClass.finalDir + "\\menu.dat";
		databaseRW.writeMenu(menuItemList, finalPath);
		
	}
	/**
	 * This method is used to change the name of a menu item
	 * @param  idx index of the menu item that has been queried
	 * @param name String to modify menuItem name into something
	 */
	public static void updateMenuItemName(int idx, String name) {
		String old_name = menuItemList.get(idx).getName();
		menuItemList.get(idx).setName(name);
		System.out.println("Successfully Updated " + old_name + " name to " + name);
		
		String finalPath = ApplicationClass.finalDir + "\\menu.dat";
		databaseRW.writeMenu(menuItemList, finalPath);
	}
	
	/**
	 * This method is used to change the description of a menu item.
	 * @param  idx index of the menu item that has been queried
	 * @param  description contains the desired description which should replace the current menu item description
	 */
	public static void updateMenuItemDescription(int idx, String description) {
		menuItemList.get(idx).setDescription(description);
		System.out.println("Successfully Updated " + menuItemList.get(idx).getName() + " description to " + description);
		
		String finalPath = ApplicationClass.finalDir + "\\menu.dat";
		databaseRW.writeMenu(menuItemList, finalPath);
	}
	
	/**
	 * This method is used to change the price of a menu item.
	 * @param  idx index of the menu item that has been queried
	 * @param  price New price that need to replace the existing price
	 */
	public static void updateMenuItemPrice(int idx, double price) {
		menuItemList.get(idx).setPrice(price);
		System.out.println("Successfully Updated " + menuItemList.get(idx).getName() + " price to " + price);
				
		String finalPath = ApplicationClass.finalDir + "\\menu.dat";
		databaseRW.writeMenu(menuItemList, finalPath);
	}
	
	/**
	 * This method is used to change the food type of a food item in the menu.
	 * @param  idx index of the menu item that has been queried
	 * @param type the new type of the food(MAIN COURSE/ DESSERT/ DRINK)
	 */
	public static void updateFoodItemType(int idx, FoodItem.FoodType type) {
		FoodItem foodToBeUpdated = (FoodItem) menuItemList.get(idx);
		foodToBeUpdated.setType(type);
		System.out.println("Successfully Updated " + foodToBeUpdated.getName() + " Course Type to " + type);
		
		String finalPath = ApplicationClass.finalDir + "\\menu.dat";
		databaseRW.writeMenu(menuItemList, finalPath);
	}

}
