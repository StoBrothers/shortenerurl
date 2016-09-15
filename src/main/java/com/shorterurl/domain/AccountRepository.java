package com.shorterurl.domain;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.shorterurl.security.Role;

/**
 * User repository.
 *
 * @author Sergey Stotskiy
 */
public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findOneByLogonName(String logonName);

    Optional<Account> findOneById(Long id);

    Collection<Account> findAllByRoles(Role role, Sort sort);

    Collection<Account> findAllByAdminRulesIsTrue();

}