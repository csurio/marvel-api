package com.siman.assestment.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CharacterDTO {
	private int       id;
	private String    name;
	private String    description;
	private String    thumbnail;
	private ListDTO   comics;
}
