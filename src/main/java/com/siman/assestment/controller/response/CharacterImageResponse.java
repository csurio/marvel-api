package com.siman.assestment.controller.response;

import com.siman.assestment.common.ApiResponse;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CharacterImageResponse extends ApiResponse {
	private String description;
	private String image;
	
	@Builder
	public CharacterImageResponse(int code, String status, String description, String image) {
		super(code, status);
		this.description = description;
		this.image = image;
	}
	
	
}
