package controller;

/**
 * Thrown when there is an error adding an item.
 * @author Erik
 */
public class AddItemFailedException extends Exception{
    
    /**
     * Creates a new instance with a descriptive message.
     */
    public AddItemFailedException(String message, Exception cause) {
        super(message, cause);
    }
}
