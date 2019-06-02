/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 * One strategy for determining a discount.
 * @author Erik
 */
public class SmallDiscount implements DiscountStrategy{
    private final double discount = 0.9;
    
    /**
     * Returns the discounted price.
     * @param runningTotal the price that the discount is applied to.
     * @return the discounted price.
     */
    @Override
    public double getDiscountedPrice(double runningTotal) {
        return runningTotal*discount;
    }
    
}
