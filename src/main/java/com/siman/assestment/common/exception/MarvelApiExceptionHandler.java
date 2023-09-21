/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siman.assestment.common.exception;

import java.io.IOException;
import java.net.UnknownHostException;
import java.security.NoSuchAlgorithmException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.siman.assestment.common.ApiExceptionResponse;

/**
 *
 * @author Carlos Surio
 * 
 * Standard http communication have five levels of response codes
 * 100-level (Informational) — Server acknowledges a request, it mean that request was received and understood, it is transient response , alert client for awaiting response
 * 200-level (Success) — Server completed the request as expected
 * 300-level (Redirection) — Client needs to perform further actions to complete the request
 * 400-level (Client error) — Client sent an invalid request
 * 500-level (Server error) — Server failed to fulfill a valid request due to an error with server
 * 
 * The goal of handler exception is provide to customer with appropriate code and 
 * additional comprehensible information to help troubleshoot the problem. 
 * The message portion of the body should be present as user interface, event if 
 * customer send an Accept-Language header (en or french ie) we should translate the title part 
 * to customer language if we support internationalization, detail is intended for developer
 * of clients, so the translation is not necessary. If more than one error is need to report , we can 
 * response a list of errors.
 * 
 */
@RestControllerAdvice
public class MarvelApiExceptionHandler {       
	
	@ExceptionHandler(UnknownHostException.class)
    public ResponseEntity<ApiExceptionResponse> handleUnknownHostException(UnknownHostException ex) {
        ApiExceptionResponse response = new ApiExceptionResponse("Conection Error","erorr-1024",ex.getMessage());
        return new ResponseEntity(response, HttpStatus.PARTIAL_CONTENT);
    }
    
    @ExceptionHandler(MarvelApiClientException.class)
    public ResponseEntity<ApiExceptionResponse> handleMarvelApiClientException(MarvelApiClientException ex) {
        ApiExceptionResponse response = new ApiExceptionResponse("REST Client Error",ex.getCode(),ex.getMessage());
        return new ResponseEntity(response, HttpStatus.PARTIAL_CONTENT);
    }
    
    @ExceptionHandler(MarvelApiException.class)
    public ResponseEntity<ApiExceptionResponse> handleMarvelApiException(MarvelApiException ex) {
        ApiExceptionResponse response = new ApiExceptionResponse("API Validation Error",ex.getCode(),ex.getMessage());
        return new ResponseEntity(response, HttpStatus.PARTIAL_CONTENT);
    }
    
    
    @ExceptionHandler(NoSuchAlgorithmException.class)
    public ResponseEntity<ApiExceptionResponse> handleMarvelApiException(NoSuchAlgorithmException ex) {
        ApiExceptionResponse response = new ApiExceptionResponse("API Generate key Error","erorr-401",ex.getMessage());
        return new ResponseEntity(response, HttpStatus.UNAUTHORIZED);
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiExceptionResponse> handleGenericException(Exception ex) {
        ApiExceptionResponse response = new ApiExceptionResponse("Server Error","erorr-500",ex.getMessage());
        return new ResponseEntity(response, HttpStatus.PARTIAL_CONTENT);
    }
    
}
