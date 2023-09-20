package com.siman.assestment.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ApiResponse<T> {
	
	private int    code;
	private String status;
	private T      data;

}
