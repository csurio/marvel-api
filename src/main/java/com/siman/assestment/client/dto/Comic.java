package com.siman.assestment.client.dto;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Comic {
	private int              id;
	private int              digitalId;
	private String           title;
	private double           issueNumber;
	private String           variantDescription;
	private String           description;
	private String           modified;
	private String           isbn;
	private String           upc;
	private String           diamondCode;
	private String           ean;
	private String           issn;
	private int              pageCount;
	private List<ObjText>    textObjects;
	private String           resourceURI;
	private List<ObjUrl>     urls;
	private ObjSummary       series;
	private List<ObjSummary> variants;
	private List<ObjSummary> collections;
	private List<ObjSummary> collectedIssues;
	private List<ObjDate>    dates;
	private List<ObjPrice>   prices;
	private ObjImage         thumbnail;
	private List<ObjImage>   images;
	private ObjList          creators;
	private ObjList          characters;
	private ObjList          stories;
	private ObjList          events;
}
