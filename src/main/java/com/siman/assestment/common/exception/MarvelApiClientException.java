/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siman.assestment.common.exception;

import org.springframework.http.HttpStatus;

/**
 *
 * @author Carlos Surio
 */
public class MarvelApiClientException extends Exception{
  
    private static final long serialVersionUID = 1L;
	private long id;
    private String code;   
    private HttpStatus httpStatus;
    
    public MarvelApiClientException(long id, String code, String message,HttpStatus httpStatus) {
        super(message);
        this.id = id;
        this.code = code;
        this.httpStatus = httpStatus;
    }

    public MarvelApiClientException(String code, String message,HttpStatus httpStatus) {
        super(message);
        this.code = code;
        this.httpStatus = httpStatus;
    }
    
    public MarvelApiClientException(String message, Throwable cause) {
        super(message, cause);
    }     

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }    

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }   

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }    
    
}
