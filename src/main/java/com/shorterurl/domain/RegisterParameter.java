package com.shorterurl.domain;

import java.io.Serializable;

@SuppressWarnings("serial")
public class RegisterParameter implements Serializable {

    private String url;

    private Integer redirectType;

    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    
    public Integer getRedirectType() {
        return redirectType;
    }
    
    public void setRedirectType(Integer redirectType) {
        this.redirectType = redirectType;
    }
}
