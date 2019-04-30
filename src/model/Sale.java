package model;

import integration.AccountingSystem;
import integration.InventorySystem;
import integration.Printer;
import integration.SystemCreator;
import java.util.ArrayList;

/**
 * Represents a sale happening in a grocery store.
 * @author Erik
 */
public class Sale {
    private SystemCreator creator;
    private double VAT = 0.25;
    private ArrayList<ItemDTO> itemList = new ArrayList<ItemDTO>();
    private ArrayList<Integer> itemQuantity = new ArrayList<Integer>();
    private double runningTotal = 0.0;
    private double change;
    private double payment;
    private double totalPriceIncludingVAT;
    CashRegister cashRegister;
    boolean itemFlag = false;
    boolean discountFlag = false;

    public Sale (SystemCreator creator){
        this.creator = creator;
    }
    
    
    /**
     * addItem adds an item to an itemlist. If the itemlist is empty it simply
     * adds the item. If the itemlist contains an item of the same kind (same identifier)
     * the method simply updates the quantity of that item. If none of the above,
     * the method simply adds an item to the itemlist.
     * @param item the item to be added to the list
     * @param quantity the quantity of the item
     */
    public void addItem(ItemDTO item, int quantity) {	
	if(itemList.isEmpty()) {
            itemList.add(item);
            itemQuantity.add(quantity);
	}	
	for(int i = 0; i < itemList.size(); i++) {
            if(itemAlreadyScanned(item, i)) {
		int quantTemp = itemQuantity.get(i);
                itemQuantity.set(i,quantTemp + quantity);
		itemFlag = true;
            } 
	}		
	if(!itemFlag){ 
            itemList.add(item);
            itemQuantity.add(quantity);
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
     * pay represents the action of the customer paying for the sale. The pay method
     * sends the information about the sale, to the external systems InventorySystem
     * and AccountingSystem, so that the information may be saved.
     * @param payment The amount of money the customer pays
     * @param newTotalPrice the final price of the sale.
     * @return The amount of change the customer receives
     */
    public double pay(double payment) {
        
        double totalPrice = totalPriceIncludingVAT();
	change = cashRegister.calculateChange(payment, totalPrice);
	this.payment = payment;
	cashRegister.addPayment(payment - change);
	
        InventorySystem inventorySystem = creator.getInventorySystem();
        AccountingSystem accountingSystem = creator.getAccountingSystem();
        inventorySystem.saveSaleInformation(this);
        accountingSystem.saveSaleInformation(this);
        
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
     * sale after no more items are added to the sale.
     */
    public double getRunningTotal() {
	return this.runningTotal;
    }

    /**
     * 
     * @return Returns the list of items that is a part of the sale.
     */
    public ArrayList<ItemDTO> getItemList() {
	return this.itemList;
    }

    public ArrayList<Integer> getItemQuantity() {
        return this.itemQuantity;
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
     * @return The totalPrice of the sale, including the VAT (tax).
     */
    public double getTotalPrice(){
        return this.totalPriceIncludingVAT;
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
     * @param amount sets the itemquantity 
     */
    public void setItemQuantity(int i, int amount){
        this.itemQuantity.set(i, amount);
    }
    
    public void setItemList(int i, ItemDTO item){
        this.itemList.set(i, item);
    }
    
    private double totalPriceIncludingVAT (){
        this.totalPriceIncludingVAT = this.runningTotal + this.runningTotal*this.VAT;
        return totalPriceIncludingVAT;
    }
    
    private boolean itemAlreadyScanned(ItemDTO item, int i) {
        if (item.getItemIdentifier() == itemList.get(i).getItemIdentifier() && itemFlag == false){
            return true;
        }   
        else
            return false;
    }
}