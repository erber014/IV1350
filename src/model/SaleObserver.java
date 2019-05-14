package model;

/**
 * A listener interface for receiving notifications about the amount paid in the sale.
 * The class that is interested in this information implements this interface.
 * @author Erik
 */
public interface SaleObserver {
    /**
     * Invoked when a sale has been paid for.
     * @param sale The sale that was paid for.
     */
    void newSale (Sale sale);
    
}
