package com.shorterurl.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.shorterurl.domain.AccountParameters;
import com.shorterurl.service.AccountService;

/**
 * New account registration.
 *
 * @author Sergey Stotskiy
 *
 */
@RestController
public class AccountController {

    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/account/", method = RequestMethod.POST, consumes = {
        "application/json" }, produces = { "application/json" })
    @Transactional
    public Object openAccount(@RequestBody AccountParameters account) {
        return accountService.openAccount(account);
    }
}
