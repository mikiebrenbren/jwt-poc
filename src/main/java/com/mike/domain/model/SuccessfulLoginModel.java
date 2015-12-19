package com.mike.domain.model;

import java.util.Date;

/**
 * Created by michaelbrennan on 12/18/15.
 */
public class SuccessfulLoginModel {

    private static final String message = "Login Successful";
    private Date timestamp;

    public String getMessage() {
        return message;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
