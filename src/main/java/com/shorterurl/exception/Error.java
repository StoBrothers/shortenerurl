package com.shorterurl.exception;

/**
 * Error with code of error and message and type.
 * 
 * @author Sergey Stotskiy
 * 
 */
public enum Error {
    DATE_PARSE(1, ErrorMessage.DATE_PARSE_MESSAGE, ErrorType.SERVER),
    DEFAULT_DB_SAVE(2, ErrorMessage.DEFAULT_DB_MESSAGE, ErrorType.DB);

    private final int code;
    private final ErrorMessage message;
    private final ErrorType errorType;

    Error(int code, ErrorMessage message, ErrorType errorType) {
        this.code = code;
        this.message = message;
        this.errorType = errorType;
    }

    public int getCode() {
        return code;
    }

    public ErrorMessage getMessage() {
        return message;
    }

    public ErrorType getErrorType() {
        return errorType;
    }
}
