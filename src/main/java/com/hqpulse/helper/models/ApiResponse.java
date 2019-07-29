package com.hqpulse.helper.models;

import java.util.List;

/**
 * @author Josekutty
 * 28-07-2019
 */
public class ApiResponse<T> {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private Status status;
    private String error;
    private List<String> errors;
    private String message;
    private List<String> messages;
    private T record;
    private List<T> records;

    public ApiResponse() {
        //Default constructor
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    public T getRecord() {
        return record;
    }

    public void setRecord(T record) {
        this.record = record;
    }

    public List<T> getRecords() {
        return records;
    }

    public void setRecords(List<T> records) {
        this.records = records;
    }

    @Override
    public String toString() {
        return "ApiResponse{" +
                "error='" + error + '\'' +
                ", errors=" + errors +
                ", message='" + message + '\'' +
                ", messages=" + messages +
                ", record=" + record +
                ", records=" + records +
                '}';
    }

    public enum Status {
        SUCCESS, ERROR, NOTAUTHENTICATED, NOTAUTHORIZED, LIMIT_EXCEEDED
    }
}
