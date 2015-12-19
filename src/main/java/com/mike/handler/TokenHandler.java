package com.mike.handler;

import com.mike.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.User;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by michaelbrennan on 12/18/15.
 */
public final class TokenHandler {

    static final long ONE_MINUTE_IN_MILLIS=60000; //millisecs

    private final String secret;
    private final UserService userService;

    public TokenHandler(String secret, UserService userService) {
        this.secret = secret;
        this.userService = userService;
    }

    public User parseUserFromToken(String token) throws MalformedJwtException {
        String username = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
        return userService.loadUserByUsername(username);
    }

    public String createTokenForUser(User user) {

        return Jwts.builder()
                .setExpiration(addThirtyMinutesToExpiration())
                .setSubject(user.getUsername())
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    private Date addThirtyMinutesToExpiration() {
        Calendar date = Calendar.getInstance();
        long t= date.getTimeInMillis();
        return new Date(t + (30 * ONE_MINUTE_IN_MILLIS));
    }
}

