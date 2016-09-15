package com.shorterurl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.shorterurl.domain.Account;

/**
 *  Authentication account.
 * 
 *  @author Sergey Stotskiy
 */
@Service
public class CurrentAccountDetailsService implements UserDetailsService {

    private final AccountService accountService;

    @Autowired
    public CurrentAccountDetailsService(AccountService userService) {
        this.accountService = userService;
    }

    @Override
    public CurrentAccount loadUserByUsername(String logonName)
        throws UsernameNotFoundException {
        Account account = accountService.getAccountByLogonName(logonName)
            .orElseThrow(() -> new UsernameNotFoundException(
                String.format("User with login=%s not found", logonName)));
        return new CurrentAccount(account);
    }
}
