package startup;

import controller.Controller;
import integration.Printer;
import integration.SystemCreator;
import model.CashRegister;
import model.Receipt;
import integration.InventorySystem;
import integration.AccountingSystem;
import java.io.IOException;
import view.View;


public class Main {
    
    public static void main(String[] args) {
	
        try {
            SystemCreator creator = new SystemCreator();
            InventorySystem InventorySystem = creator.getInventorySystem();
            AccountingSystem AccountingSystem = creator.getAccountingSystem();
            CashRegister CashRegister = new CashRegister();
            Controller contr = new Controller(InventorySystem, AccountingSystem, CashRegister);
            View view = new View(contr);
            Printer printer = new Printer();
            view.runSale(creator);
            view.addItemsAndPrintToConsole();
            view.pay(200);
            view.printReceipt();
            
            view.runSale(creator);
            view.addItemsAndPrintToConsole();
            view.pay(200);
            view.printReceipt();
     
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
}