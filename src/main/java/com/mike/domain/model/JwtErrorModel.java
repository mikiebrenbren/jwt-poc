package com.mike.domain.model;

import java.util.Date;

/**
 * Created by michaelbrennan on 12/19/15.
 */
public class JwtErrorModel {
    private String message;
    private Date timestamp;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
