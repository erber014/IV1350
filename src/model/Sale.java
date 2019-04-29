package model;

import integration.Printer;
import java.util.ArrayList;

/**
 * Represents a sale happening in a grocery store.
 * @author Erik
 */
public class Sale {
    private int vAT;
    private ArrayList<ItemDTO> itemList = new ArrayList<ItemDTO>();
    private double runningTotal = 0.0;
    private int itemsExisting = 0;
    private double change;
    private double payment;
    CashRegister cashRegister;
    boolean itemFlag = false;
    boolean discountFlag = false;

    /**
     * addItem adds an item to an itemlist. If the itemlist is empty it simply
     * adds the item. If the itemlist contains an item of the same kind (same identifier)
     * the method simply updates the quantity of that item.
     * @param item the item to be added to the list
     * @param quantity the quantity of the item
     */
    public void addItem(ItemDTO item, int quantity) {	
	if(itemList.isEmpty()) {
            itemList.add(item);
	}	
	for(int i = 0; i < itemsExisting + 1; i++) {
            if(item.getItemIdentifier() == itemList.get(i).getItemIdentifier() && itemFlag == false) {
		itemList.get(i).updateQuantity(quantity);
		itemFlag = true;
            } 
	}		
	if(!itemFlag){ 
            itemList.add(item);
            item.updateQuantity(quantity);
	}
        
	itemFlag = false;
	calculateRunningTotal(item, quantity);	
    }

    /**
     * Represents the action of the cashier turning on the cashregister.
     * Creates an instance of the CashRegister class.
     */
    public void turnOnCashRegister() {
	cashRegister = new CashRegister();
    }
    
    /**
     * pay represents the action of the customer paying for the sale.
     * @param payment The amount of money the customer pays
     * @param newTotalPrice the final price of the sale.
     * @return The amount of change the customer receives
     */
    public double pay(double payment, double newTotalPrice) {
	change = cashRegister.calculateChange(payment, newTotalPrice);
	this.payment = payment;
	cashRegister.addPayment(payment - change);
	return change;
    }
    
    /**
     * Updates the running total of the sale with the price of an item that is part of the sale,
     * as well as the quantity of that item.
     * @param foundItem The item who's will update the runningtotal
     * @param itemQuantity The quantity of the found item
     */
    private void calculateRunningTotal(ItemDTO foundItem, int itemQuantity) {
        this.runningTotal += foundItem.getPrice() * itemQuantity;
    }
	
    /**
     * 
     * @return The runningtotal of the sale. This value becomes the total price of the
     * sale after no more items are added to the sale. The running total is decreased
     * by an amount determined by the discount rules should the customer ask for a 
     * discount.
     */
    public double getRunningTotal() {
	if(!discountFlag) {
            return this.runningTotal;
            } 
        else {
            return runningTotal * 0.8;
        }
    }

    /**
     * 
     * @return Returns the list of items that is a part of the sale.
     */
    public ArrayList<ItemDTO> getItemList() {
	return itemList;
    }
	
    /**
     * What does this do?
     * @return 
     */
    public int getExistingItems() {
	return itemsExisting + 1;
    }
	
    /**
     * 
     * @return Returns the amount of change the customer receives. This value is rounded
     * so that the customer does not receive an amount of money that cannot be represented
     * by cash (Really small decimal values).
     */
    public double getChange() {
	return Math.round(change * 100.0) / 100.0;
    }
	
    /**
     * 
     * @return The amount of money the customer pays.
     */
    public double getAmountPaid() {
	return payment;
    }
	
    /**
     * 
     * @return The discounted price of the sale.
     */
    public double calcDiscountedPrice() {
	discountFlag = true;
	return this.runningTotal * 0.8;
    }
}