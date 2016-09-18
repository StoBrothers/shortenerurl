package com.shorterurl.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.shorterurl.domain.Account;
import com.shorterurl.domain.AccountParameters;

/**
 * Service for work with account entity.
 *
 * @author Sergey Stotskiy
 */
public interface AccountService {
  
    /**
     * Get account by Id
     * @param id
     * @return
     */
    Optional<Account> getAccountById(long id);
    /**
     * Update account  
     * @param account
     * @return
     */
    Optional<Account> updateAccount(Account account);
    /**
     * Get account by logon name 
     * @param logonName
     * @return
     */
    Optional<Account> getAccountByLogonName(String logonName);
    /**
     * Get all accounts 
     * @return
     */
    Collection<Account> getAllAccounts();
    Page<Account> getAllAccounts(Pageable pageable);

    Collection<Account> getAllAdminAccounts();
    /**
     * Open account  
     * @param account
     * @return
     */
    AccountParameters openAccount(AccountParameters account);

}
