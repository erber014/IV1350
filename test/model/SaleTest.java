/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
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
public class SaleTest {
    
    public SaleTest() {
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
     * Test of addItem method, of class Sale.
     */
    /*@Test
    public void testAddItemNoItemScanned() {
        
        int quantity = 1;
        ItemDTO item = new ItemDTO(0.0, 1, "aa", 0.0, 1);
        
        Sale instance = new Sale();
        //instance.setItemList(0, item);
        instance.addItem(item, quantity);
        ArrayList<Integer> itemQuantity = instance.getItemQuantity(); 
        int quantityInList = itemQuantity.get(0);
        assertEquals(quantity, quantityInList);
    }
    */
    /**
     * Test of turnOnCashRegister method, of class Sale.
     */
    /*@Test
    public void testTurnOnCashRegister() {
        System.out.println("turnOnCashRegister");
        Sale instance = new Sale();
        instance.turnOnCashRegister();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }*/

    /**
     * Test of pay method, of class Sale.
     */
    /*@Test
    public void testPay() {
        System.out.println("pay");
        double payment = 0.0;
        Sale instance = new Sale();
        double expResult = 0.0;
        double result = instance.pay(payment);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    */
}
