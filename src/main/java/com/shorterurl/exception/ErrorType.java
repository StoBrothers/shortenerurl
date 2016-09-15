package com.shorterurl.exception;

/**
 * Enum of type error.
 *
 * @author Sergey Stotskiy
 *
 */
public enum ErrorType {
    UI("Client error"), SERVER("Serever error"), DB("DB error");

    private final String text;

    ErrorType(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
