package com.mike.controller;

import com.mike.domain.UserAuthentication;
import com.mike.roles.AdminRole;
import com.mike.domain.model.SuccessfulLoginModel;
import com.mike.service.TokenAuthenticationService;
import com.mike.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

/**
 * Created by michaelbrennan on 12/16/15.
 */
@RestController
@RequestMapping("/api/auth/")
public class LoginController {

    private final TokenAuthenticationService authenticationService;
    private final UserService userService;

    @Autowired
    public LoginController(TokenAuthenticationService authenticationService, UserService userService) {

        this.userService = userService;
        this.authenticationService = authenticationService;
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ResponseEntity<SuccessfulLoginModel> login(HttpServletRequest request, HttpServletResponse response) throws Exception {

        SuccessfulLoginModel successfulLoginModel = new SuccessfulLoginModel();
        successfulLoginModel.setTimestamp(new Date());

        UserAuthentication userAuthentication = userService.createNewUser(request);
        authenticationService.addAuthentication(response, userAuthentication);

        return new ResponseEntity<>(successfulLoginModel, HttpStatus.OK);
    }

    /**
     * this is  a hardcoded method that creates a new user based on any users attempt to login so long as they have a 'user' and 'password'
     * header in the parameter map, meant to be exchanged by an actual authentication scheme
     * @param request
     */

}
