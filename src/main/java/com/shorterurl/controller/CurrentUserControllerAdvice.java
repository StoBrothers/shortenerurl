package com.shorterurl.controller;


import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.shorterurl.service.CurrentAccount;

/**
 * Store current user for show at page.
 *
 * @author Sergey Stotskiy
 */
@ControllerAdvice
public class CurrentUserControllerAdvice {

    @ModelAttribute("currentUser")
    public CurrentAccount getCurrentUser(Authentication authentication) {
        return (authentication == null) ? null
            : (CurrentAccount) authentication.getPrincipal();
    }

}