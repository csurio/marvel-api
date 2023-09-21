package com.siman.assestment.client.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ObjList {
	private int              available;
	private int              returned;
	private String           collectionURI;
	private List<ObjSummary> items;
}
