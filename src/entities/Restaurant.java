package entities;
import java.util.ArrayList; 
import java.util.Arrays;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import databasescanner.databaseRW;
import entities.FoodItem.FoodType;
import mainapplicationdriver.ApplicationClass;

import java.time.*;

/**
 * Restaurant class creates lists which hold and initialize important restaurant objects such as tables, staff, etc.
 *
 */

public class Restaurant {
	public static ArrayList<Table> tables;
	public static ArrayList<MenuItem> menuItems;
	public static ArrayList<Order> orders;
	public static ArrayList<Order> invoices;
	public static ArrayList<Staff> staff;
	public static ArrayList<Table> reservations;
	public static ArrayList<Member> members;

	public static void initRestaurant() {
		initMenu();
		initTable();
		initStaff();
		initOrders();
		initInvoices();
		initReservations();
		initMembers();
	}
	
	
	public static void initMenu() {
		menuItems = new ArrayList<MenuItem>();
		MenuItem[] temp = new MenuItem[30];//used fixed int because size==0
			temp = databaseRW.readItem();
			for(int i=0;i<temp.length;i++)
				menuItems.add(temp[i]);
	}

	
	public static void initTable(){
		
		tables = new ArrayList<Table>();
		Table[] temp = new Table[30];//used fixed int because size==0
			temp = databaseRW.readTable();
			for(int i=0;i<temp.length;i++)
				tables.add(temp[i]); 
				
	
////////////////////////////////////////////////////////////////////////////
		/*int j = 1;
		tables = new ArrayList<Table>();
			for(int i = 0; i < 5; i++){
				tables.add(new Table(2, j));
				j++;
			}
			for(int i = 0; i < 5; i++){
				tables.add(new Table(4, j));
				j++;
			}
			
			for(int i = 0; i < 2; i++){
				tables.add(new Table(6, j));
				j++;
			}
			for(int i = 0; i < 2; i++){
				tables.add(new Table(10, j));
				j++;
			}
			String filepath = ApplicationClass.finalDir + "\\tables.dat";
			databaseRW.write(tables.toArray(), filepath); */ 
///////////////////////////////////////////////////////////////////////////////////////////////////
	}
	
	public static void initStaff() {
//		
//			Staff[] temp = new Staff[30];//used fixed int because size==0
//			temp = databaseRW.readStaff();
//			for(int i=0;i<temp.length;i++)
//				staff.add(temp[i]);
//	
		
		  	staff = new ArrayList<Staff>();
		  	String filepath = ApplicationClass.finalDir + "\\staff.dat";
			Staff staff_1 = new Staff(0, "Sanjana", "Waitress", Staff.GenderType.GENDER_FEMALE);
			Staff staff_2 = new Staff(1, "Ishita", "Chef", Staff.GenderType.GENDER_FEMALE);
			Staff staff_3 = new Staff(2, "Divyansh", "Manager", Staff.GenderType.GENDER_MALE);
			staff.add(staff_1);
			staff.add(staff_2);
			staff.add(staff_3);
			databaseRW.write(staff.toArray(), filepath);
		
	}
	
	public static void initOrders() {
		orders = new ArrayList<Order>();
			Order[] temp = new Order[30];//used fixed int because size==0
			temp = databaseRW.readOrder();
			for(int i=0;i<temp.length;i++)
				orders.add(temp[i]);
	}
	
	public static void initInvoices(){
		invoices = new ArrayList<Order>();		
		Order[] tempInvoices = databaseRW.readInvoices();
		for(int i=0;i<tempInvoices.length;i++)
			invoices.add(tempInvoices[i]);
	}
	
	public static void initReservations() {
		reservations = new ArrayList<Table>();		
		Table[] tempReservations = databaseRW.readReservation();
		for(int i=0;i<tempReservations.length;i++)
			reservations.add(tempReservations[i]);
	}
	
	public static void initMembers() {
		members = new ArrayList<Member>();		
		Member[] tempMembers = databaseRW.readMembers();
		for(int i=0;i<tempMembers.length;i++)
			members.add(tempMembers[i]);
	}
	
	
		
}
