
package com.shorterurl.config.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import com.shorterurl.domain.Account;
import com.shorterurl.domain.AccountRepository;

/**
 * Initialization default Accounts in system.
 *
 * @author Sergey Stotskiy
 */
@Component
@DependsOn({ "applicationProperties" })
public class AccountInit extends AbstractInit {

    /**
     *  password:123
     */
    private static final String PASS_TEST = "$2a$10$lGQ0FEvIVA/6mJor8rYK.eOoHmvi9lp84lnbMV1098cgL4agQNM/i";

    @Autowired
    AccountRepository accountRepository;

    @Override
    protected void init() {
        String accountPass = PASS_TEST;

        create("admin", accountPass);
        // -------------------
        create("app1", accountPass);
        create("app2", accountPass);
    }

    /**
     * Create one Account
     *
     * @param logonName
     * @param passwordHash
     * 
     */
    private void create(String logonName, String passwordHash) {
        Account account = new Account();
        account.setLogonName(logonName);
        account.setPasswordHash(passwordHash);
        account.setAdminRules(true);
        accountRepository.save(account);
    }
}
