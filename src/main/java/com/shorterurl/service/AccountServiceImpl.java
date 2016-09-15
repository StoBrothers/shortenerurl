package com.shorterurl.service;

import java.util.Collection;
import java.util.Optional;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.shorterurl.domain.Account;
import com.shorterurl.domain.AccountParameters;
import com.shorterurl.domain.AccountRepository;

/**
 * Service for work with user entity.
 *
 * @author Sergey Stotskiy
 */
@Service("userService")
public class AccountServiceImpl implements AccountService {

    private static final Logger logger = LoggerFactory
        .getLogger(AccountServiceImpl.class);

    private static final String ACCOUNT_EXISTED = "Account with that ID: #  already exists";

    private static final String ACCOUNT_OPENED = "Your account is opened";

    private static final String PATTERN = "#";

    private final AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Optional<Account> getAccountById(long id) {
        return Optional.ofNullable(accountRepository.findOne(id));
    }

    @Override
    public Optional<Account> updateAccount(Account user) {
        return Optional.ofNullable(accountRepository.save(user));
    }

    @Override
    public Optional<Account> getAccountByLogonName(String logonName) {
        return accountRepository.findOneByLogonName(logonName);
    }

    @Override
    public Collection<Account> getAllAccounts() {
        return accountRepository.findAll(new Sort("logonName"));
    }

    @Override
    public Page<Account> getAllAccounts(Pageable pageable) {
        return accountRepository.findAll(pageable);
    }

    @Override
    public Collection<Account> getAllAdminAccounts() {
        return accountRepository.findAllByAdminRulesIsTrue();
    }

    @Override
    public AccountParameters openAccount(AccountParameters account) {

        if (account.getAccountId() == null) {// check AccountId
            logger.error("AccountId is null");
            return account;
        }

        Optional<Account> accountExisted = accountRepository
            .findOneByLogonName(account.getAccountId());
        if (accountExisted.isPresent()) {
            account.setSuccess(String.valueOf(false));
            account.setDescription(
                StringUtils.replace(ACCOUNT_EXISTED, PATTERN, account.getAccountId()));
            account.setAccountId(null);
            return account;
        }

        Account user = new Account();
        user.setLogonName(account.getAccountId());
        String generatedPassword = RandomStringUtils.randomAlphanumeric(8);
        user.setPasswordHash(getHashPassword(generatedPassword));
        accountRepository.save(user);
        account.setSuccess(String.valueOf(true));
        account.setDescription(ACCOUNT_OPENED);
        account.setGeneratedPassword(generatedPassword);
        account.setAccountId(null);
        return account;
    }

    /**
     * Get coded password.
     *
     * @param password
     *            - pure password
     * @return - encoded password (hashPassword)
     */
    private String getHashPassword(String password) {
        String hashPassword = new BCryptPasswordEncoder().encode(password);
        return hashPassword;
    }

}