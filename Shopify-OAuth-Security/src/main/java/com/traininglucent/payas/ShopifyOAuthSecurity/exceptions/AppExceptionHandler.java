package com.traininglucent.payas.ShopifyOAuthSecurity.exceptions;

import com.traininglucent.payas.ShopifyOAuthSecurity.model.response.ErrorMessageRest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class AppExceptionHandler {
    @ExceptionHandler(value = {CustomerServiceException.class})
    public ResponseEntity<Object> handelUserServiceException(CustomerServiceException ex, WebRequest request){

        ErrorMessageRest err = new ErrorMessageRest(new Date(), ex.getMessage());

        return new ResponseEntity<>(err, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleOtherExceptions(Exception ex, WebRequest request){

        ErrorMessageRest err = new ErrorMessageRest(new Date(), ex.getMessage());

        return new ResponseEntity<>(err, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
