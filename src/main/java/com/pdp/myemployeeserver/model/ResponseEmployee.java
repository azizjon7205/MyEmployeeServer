package com.pdp.myemployeeserver.model;

import org.springframework.data.mongodb.core.mapping.Field;

public class ResponseEmployee<T> {

    @Field
    private String status;

    @Field
    private T data;

    @Field
    private String message;

    public ResponseEmployee(String status, T data, String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
