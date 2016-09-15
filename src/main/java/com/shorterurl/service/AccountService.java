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

    Optional<Account> getAccountById(long id);

    Optional<Account> updateAccount(Account account);

    Optional<Account> getAccountByLogonName(String logonName);

    Collection<Account> getAllAccounts();

    Page<Account> getAllAccounts(Pageable pageable);

    Collection<Account> getAllAdminAccounts();

    AccountParameters openAccount(AccountParameters account);

}
