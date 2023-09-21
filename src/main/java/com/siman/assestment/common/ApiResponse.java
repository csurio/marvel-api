package com.siman.assestment.common;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {
	
	@ApiModelProperty(
    		notes    = "The code of endpoint response ", 
    		name     = "code", 
    		required = true, 
    		example  = "200")
	private int    code;
	
	@ApiModelProperty(
    		notes    = "The status of endpoint response ", 
    		name     = "status", 
    		required = true, 
    		example  = "OK")
	private String status;

}
