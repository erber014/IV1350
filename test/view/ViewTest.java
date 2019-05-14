/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.AddItemFailedException;
import controller.Controller;
import integration.AccountingSystem;
import integration.InventorySystem;
import integration.ItemIdentifierNotFoundException;
import integration.SystemCreator;
import logHandler.LogHandler;
import model.CashRegister;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Erik
 */
public class ViewTest {
    SystemCreator creator = new SystemCreator();
    InventorySystem inventorySystem = creator.getInventorySystem();
    AccountingSystem accountingSystem = creator.getAccountingSystem();
    CashRegister cashRegister = new CashRegister();
    Controller contr = new Controller(inventorySystem, accountingSystem, cashRegister);
    
    
    public ViewTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of addItem method, of class View.
     */
    @Test
    public void testAddItem() throws AddItemFailedException, ItemIdentifierNotFoundException {
        try {
            contr.addItem(4, 0);
            fail("Could find item that doesnt exist with using an incorrect itemidentifier.");
        } catch (ItemIdentifierNotFoundException exc) {
            assertTrue("Wrong exception messsage", 
                    exc.getMessage().contains("Unable to find item with the corresponding itemidentifer: " + 4));
        }
        catch (AddItemFailedException exc){
            fail("There was an exception of this kind");
            exc.printStackTrace();
        }
        
    }
    @Test
    public void testAddItemDatabaseError() throws AddItemFailedException,ItemIdentifierNotFoundException {
        try {
            contr.addItem(7, 0);
        } catch (AddItemFailedException exc) {
            assertTrue("Wrong exception message", exc.getMessage().contains("Could not add the item.(Database error)"));
        }
        catch (ItemIdentifierNotFoundException exc){
            fail("This exception shouldnt be thrown here");
            exc.printStackTrace();
        }
    }
   
}
