package view;

import controller.AddItemFailedException;
import controller.Controller;
import integration.ItemIdentifierNotFoundException;
import integration.Printer;
import integration.SystemCreator;
import java.io.IOException;
import java.lang.Math;
import logHandler.LogHandler;
import model.ItemDTO;
import model.Receipt;
import model.Sale;

/**
 * The view is a representation of what the customer and cashier sees as well as
 * how the customer and cashier interacts with the system.
 * @author Erik
 */
public class View{
    private Controller controller;
    
        
    /**
     * Creates an instance of the view
     * @param controller A reference to an instance of the controller class. Is
     * used to connect the view to the rest of the system.
     */
    public View(Controller controller) throws IOException{
        this.controller = controller;
        controller.addSaleObserver(new TotalRevenueView());
    }
    /**
     * Represents the action of the cashier scanning an item, thereby adding it
     * to the sale.If the itemidentifier is incorrect it prints an error message.
     * @param itemId The identifier of the item, used to find it in the
     * database.
     * @param quantity The quantity of the item that is scanned.
     * @throws integration.ItemIdentifierNotFoundException If the item to be added cannot be
     * found in the inventory.
     * @throws AddItemFailedException If there is an error when trying to add an item.
     */
    public void addItem(int itemId, int quantity) throws 
        ItemIdentifierNotFoundException, AddItemFailedException, IOException{
    	try {
            ItemDTO currentItem = controller.addItem(itemId, quantity);
            printItemOnScreen(currentItem, quantity);
            
        } catch (ItemIdentifierNotFoundException exc) {
            System.out.println(exc.getMessage());
            LogHandler.getLogHandler().logException(exc);
        }
        catch (AddItemFailedException exc) {
           System.out.println(exc.getMessage());
           LogHandler.getLogHandler().logException(exc);
        }
    }
    
    /**
     * Represents the action of a customer requesting a discount.
     * Which discount the customer gets is determined by the customerID-
     * @param customerID The number which determines how much the price of the sale
     * is discounted.
     */
    public void signalDiscountRequest (int customerID) {
        controller.requestDiscount(customerID);
        System.out.println("Customer has requested a discount");
    }
    /**
     * The method represents the action of the cashier starting a new sale.
     * @param creator A reference to a systemcreator which gives access to the external
     * systems InventorySystem and AccountingSystem.
     */
    public void runSale(SystemCreator creator) {
    	controller.startNewSale(creator);
        System.out.println("New sale was started.");
    }
    
    /**
     * pay Represents the action of the customer paying for the sale. It also
     * calculates the amount of change that the customer is to receive and it
     * saves the information about the sale in the external systems AccountingSystem
     * and InventorySystem.
     * 
     * @param amount The amount of money that the customer pays.
     */
    public void pay(double amount) {
    	double amountPaid = amount;
    	double change = controller.pay(amount);
        if(change > 0) {
            printTotalPriceAndChange(amountPaid, change);
        } else {
            System.out.println("Insufficient ammount paid.");
        }
    }
    
    /**
     * This method represents the cashier scanning items, it also prints the
     * information about the scanned items aswell as the running total.
     */
    public void addItemsAndPrintToConsole() throws IOException{
        try {
            addItem(0, 2);
            addItem(0, 2);
            addItem(1, 3);
            addItem(2, 4);
            addItem(3, 4);
            addItem(0, 1);
            addItem(7, 5);
            
        } catch (ItemIdentifierNotFoundException e) {
            System.out.println(e.getMessage());
            LogHandler.getLogHandler().logException(e);
        }
        catch (AddItemFailedException exc) {
           System.out.println(exc.getMessage());
           LogHandler.getLogHandler().logException(exc);
        }
    }
    
    /**
     * requestReceipt prints out all the information on the receipt.
     */
    public void printReceipt() {
    	Receipt receipt = controller.requestReceipt();
        Printer printer = new Printer();
        printer.printReceipt(receipt);
        
    }
    
    private void printItemOnScreen(ItemDTO IDTO, int quantity) {
    	double runningTotal = controller.getRunningTotal();
    	System.out.println("Item: " + IDTO.getItemDescription() + " x" + quantity + " Price: " + IDTO.getPrice() * quantity + " Running Total: " + runningTotal);
    }
    
    
    private void printTotalPriceAndChange(double amountPaid, double change) {
        double totalPrice = controller.indicateAllItemsRegistered();
        System.out.println("Total cost (Including VAT): " + totalPrice);
    	System.out.println("Amount paid : " + amountPaid + " Change: " + Math.round(change * 100.0) / 100.0);
    }
   
}