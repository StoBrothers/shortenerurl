package com.shorterurl.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@SuppressWarnings("serial")
@Entity
public class Statistic {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Account account;

    @ManyToOne(fetch = FetchType.EAGER)
    private RegisterUrl registerUrl;

    @Column(nullable = false)
    private Integer countRedirects;

    public Statistic() {
        this.countRedirects = 0;
    };

    public Statistic(Account account, RegisterUrl registerUrl) {
        this.account = account;
        this.registerUrl = registerUrl;
        this.countRedirects = 0;
    };

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public RegisterUrl getRegisterUrl() {
        return registerUrl;
    }

    public void setRegisterUrl(RegisterUrl registerUrl) {
        this.registerUrl = registerUrl;
    }

    public Integer getCountRedirects() {
        return countRedirects;
    }

    public void setCountRedirects(Integer countRedirects) {
        this.countRedirects = countRedirects;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int incCountRedirects() {
        return ++countRedirects;
    }

    @Override
    public String toString() {
        return registerUrl.getFullUrl() + ":" + countRedirects;
    }
}
