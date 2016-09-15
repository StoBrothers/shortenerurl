package com.shorterurl.util;

import org.slf4j.helpers.MessageFormatter;

/**
 * Custom string formatter.
 * This class use in ApplicationException.
 *   
 * @author Sergey Stotskiy
 */
public class StringFormatter {

    public static String format(String messagePattern, Object... argArray) {
        if (argArray == null || argArray.length == 0) {
            return messagePattern;
        } else {
            return MessageFormatter.arrayFormat(messagePattern, argArray).getMessage();
        }
    }

}
