package integration;

/**
 * Thrown when there is an error in the database.
 * @author Erik
 */
public class DatabaseErrorException extends RuntimeException{
    
    /**
     * Creates a new instance of the exception with a descriptive message.
     */
    public DatabaseErrorException(){
        super("There was a database error. This is totally not a message that should be sent to the view.");
    }
}
