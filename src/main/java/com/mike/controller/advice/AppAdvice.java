package com.mike.controller.advice;

import com.mike.domain.model.JwtErrorModel;
import com.mike.exception.ExceptionClass;
import io.jsonwebtoken.MalformedJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * Created by michaelbrennan on 12/18/15.
 */
@ControllerAdvice
public class AppAdvice {

//    @ExceptionHandler(DateInputException.class)
//    public ResponseEntity<DateErrorModel> indexOutOfBounds(HttpServletRequest request, DateInputException exception){
//
//        logger.log(Level.WARNING, "", exception);
//        DateErrorModel errorModel = new DateErrorModel();
//        errorModel.setErrorMessage(exception.getMessage());
//        errorModel.setTimeStamp(new Date());
//        errorModel.setRequestUri(request.getRequestURI());
//
//        return new ResponseEntity<>(errorModel, HttpStatus.BAD_REQUEST);  //400
//    }

    @ExceptionHandler(ExceptionClass.class)
    public void myError(HttpServletRequest request, HttpServletResponse response, Exception exception) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value(), "Resource not found, invalid ID");
    }

    @ExceptionHandler(MalformedJwtException.class)
    public ResponseEntity<JwtErrorModel> myError(HttpServletResponse response, MalformedJwtException ex) {
        JwtErrorModel errorResponse = new JwtErrorModel();
        errorResponse.setMessage(ex.getMessage());
        errorResponse.setTimestamp(new Date());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST); //could be a bad request or unauthorized
    }
}
