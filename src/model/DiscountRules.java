package model;

/**
 * Represents the rules by which a customer receives a discount.
 * @author Erik
 */
public class DiscountRules
{
    /**
     * Calculates the discounted price of the sale, using some rules
     * @param totalPrice The price to be discounted
     * @return Returns the discounted price.
     */
    public static double calcDiscountedPrice(double totalPrice)
    {
        return 0.8*totalPrice;
    }
}
