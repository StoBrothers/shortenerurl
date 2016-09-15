package com.shorterurl.exception;

/**
 * 
 * Error messages. 
 *  
 * @author Sergey Stotskiy
 * 
 */
public enum ErrorMessage {
    // @formatter:off
    DATE_PARSE_MESSAGE("String with Date parsed with errors. Input string '{}'. Input format date '{}'."),
    DEFAULT_DB_MESSAGE("Error created after call DB. ");
    // @formatter:on

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
