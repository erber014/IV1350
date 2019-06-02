package model;

/**
 *
 * @author Erik
 */
public class DiscountRules {
    private DiscountStrategy discountStrat;
    
    /**
     * Chooses a discount based on customerID and returns the discounted
     * price.
     * @param customerID The number used to determine which discount is applied
     * @param runningTotal The price to be lowered by the discount.
     * @return the discounted price
     */
    public double calculateDiscount(int customerID, double runningTotal){
        if (customerID > 5){
            discountStrat = new BigDiscount();
        }
        else {
            discountStrat = new SmallDiscount();
        }
        return discountStrat.getDiscountedPrice(runningTotal);
    }
    
    
}
