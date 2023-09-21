package com.siman.assestment.common;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Carlos Surio
 * 
 *  The effort to standardize rest API error reports is support by ITEF 
 *  (Internet Engineering Task Force, open standard organization  
 *  that which develop and promotes voluntary internet standards) 
 *  in RFC 7807 which created a generalized error-handling schema composed by five parts.
 *  
 *  1- type     — A URI identifier that categorizes the error
 *  2- title    — A brief, human-readable message about the error
 *  3- code     — The unique error code
 *  4- detail   — A human-readable explanation of the error
 *  5- instance — A URI that identifies the specific occurrence of the error
 *  
 *   Standarized is optional but have advantage, it is use for facebook and twitter ie:
 *   https://graph.facebook.com/oauth/access_token?
 *   https://api.twitter.com/1.1/statuses/update.json?include_entities=true
 */

@Getter
@Setter
public class ApiExceptionResponse {
   @ApiModelProperty(
		   	notes       = "The unique uri identifier that categorizes the error", 
		   	name        = "type", 
		   	required    = true, 
		   	example     = "/errors/authentication/not-authorized")
    private String type = "/errors/uncategorized";
   
    @ApiModelProperty(
    		notes    = "A brief, human-readable message about the error", 
    		name     = "title",
    		required = true, 
    		example  = "The user does not have autorization")
    private String title;
    
    @ApiModelProperty(
    		notes    = "The unique error code", 
    		name     = "code", 
    		required = false, 
    		example  = "192")
    private String code;
    
    @ApiModelProperty(
    		notes    = "A human-readable explanation of the error", 
    		name     = "detail",
    		required = true, 
    		example  = "User does not have persmissions to access resource, " +
                       "please contact with us at https://www.siman.com/tech-support/contact")
    private String detail;
    
    @ApiModelProperty(
    		notes    = "A URI that identifies the specific occurrence of the error", 
    		name     = "detail",
    		required = true, 
    		example  = "/errors/authentication/not-authorized/01")
    private String instance = "/errors/uncategorized/characters";

    public ApiExceptionResponse(String title, String code, String detail) {
        super();
        this.title  = title;
        this.code   = code;
        this.detail = detail;
    }       
}
