package entities;

import java.time.LocalDate;
import java.time.LocalTime;
/**
 * Reservation class represents a time slot in the next 1 month during the restaurant opening hours, in which a customer has created a reservation.
 *
 */
public class Reservation implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;

	public Reservation(LocalDate rDate, LocalTime rTime, int pax, String name, String num, Table tab){
		this.rDate = rDate;
		this.rTime = rTime;
		this.pax = pax;
		this.table = tab;
		this.name = name;
		this.num = num;
	}
	
	public LocalDate rDate;
	public LocalTime rTime;	
	public int pax;
	public Table table;
	private String name;
	private String num;
	
	public String getName() {
		return this.name;
	}
	
	public String getNum() {
		return this.num;
	}

	
}