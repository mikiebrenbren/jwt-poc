package com.mike.service;


import com.mike.domain.UserAuthentication;
import com.mike.roles.AdminRole;

import com.mike.utils.Constants;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Michael Brennan on 12/18/15.
 */
@Service
public class UserService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final AccountStatusUserDetailsChecker detailsChecker = new AccountStatusUserDetailsChecker();
    private final HashMap<String, User> userMap = new HashMap<>();

    @Override
    public final User loadUserByUsername(String username) {

        //creates a hardcoded user in order to compare with the jwt, to be replaced by an actual authentication scheme
        Collection<GrantedAuthority> usersRoles  = new ArrayList<>();
        usersRoles.add(new AdminRole());
        User userToAdd = new User(Constants.USERNAME,Constants.PASSWORD, usersRoles);
        userMap.put(Constants.USERNAME, userToAdd);

        //hard coded user with role
        final User user = userMap.get(username);
        if (user == null) {
            throw new UsernameNotFoundException("user not found");
        }
        detailsChecker.check(user);
        return user;
    }

    public UserAuthentication createNewUser(HttpServletRequest request) throws Exception {

        Map<String, String[]> parameterMap = request.getParameterMap();

        String user = parameterMap.get("user")[0];
        String password = parameterMap.get("password")[0];
        //checks to see if passwords have been entered then changes them to hardcoded values
        if (user != null && password != null) {
            user = Constants.USERNAME; //hardcoded values
            password = Constants.PASSWORD;
        }else{
            throw new Exception("Username and password required");
        }

        Collection<GrantedAuthority> grantedRole = new ArrayList<>();
        grantedRole.add(new AdminRole());

        return new UserAuthentication(new User(user, password, grantedRole));
    }

    public void addUser(User user) {
        userMap.put(user.getUsername(), user);
    }
}