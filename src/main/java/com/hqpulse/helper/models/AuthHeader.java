package com.hqpulse.helper.models;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author Josekutty
 * 28-07-2019
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthHeader {

    private String id;

    private String token;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
