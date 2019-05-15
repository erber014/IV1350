package logHandler;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * This class is responsible for the log.
 * @author Erik
 */
public class LogHandler {
    private static final String LOG_FILE_NAME = "sale-error-log.txt";
    
    private static LogHandler single_instance = null;
    
    private PrintWriter logFile;
    
    /**
     * Creates a new instance of loghandler.
     * @throws IOException If there is an IO error.
     */
    private LogHandler() throws IOException {
        logFile = new PrintWriter(
                    new FileWriter(LOG_FILE_NAME), true);
    }
    /**
     * Writes an entry for the exception log describing the exception.
     * @param exception The exception that is to be logged.
     */
    public void logException(Exception exception){
        StringBuilder logMsgBuilder = new StringBuilder();
        logMsgBuilder.append(getPresentTime());
        logMsgBuilder.append(",An exception was thrown: ");
        logMsgBuilder.append(exception.getMessage());
        logFile.println(logMsgBuilder);
        exception.printStackTrace(logFile);

    }
    
    private String getPresentTime (){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
        return now.format(dateTimeFormatter);
    }
    
    /**
     * Method to get the only instance of the Singleton class LogHandler.
     * @return 
     */
    public static LogHandler getLogHandler() throws IOException{
        if (single_instance == null) {
            single_instance = new LogHandler();
        }
        return single_instance;
    }
}
