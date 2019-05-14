/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import integration.AccountingSystem;
import integration.InventorySystem;
import integration.ItemIdentifierNotFoundException;
import integration.SystemCreator;
import model.CashRegister;
import model.ItemDTO;
import model.Receipt;
import model.Sale;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * A testclass for testing methods in the Controller class in package controller.
 * @author Erik
 */
public class ControllerTest {
    
    public ControllerTest() {
    }
    SystemCreator creator = new SystemCreator();
    InventorySystem inventorySystem = creator.getInventorySystem();
    AccountingSystem accountingSystem = creator.getAccountingSystem();
    CashRegister cashRegister = new CashRegister();
    Controller controller = new Controller(inventorySystem, accountingSystem, cashRegister);
    
    
    @Test
    public void addExistingItem() throws ItemIdentifierNotFoundException, AddItemFailedException{
       controller.startNewSale(creator);
       ItemDTO item = controller.addItem(0, 2);
       assertEquals(0, item.getItemIdentifier());
    }

    @Test
    public void addNonexistingItem() throws ItemIdentifierNotFoundException, AddItemFailedException{
        controller.startNewSale(creator);
        try {
            ItemDTO item = controller.addItem(5, 1);
            fail("Could find item that doesnt exist with using an incorrect itemidentifier.");
        } catch (ItemIdentifierNotFoundException exc) {
            assertTrue("Wrong exception messsage", 
                    exc.getMessage().contains("Unable to find item with the corresponding itemidentifer: " + 5));
        }
        catch (AddItemFailedException exc){
            fail("There was an exception of this kind");
            exc.printStackTrace();
        }
       
    }
    
    @Test(expected = AddItemFailedException.class)
    public void addItemDatabaseFailure() throws AddItemFailedException, ItemIdentifierNotFoundException{
        controller.startNewSale(creator);
        ItemDTO item = controller.addItem(7,1);
    }
            
            
    @Test
    public void enoughPaidMoney() {
        controller.startNewSale(creator);
        double change = controller.pay(1.0);
        assertEquals(1.0, change, 0.0);
    }

    @Test
    public void notEnoughMoneyPaid () {
        controller.startNewSale(creator);
        double change = controller.pay(-10.0);
        assertEquals(-1.0, change, 0.0);
    }

    @Test
    public void indicateAllItemsRegistered() throws ItemIdentifierNotFoundException, AddItemFailedException{
        
        controller.startNewSale(creator);
        controller.addItem(0, 2);
        controller.pay(40);
        double totalPrice = controller.indicateAllItemsRegistered();
        assertEquals(25, totalPrice, 0.0);
    }

    @Test
    public void requestReceipt() {
        controller.startNewSale(creator);
        Receipt receipt = controller.requestReceipt();
        assertNotNull(receipt);
    }
}
