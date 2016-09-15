package com.shorterurl.exception;

import com.shorterurl.util.StringFormatter;

/**
 * Custom exception, wrapper for Runtime exception.
 *
 * @author Sergey Stotskiy
 */
@SuppressWarnings("serial")
public class ApplicationException extends RuntimeException {

    private Error error;

    public ApplicationException(Error error, Object... args) {
        super(buildMessage(error, args));
        this.error = error;
    }

    public ApplicationException(Error error, Throwable cause, Object... args) {
        super(buildMessage(error, args), cause);
        this.error = error;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }

    private static String buildMessage(Error error, Object... args) {
        String result = error.getMessage().getMessage();
        result = StringFormatter.format(result, args);
        return result;
    }
}
