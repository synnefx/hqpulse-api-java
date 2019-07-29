package com.hqpulse.helper.models;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Set;

/**
 * @author Josekutty
 * 28-07-2019
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class ApiRequest<T> {

    private static final long serialVersionUID = 1L;

    private AuthHeader authHeader;

    private Set<T> records;

    public AuthHeader getAuthHeader() {
        return authHeader;
    }

    public void setAuthHeader(AuthHeader authHeader) {
        this.authHeader = authHeader;
    }

    public Set<T> getRecords() {
        return records;
    }

    public void setRecords(Set<T> records) {
        this.records = records;
    }

    @Override
    public String toString() {
        return "ApiRequest [authHeader=" + authHeader + ", records=" + records + "]";
    }
}
