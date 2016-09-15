package com.shorterurl.domain;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.shorterurl.security.Role;

/**
 * User entity.
 *
 * @author Sergey Stotskiy
 *
 */
@SuppressWarnings("serial")
@Entity(name = "user_")
public class Account implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;

    @Column(nullable = false, unique = true)
    private String logonName;

    @Column(nullable = false)
    private String passwordHash;

    @Column(nullable = false)
    private Boolean adminRules;

    /**
     * Password
     */
    private String newPassword;

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the logonName
     */
    public String getLogonName() {
        return logonName;
    }

    /**
     * @param logonName
     *            the logonName to set
     */
    public void setLogonName(String logonName) {
        this.logonName = logonName;
    }

    /**
     * @return the passwordHash
     */
    public String getPasswordHash() {
        return passwordHash;
    }

    /**
     * @param passwordHash
     *            the passwordHash to set
     */
    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public Set<Role> getRoles() {
        if (getAdminRules()) {
            return Collections.singleton(Role.ADMIN);
        }
        Set<Role> roles = new HashSet<Role>();
        roles.add(Role.APP_HEAD);
        return roles;
    }

    /**
     * Get new password
     *
     * @return - password
     */
    public String getNewPassword() {
        return newPassword;
    }

    /**
     * Set password
     *
     * @param newPassword
     *            - password
     */
    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public Boolean getAdminRules() {
        return adminRules;
    }

    public void setAdminRules(Boolean adminRules) {
        this.adminRules = adminRules;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Account user = (Account) o;

        return !(id != null ? !id.equals(user.id) : user.id != null);

    }

    public String getFullName() {
        return getLogonName();
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

}