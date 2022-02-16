package entities;
import java.io.Serializable;
import java.util.ArrayList; 
import java.util.Arrays;

/**
 * Table class enacts a real table where customers are seated or the tables are kept empty for reservations.
 *
 */

public class Table implements Serializable {
	private static final long serialVersionUID = 4743545238689522397L;
	public int numOfSeats;
	public int tableId;
	public boolean isOccupied;
	public Reservation tableReservation;
	
	public Table(int numOfSeats, int tableId) { 
		this.numOfSeats = numOfSeats;
		this.isOccupied = false;
		this.tableId = tableId;
	}
}