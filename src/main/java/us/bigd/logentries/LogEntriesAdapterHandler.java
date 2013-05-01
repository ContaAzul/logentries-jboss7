package us.bigd.logentries;

import java.util.logging.Handler;
import java.util.logging.LogRecord;

import com.logentries.net.AsyncLogger;

/**
 * <p>Adapter from {@link Handler} to {@link AsyncLogger} for use in JBoss 7.1.X.</p>
 */
public class LogEntriesAdapterHandler extends Handler {

    private AsyncLogger asyncLogger;

    /**
     * Create a new adapter
     */
    public LogEntriesAdapterHandler() {
        super();
        asyncLogger = new AsyncLogger();
    }

    /**
     * Publish the record and delegate logging to the LogEntries AsyncLogger
     * @param record
     */
    @Override
    public void publish(final LogRecord record) {
        if(isLoggable(record)) {
            process(record);
        }
    }

    void process(final LogRecord record){
      asyncLogger.addLineToQueue(getFormatter().format(record));
    }

    /**
     * Flush is a no-op in this adapter
     */
    @Override
    public void flush() {
        // no op
    }

    /**
     * Close the logger.
     * @throws SecurityException
     */
    @Override
    public void close() throws SecurityException {
        asyncLogger.close();
    }

    /** LogEntries token value associated with the destination log (required)**/
    public void setToken(final String token) {
        asyncLogger.setToken(token);
    }

    /** LogEntries account key (optional) */
    public void setKey(final String key) {
        asyncLogger.setKey(key);
    }

    /** LogEntries location string (optional) */
    public void setLocation(final String location) {
        asyncLogger.setLocation(location);
    }

    /** LogEntries: Should we use HTTP PUT? */
    public void setHttpPut(final boolean httpPut) {
        asyncLogger.setHttpPut(httpPut);
    }

    /** LogEntries: SSL/TLS flag. */
    public void setSsl(final boolean ssl) {
        asyncLogger.setSsl(ssl);
    }
}