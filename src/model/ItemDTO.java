package model;

/**
 * Represents an item in a grocery store.
 * @author Erik
 */
public class ItemDTO {
	private double price;
	private int itemIdentifier;
	private String itemDescription;
	private double VAT;
	private int quantity;
        
        /**
         * Creates an instance of the ItemDTO class, that is, an item.
         * @param price The price of the item
         * @param itemIdentifier The unique number representing the item
         * @param itemDescription A description of the item
         * @param VAT The VAT rate of the item
         * @param quantity The quantity of the item
         */
	public ItemDTO (double price, int itemIdentifier, String itemDescription, double VAT, int quantity){
		this.price = price;
		this.itemIdentifier = itemIdentifier;
		this.itemDescription = itemDescription;
		this.VAT = VAT;
		this.quantity = quantity;
	}
	
        /**
         * @return Returns the price of the item as a double.
         */
	public double getPrice() {
		return this.price;
	}
	
        /**
         * @return Returns the itemIdentifier of an item
         */
	public int getItemIdentifier() {
		return this.itemIdentifier;
	}
	
        /**
         * 
         * @return Returns the itemDesciption of an item
         */
	public String getItemDescription() {
		return this.itemDescription;
	}
        
	/**
         * 
         * @return Returns the VAT rate of an item
         */
	public double getVAT() {
		return this.VAT;
	}
	
        /**
         * 
         * @return Returns the quantity of the item
         */
	public int getQuantity() {
		return this.quantity;
	}
	
        /**
         * Updates the quantity of an item.
         * @param quantity Updates the quantity of the item by this amount.
         */
	/*public void updateQuantity(int quantity) {
		this.quantity += quantity;
	}*/

}