package com.mike.roles;

import org.springframework.security.core.GrantedAuthority;

/**
 * Created by michaelbrennan on 12/18/15.
 */
public class AdminRole implements GrantedAuthority {

    private static final String ADMIN = "admin";

    @Override
    public String getAuthority() {
        return ADMIN;
    }
}
