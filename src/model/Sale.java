package model;

import integration.AccountingSystem;
import integration.InventorySystem;
import integration.Printer;
import integration.SystemCreator;
import java.util.ArrayList;
import java.util.List;

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
    
    private List<SaleObserver> saleObservers = new ArrayList<>();

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
            itemFlag = true;
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
     * Applies a discount to the price of the sale. The amount that is discounted
     * is dependent on customerID
     * @param customerID The number that determines how much the price is discounted.
     */
    public void applyDiscount(int customerID){
        DiscountRules discountRules = new DiscountRules();
        
        double discountedPrice = discountRules.calculateDiscount(customerID, this.runningTotal);
        this.runningTotal = discountedPrice;
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
        
        notifyObservers();
        
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

    /**
     * 
     * @return The arrayList ItemQuantity, which contains the quantities for all the
     * items.
     */
    public ArrayList<Integer> getItemQuantityList() {
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
    
    /**
     * The method sets the parameter item on the specified index in the list.
     * @param i the index in the list where something is to be set.
     * @param item the item to be set in the list.
     */
    public void setItemList(int i, ItemDTO item){
        this.itemList.set(i, item);
    }
    
    /**
     * Adds an observer to the list saleObservers
     * @param saleObs The observer to be added to the list.
     */
    public void addSaleObserver(SaleObserver saleObs) {
        saleObservers.add(saleObs);
    }
    
    /**
     * All the specified observers will be notified when a sale has been paid for.
     * @param observers The observers to notify.
     */
    public void addSaleObservers(List<SaleObserver> observers) {
        saleObservers.addAll(observers);
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
    
    private void notifyObservers() {
        for(SaleObserver obs: saleObservers) {
            obs.newSale(this);
        }
    }
    
}