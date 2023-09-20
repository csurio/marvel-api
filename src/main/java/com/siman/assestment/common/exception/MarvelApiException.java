package com.siman.assestment.common.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class MarvelApiException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private HttpStatus code;
	private String     status;

}
