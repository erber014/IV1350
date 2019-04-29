/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import static junit.framework.Assert.assertEquals;
import org.junit.Test;
import org.junit.*;
/*import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
*/
/**
 * Test for the methods in the CashRegister class.
 * @author Erik
 */
public class CashRegisterTest {
    
    public CashRegisterTest() {
    }
    

    /**
     * Test of addPayment method, of class CashRegister.
     */
    @Test
    public void testAddPayment() {
        double payment = 30.5;
        CashRegister instance = new CashRegister();
        instance.addPayment(payment);
        assertEquals(30.5, instance.getBalance());
    }
    
    @Test
    public void testAddPaymentWithNegativeDouble() {
        double payment = -10.5;
        CashRegister instance = new CashRegister();
        instance.addPayment(payment);
        assertEquals(-10.5, instance.getBalance());
    }
    
    /**
     * Test of calculateChange method, of class CashRegister.
     */
    @Test
    public void CalculateChangeTest() {
        double payment = 35.0;
        double cost = 25.0;
        CashRegister instance = new CashRegister();
        double expResult = 10.0;
        double result = instance.calculateChange(payment, cost);
        assertEquals(expResult, result);
    }
    
    /**
     * 
     */
    @Test
    public void testCalculateChangeNegativeDouble(){
        double payment = 0.0;
        double cost = 25.0;
        CashRegister instance = new CashRegister();
        double expResult = -25.0;
        double result = instance.calculateChange(payment, cost);
        assertEquals(expResult, result);
    }
    
}
