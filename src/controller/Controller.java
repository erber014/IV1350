package controller;

import model.CashRegister;
import model.ItemDTO;
import model.Receipt;
import model.Sale;
import integration.InventorySystem;
import integration.AccountingSystem;
import model.SaleDTO;

public class Controller {
	private Sale sale;
	private InventorySystem inventorySystem;
	private AccountingSystem accountingSystem;
	private CashRegister cashRegister;
	private double totalPrice;

    public Controller(InventorySystem inventorySystem, AccountingSystem accountingSystem, CashRegister cashRegister) {
    	this.inventorySystem = inventorySystem;
    	this.accountingSystem = accountingSystem;
    	this.cashRegister = cashRegister;
	}

	public void startNewSale() {
        sale = new Sale();
        sale.turnOnCashRegister();
    }
    
    public ItemDTO addItem(int itemIdentifier, int itemQuantity) {
    	ItemDTO foundItem = inventorySystem.findItem(itemIdentifier);
    	if(foundItem != null) {
	    	sale.addItem(foundItem, itemQuantity);
	    	return foundItem;
    	} else {
    		return null;
    	}
    }
    
    public double pay (double amount) {
    	double change = sale.pay(amount, totalPrice);
    	return change;
    }
    
    public double indicateAllItemsRegistered() {
    	totalPrice = sale.getRunningTotal();
    	return sale.getRunningTotal();
    }
    
    public double signalDiscountRequest() {
    	totalPrice = sale.calcDiscountedPrice();
    	return totalPrice;
    }
    
    public Receipt requestReceipt() {
    	Receipt receipt = new Receipt(sale, sale.getExistingItems());
    	return receipt;
    }
} 