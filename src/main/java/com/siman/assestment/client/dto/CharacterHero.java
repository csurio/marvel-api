package com.siman.assestment.client.dto;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CharacterHero {
	private int       id;
	private String    name;
	private String    description;
	private Date      modified;
	private String    resourceURI;
	private List<Url> urls;
	private Image     thumbnail;
	private ObjList   comics;
	private ObjList   stories;
	private ObjList   events;
	private ObjList   series;
}
