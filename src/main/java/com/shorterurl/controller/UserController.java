package com.shorterurl.controller;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.shorterurl.service.AccountService;

/**
 * User controller.
 *
 * @author Sergey Stotskiy
 */
@Controller
public class UserController {

    private final AccountService accountService;

    @Autowired
    public UserController(AccountService accountService) {
        this.accountService = accountService;
    }

    @RequestMapping("/user/{id}")
    public ModelAndView getUserPage(@PathVariable Long id) {
        return new ModelAndView("user", "user",
            accountService.getAccountById(id)
                .orElseThrow(() -> new NoSuchElementException(
                    String.format("User=%s not found", id))));
    }
}
