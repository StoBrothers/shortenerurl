package com.shorterurl.domain;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@SuppressWarnings("serial")
public class AccountParameters implements Serializable {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String accountId;

    private String success;

    private String description;

    private String generatedPassword;

    @JsonProperty("password")
    public String getGeneratedPassword() {
        return generatedPassword;
    }

    @JsonProperty("password")
    public void setGeneratedPassword(String generatedPassword) {
        this.generatedPassword = generatedPassword;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("AccountId")
    public String getAccountId() {
        return accountId;
    }

    @JsonProperty("AccountId")
    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
}
