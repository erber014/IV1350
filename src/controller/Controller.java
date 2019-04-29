package controller;

import model.CashRegister;
import model.ItemDTO;
import model.Receipt;
import model.Sale;
import integration.InventorySystem;
import integration.AccountingSystem;
import model.SaleDTO;

/**
 * Controller is the class that ties together what happens in the view with what
 * the program needs to do
 * @author Erik
 */
public class Controller {
    private Sale sale;
    private InventorySystem inventorySystem;
    private AccountingSystem accountingSystem;
    private CashRegister cashRegister;
    private double totalPrice;
    
    /**
    * Creates a new instance of controller with references to inventorysystem
    * accountingsystem and cashRegister
    * @param inventorySystem 
    * @param accountingSystem
    * @param cashRegister 
    */
    public Controller(InventorySystem inventorySystem, AccountingSystem accountingSystem, CashRegister cashRegister) {
    	this.inventorySystem = inventorySystem;
    	this.accountingSystem = accountingSystem;
    	this.cashRegister = cashRegister;
    }

    public void startNewSale() {
        sale = new Sale();
        sale.turnOnCashRegister();
    }
    
    /**
     * addItem adds an item to the current sale aswell as the quantity of that item
     * @param itemIdentifier a unique code that represents a unique item in the inventorysystem
     * @param itemQuantity represents the quantity of the item to be added
     * @return the method returns an item found in the inventorysystem.
     */
    public ItemDTO addItem(int itemIdentifier, int itemQuantity) {
    	ItemDTO foundItem = inventorySystem.findItem(itemIdentifier);
    	if(foundItem != null) {
	    	sale.addItem(foundItem, itemQuantity);
	    	return foundItem;
    	} else {
    		return null;
    	}
    }
    
    /**
     * pay calls upon the method pay in the class sale which represents
     * the act of the customer paying for a sale.
     * @param amount represents the amount the customer pays
     * @return returns the amount of change that the customer is to receive.
     */
    public double pay (double amount) {
    	double change = sale.pay(amount);
    	return change;
    }
    
    /**
     * Represents the action of the cashier indicating that all items have been registered
     * @return Returns the price of the sale so the cashier and customer can see it.
     */
    public double indicateAllItemsRegistered() {
    	totalPrice = sale.getRunningTotal();
    	return sale.getRunningTotal();
    }
    /**
     * Represents the action of a customer asking for a discount, which leads to
     * a discount being applied to the current sale.
     * @return Returns the new total price, after the discount has been calculated
     */
    //public double signalDiscountRequest() {
    //	totalPrice = sale.calcDiscountedPrice();
    //	return totalPrice;
    //}
    
    /**
     * Creates an instance of the receipt class, which contains all the needed
     * information about the sale.
     * @return Returns an instance of the receipt class.
     */
    public Receipt requestReceipt() {
    	Receipt receipt = new Receipt(sale);
    	return receipt;
    }
} 