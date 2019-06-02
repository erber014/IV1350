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
public interface DiscountStrategy {
    /**
     * @param runningTotal The amount of money that the discount get applied to.
     * @return the discounted price
     */
    public double getDiscountedPrice(double runningTotal);
}
