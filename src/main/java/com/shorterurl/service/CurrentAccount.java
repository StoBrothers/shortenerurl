package com.shorterurl.service;

import java.util.Set;

import org.springframework.security.core.authority.AuthorityUtils;

import com.shorterurl.domain.Account;
import com.shorterurl.security.Role;

/**
 *  Wrapper for authenticated User.
 *  
 *  @author Sergey Stotskiy
 */
@SuppressWarnings("serial")
public class CurrentAccount extends org.springframework.security.core.userdetails.User {

    private Account account;

    public CurrentAccount(Account account) {
        super(account.getLogonName(), account.getPasswordHash(), true, true, true, true,
            AuthorityUtils.createAuthorityList(
                account.getRoles().stream().flatMap(role -> role.getPermissions().stream())
                    .map(permission -> permission.toString()).toArray(String[]::new)));
        this.account = account;
    }

    public Account getAccount() {
        return account;
    }

    public Long getId() {
        return account.getId();
    }

    /**
     * Get logon name.
     * @return
     * @see Account#getLogonName()
     */
    public String getLogonName() {
        return account.getLogonName();
    }

    /**
     * Get roles.
     * @return
     */
    public Set<Role> getRoles() {
        return account.getRoles();
    }
}
