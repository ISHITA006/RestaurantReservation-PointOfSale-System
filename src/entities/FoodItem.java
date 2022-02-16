package entities;

/**
 * This class represents the Ala Carte items on the menu.It extends from Menu Items
 * 
 */
public class FoodItem extends MenuItem{
	private static final long serialVersionUID = -1762180885408039220L;
	public enum FoodType {MAIN_COURSE, DRINK, DESSERT};
	private FoodType type;
	
	/**
	 * Constructor for FoodItem
	 * @param name Name of foodItem
	 * @param description Description of FoodItem
	 * @param price Price of FoodItem
	 * @param type FoodType of FoodItem
	 */
	public FoodItem(String name, String description, double price, FoodType type) {
		super(name, description, price);
		this.setType(type);
	}
	/**
	 * returns Food Type
	 * @return Food Type
	 */
	public FoodType getType() {
		return type;
	}
	/**
	 * Modifies FoodType of FoodItem Object
	 * @param type Type to be modify to
	 */
	public void setType(FoodType type) {
		this.type = type;
	}
	
	
}
