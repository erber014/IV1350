/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Erik
 */
public class BigDiscount implements DiscountStrategy{
    private final double discount = 0.5;
    
    /**
     * Returns the discounted price.
     * @param runningTotal The amount of money to apply the discount to
     * @return the discounted price
     */
    @Override
    public double getDiscountedPrice(double runningTotal) {
        return runningTotal*discount;
    }
}
