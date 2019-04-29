package model;
/**
 * Represents the cashregister of a grocery store. Contains a balance, which is
 * an amount of money.
 * @author Erik
 */
public class CashRegister
{
    private double balance = 0.0;
    
    /**
     * addPayment represents the action of taking the payment given by a customer
     * and putting it in the register.
     * @param payment Amount of money to be put in register
     */
    public void addPayment(double payment)
    {
        this.balance += payment;
    }
    
    /**
     * Calculates the change to be given to the customer, based on the payment
     * of the customer and the cost of the sale.
     * @param payment An amount of money
     * @param cost The cost of the sale
     * @return Returns the amount of change
     */
    public double calculateChange(double payment, double cost)
    {
        double change = payment - cost;
        return change;
    }
    
    /**
     * 
     * @return Returns the balance, that is, the amount of money in the register. 
     */
    public double getBalance (){
        return this.balance;
    }
}