package com.siman.assestment.client.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "CharacterDataWrapper", description = "Description CharacterDataWrapper Schema")
public class CharacterDataWrapper extends ObjDataWrapper{
	private ObjDataContainer<CharacterHero> data;
}
