package databasescanner;
import java.time.*;
import java.util.ArrayList;
import java.util.Arrays;

import entities.*;
import mainapplicationdriver.ApplicationClass;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * This class implements methods to read and write on the database(.dat files)
 */

public class databaseRW {
	
	/**
	    * This method writes to the input data file
	    * @param obj is an array of the the specific object to be written. For example, Orders array.
	    * @param fileName the file the object needs to be wrriten to
	    */	
	public static void write(Object[] obj, String fileName) {//general write method
		
		try {	
			FileOutputStream f = new FileOutputStream(new File(fileName));
			ObjectOutputStream o = new ObjectOutputStream(f);
			for(int i=0;i<obj.length; i++){//ends at end of array
					o.writeObject(obj[i]);
			}
			o.close();	
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	    * This method writes existing menu items to the to the Menu Data file
	    * @param obj the specific menu item to be written.
	    * @param fileName the file the menu item needs to be writen to.
	    */	
public static void writeMenu(ArrayList<MenuItem> obj, String fileName) {//general write method
		
		try {	
			FileOutputStream f = new FileOutputStream(new File(fileName));
			ObjectOutputStream o = new ObjectOutputStream(f);
			for(int i=0;i<obj.size();i++){//ends at end of array
					o.writeObject(obj.get(i));
			}
			o.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	

/**
 * This method writes to the input table file
 * @param obj the specific table to be written.
 * @param fileName the file the object needs to be written to
 */
public static void writeTables(ArrayList<Table> obj, String fileName) {//general write method
	
	try {	
		FileOutputStream f = new FileOutputStream(new File(fileName));
		ObjectOutputStream o = new ObjectOutputStream(f);
		for(int i=0;i<obj.size();i++){//ends at end of array
				o.writeObject(obj.get(i));
		}
		o.close();
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	}catch (IOException e) {
		e.printStackTrace();
	}
}

/**
 * This method writes to the input table file
 * @param obj the specific object to be written.
 * @param fileName the file the object needs to be written to. 
 */
public static void writeOrders(ArrayList<Order> obj, String fileName) {//general write method
	
	try {	
		FileOutputStream f = new FileOutputStream(new File(fileName));
		ObjectOutputStream o = new ObjectOutputStream(f);
		for(int i=0;i<obj.size();i++){//ends at end of array
				o.writeObject(obj.get(i));
		}
		o.close();
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	}catch (IOException e) {
		e.printStackTrace();
	}
}

/**
 * This is a general method that reads from a given file name
 * @param fileName the file from where the object needs to be written from.
 * @return temporary array
 */
	public static Object[] read(String fileName) {//general read method
		ArrayList<Object> temp=new ArrayList<Object>();
		try {
			FileInputStream fi = new FileInputStream(new File(fileName));
			ObjectInputStream oi = new ObjectInputStream(fi);
			while(oi.available()==0) {//read until end of file
				temp.add(oi.readObject());
			}
			oi.close();
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (EOFException e) {
			
		}catch (IOException e) {
			e.printStackTrace(); //TODO hits exception
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return temp.toArray();
	}
	
	/**
	    * This method creates a file called table.data where all the table data is stored.
	    * @param table the list with updated table objects.
	    */
	public static void writeTable(Table[] table) {
		String fileName = new String("tables.dat");
		write(table, fileName);
	}
	
	 /**
	    * This method reads from the table file.
	    * @return contains a list of table objects read from the file
	    */
	public static Table[] readTable() {
		String filepath = ApplicationClass.finalDir + "\\tables.dat";
		Object[] temp1 = read(filepath);
		Table[] temp = new Table[temp1.length];
		for(int i=0;i<temp1.length;i++){
				temp[i] = (Table)temp1[i];
				//System.out.println(temp[i].tableId);
		}
		return temp;
	}
	
	/**
	    * This method reads from the staff.dat file.
	    * @return contains a list of staff objects read from the file
	    */
	public static Staff[] readStaff() {
		String fileName = new String("staff.dat");
		Object[] temp1 = read(fileName);
		Staff[] temp = new Staff[temp1.length];
		for(int i=0;i<temp1.length;i++){
				temp[i]=(Staff)temp1[i];
		}
		return temp;
	}
	
	/**
	    * This method reads from the order file.
	    * @return contains a list of order objects read from the file
	    */
	public static Order[] readOrder() {
		String filepath = ApplicationClass.finalDir + "\\order.dat";
		Object[] temp1 = read(filepath);
		Order[] temp = new Order[temp1.length];
		for(int i=0;i<temp1.length;i++){
				temp[i]=(Order)temp1[i];
		}
		return temp;
	}

	  /**
  * This method reads from the invoice file.
  * @return Order[] contains a list of order objects read from the file
  */
	public static Order[] readInvoices() {
		String filepath = ApplicationClass.finalDir + "\\invoices.dat";
		Object[] temp1 = read(filepath);
		Order[] temp = new Order[temp1.length];
		for(int i=0;i<temp1.length;i++){
				temp[i]=(Order)temp1[i];
		}
		return temp;
	}
	
	  /**
  * This method reads from the menu file.
  * @return contains a list of menu item objects read from the file
  */
	public static MenuItem[] readItem() {
		
		String filepath = ApplicationClass.finalDir + "\\menu.dat";
		Object[] temp1 = read(filepath);
		MenuItem[] temp = new MenuItem[temp1.length];
		for(int i=0;i<temp1.length;i++){
				temp[i]=(MenuItem)temp1[i];
		}
		return temp;
	}

	/**
	    * This method reads from the reservation file.
	    * @return contains a list of table objects read from the file
	    */
	public static Table[] readReservation() {
		
		String filepath = ApplicationClass.finalDir + "\\reservations.dat";	
		Object[] temp = read(filepath); 
		Table[] temp1 = new Table[temp.length];
		for(int i=0;i<temp.length;i++){
			temp1[i]=(Table)temp[i];
		}
		return temp1;
	}
	
	/**
	    * This method reads from the members file.
	    * @return contains a list of Member objects read from the file
	    */	
public static Member[] readMembers() {
		
		String filepath = ApplicationClass.finalDir + "\\members.dat";	
		Object[] temp = read(filepath); 
		Member[] temp1 = new Member[temp.length];
		for(int i=0;i<temp.length;i++){
			temp1[i]=(Member)temp[i];
		}
		return temp1;
	}
}
