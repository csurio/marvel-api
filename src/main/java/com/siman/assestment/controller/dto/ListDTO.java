package com.siman.assestment.controller.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ListDTO {
	private int              available;
	private int              returned;
	private String           collectionURI;
	private List<SummaryDTO> items;
}
