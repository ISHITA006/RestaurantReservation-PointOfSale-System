package entities;

import java.io.Serializable;

/**
 * This is the fundamental class that represents the entities displayed on the menu. It gives information of the Menu Item like name, description and price.
 * Base MenuItem object class that encompasses standard information of items on menu. Extended to SetPackages / FoodItems
 * @author JOSHHH
 *
 */
public abstract class MenuItem implements Serializable {
	
	private static final long serialVersionUID = 4743545238689522397L;
	private String name;
	private String description;
	private double price;
	
	/**
	 * Constructor for MenuItem
	 * @param name Name of MenuItem
	 * @param description Description of MenuItem
	 * @param price Price of MenuItem
	 */
	public MenuItem(String name, String description, double price){ 
		this.setName(name);
		this.setDescription(description);
		this.setPrice(price);
	}
	/**
	 * Getter Method for MenuItem Name
	 * @return MenuItem name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Setter Method for MenuItem Name
	 * @param name String to modify name attribute into
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Getter method for description
	 * @return MenuItem Description
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Setter Method for description
	 * @param description String to modify description attribute into
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * Getter Method for Price
	 * @return MenuItem Price
	 */
	public double getPrice() {
		return price;
	}
	
	/**
	 * Setter Method for Price
	 * @param price Price to modify MenuItem price into
	 */
	public void setPrice(double price) {
		this.price = price;
	}

}
