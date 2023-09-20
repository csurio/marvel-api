package com.siman.assestment.service.mapper;

import org.mapstruct.Mapper;

import com.siman.assestment.client.dto.CharacterDataWrapper;
import com.siman.assestment.controller.response.CharacterImageResponse;

@Mapper(componentModel = "spring")
public interface CharacterMapper {
	
	CharacterImageResponse toCharacterImageResponse(final CharacterDataWrapper response);
	

}
