package com.siman.assestment.common.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

/**
*
* @author Carlos Surio
*/
@Getter
@Setter
public class MarvelApiException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private long       id;
    private String     code;   
    private HttpStatus httpStatus;
    
    public MarvelApiException(long id, String code, String message,HttpStatus httpStatus) {
        super(message);
        this.id = id;
        this.code = code;
        this.httpStatus = httpStatus;
    }

    public MarvelApiException(String code, String message,HttpStatus httpStatus) {
        super(message);
        this.code = code;
        this.httpStatus = httpStatus;
    }
    
    public MarvelApiException(String message, Throwable cause) {
        super(message, cause);
    }

}
