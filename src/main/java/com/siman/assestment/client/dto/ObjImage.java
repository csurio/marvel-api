package com.siman.assestment.client.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ObjImage {
	private String path;
	private String extension;
}
