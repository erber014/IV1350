package integration;

/**
 * Thrown when the search for an item in the inventorysystem fails.
 * @author Erik
 */
public class ItemIdentifierNotFoundException extends Exception {
    private int ItemIdentifierThatIsNotFound;
    
    /**
     * Creates a new instance of the exception with a descriptive message
     * @param itemIdentifier The itemidentifier that could not be found.
     */
    public  ItemIdentifierNotFoundException (String msg, int itemIdentifier){
        super(msg + itemIdentifier);
        this.ItemIdentifierThatIsNotFound = itemIdentifier;
    }
    
    /**
     * @return The itemidentifier that could not be found.
     */
    public int getItemIdentifierThatIsNotFound(){
        return ItemIdentifierThatIsNotFound;
    }
}
