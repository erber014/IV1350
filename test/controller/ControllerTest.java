/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import integration.AccountingSystem;
import integration.InventorySystem;
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
 *
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
    
    @Test
    public void addExistingItem() {
       controller.startNewSale(creator);
       ItemDTO item = controller.addItem(0, 2);
       assertEquals(0, item.getItemIdentifier());
    }

    @Test
    public void addNonexistingItem() {
       controller.startNewSale(creator);
       ItemDTO item = controller.addItem(5, 1);
       assertEquals(null, item);
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
    public void indicateAllItemsRegistered() {
        
        controller.startNewSale(creator);
        controller.addItem(0, 2);
        controller.pay(40);
        double total = controller.indicateAllItemsRegistered();
        assertEquals(25, total, 0.0);
    }

    @Test
    public void requestReceipt() {
        controller.startNewSale(creator);
        Receipt receipt = controller.requestReceipt();
        assertNotNull(receipt);
    }
}
