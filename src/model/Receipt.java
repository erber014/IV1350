package model;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Represents the receipt that a customer receives as proof of a completed sale
 * @author Erik
 */
public class Receipt {
	
    private double amountPaid;
    private double change;	
    private ArrayList<ItemDTO> itemList;
    private double total;
    private LocalDateTime dateTime;
    private DateTimeFormatter time;
    private String formattedDate, storeName, adress;
    //private int items;
	
    /**
     * Creates a receipt, containing the necessary information about the sale
     * @param sale The sale about which, all necessary information is received
     * @param items 
     */
    public Receipt(Sale sale) {
	storeName = "Storey McStoreFace";
	adress = "Rodeo Drive 1";
	amountPaid = sale.getAmountPaid();
	change = sale.getChange();
	itemList = sale.getItemList();
	total = sale.getTotalPrice();
	dateTime = LocalDateTime.now();
	time = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
	formattedDate = dateTime.format(time);
	//this.items = items;
    }

    /**
     * 
     * @return The amount the customer has paid for the sale
     */
    public double getAmountPaid() {
        return amountPaid;
    }
    
    /**
     * @return The amount of change the customer receives
     */
    public double getChange() {
        return change;
    }

    /**
     * 
     * @return A reference to the list of items, containing information about
     * the items
     */
    public ArrayList<ItemDTO> getItemList() {
        return itemList;
    }
    
    /**
     * 
     * @return The total cost of the sale
     */
    public double getTotal() {
	return total;
    }
    
    /**
     * 
     * @return The time that the sale was completed
     */
    public String getTime() {
	return formattedDate;
    }
    
    /**
     * 
     * @return The name of the store
     */
    public String getName() {
	return storeName;
    }
    
    /**
     * 
     * @return The adress of the store.
     */
    public String getAdress() {
	return adress;
    }

}