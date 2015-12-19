package com.mike.controller;

import com.mike.domain.model.ResponseModel;
import com.mike.exception.ExceptionClass;
import com.mike.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Michael Brennan on 9/8/15.
 */
@RestController
@RequestMapping("/api/")
public class Hello {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(Constants.HELLO+"/{name}")
    public ResponseEntity<ResponseModel> helloWorld(HttpServletRequest request, HttpServletResponse response, @PathVariable final String name){
        logger.debug("This is the name: " + name);
        if(name == null){
            throw new ExceptionClass();
        }
        return new ResponseEntity<>(new ResponseModel(name, request), HttpStatus.OK);
    }

    @RequestMapping(Constants.HELLO)
    public ResponseEntity<ResponseModel> hello(HttpServletRequest request, HttpServletResponse response){
        return new ResponseEntity<>(new ResponseModel(Constants.HELLO, request), HttpStatus.OK);
    }
       }
