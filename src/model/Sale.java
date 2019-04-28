package model;

import integration.Printer;
import java.util.ArrayList;

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
	
	public void turnOnCashRegister() {
		cashRegister = new CashRegister();
	}
	
	public double pay(double payment, double newTotalPrice) {
		change = cashRegister.calculateChange(payment, newTotalPrice);
		this.payment = payment;
		cashRegister.addPayment(payment - change);
		return change;
	}
	
	private void calculateRunningTotal(ItemDTO foundItem, int itemQuantity) {
		this.runningTotal += foundItem.getPrice() * itemQuantity;
	}
	
	public double getRunningTotal() {
		if(!discountFlag) {
			return this.runningTotal;
		} else {
			return runningTotal * 0.8;
		}
	}
	
	public ArrayList<ItemDTO> getItemList() {
		return itemList;
	}
	
	public int getExistingItems() {
		return itemsExisting + 1;
	}
	
	public double getChange() {
		return Math.round(change * 100.0) / 100.0;
	}
	
	public double getAmountPaid() {
		return payment;
	}
	
	public double calcDiscountedPrice() {
		discountFlag = true;
		return this.runningTotal * 0.8;
	}
}