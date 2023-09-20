package com.siman.assestment.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class SummaryDTO {
	private String resourceURI;
	private String name;
	private String type;
}
