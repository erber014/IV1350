package integration;

import java.util.ArrayList;

import model.ItemDTO;
import model.Receipt;

/**
 * Represents a printer in a grocery store, that prints receipts.
 * @author Erik
 */
public class Printer {
	
        /**
         * Prints all necessary information about a sale onto a receipt
         * @param receipt Contains the information to be printed
         */
	public void printReceipt(Receipt receipt) {
		System.out.println("");
		System.out.println("Receipt:");
		System.out.println(receipt.getName());
		System.out.println(receipt.getAdress());
		System.out.println(receipt.getTime());
		ArrayList<ItemDTO> itemList = receipt.getItemList();
		for(int i = 0; i < receipt.getItemList().size(); i++) {
                    System.out.print(itemList.get(i).getItemDescription());
                    System.out.print(" x" + itemList.get(i).getQuantity());
                    System.out.print(" Price: " + itemList.get(i).getPrice() * itemList.get(i).getQuantity());
                    System.out.println("");
		}
		System.out.println("Total ,including 25%VAT: " + receipt.getTotal());
		System.out.println("Amount paid: " + receipt.getAmountPaid());
		System.out.print("Change: " + receipt.getChange());
	}
}