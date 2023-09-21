package com.siman.assestment.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.siman.assestment.client.dto.CharacterDataWrapper;
import com.siman.assestment.client.dto.CharacterHero;
import com.siman.assestment.controller.dto.CharacterDTO;
import com.siman.assestment.controller.response.CharacterResponse;

@Mapper(componentModel = "spring")
public interface CharacterMapper {
	
	@Mapping(source = "code", target = "code")
    @Mapping(source = "status", target = "status")
    CharacterResponse mapToCharacterResponse(CharacterDataWrapper source);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "thumbnail.path", target = "thumbnail")
    @Mapping(source = "comics", target = "comics")
    CharacterDTO mapToCharacterDTO(CharacterHero source);
	
}
