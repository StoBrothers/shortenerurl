package com.shorterurl.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Entity included full and generated short url link with redirect status.
 * 
 * @author Sergey Stotskiy
 *
 */

@SuppressWarnings("serial")
@Entity
public class RegisterUrl {

    public RegisterUrl() {
    };

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private long id;

    @Column(nullable = false)
    private String fullUrl;

    @Column(nullable = false)
    private String shortUrl;

    @Column(nullable = false)
    private Integer redirectType;

    public RegisterUrl(String fullUrl, String shortUrl, Integer redirectType) {
        this.fullUrl = fullUrl;
        this.shortUrl = shortUrl;
        this.redirectType = redirectType;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFullUrl() {
        return fullUrl;
    }

    public void setFullUrl(String fullUrl) {
        this.fullUrl = fullUrl;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public Integer getRedirectType() {
        return redirectType;
    }

    public void setRedirectType(Integer redirectType) {
        this.redirectType = redirectType;
    }
}
