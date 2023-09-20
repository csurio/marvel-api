package com.siman.assestment.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Image {
	private String path;
	private String extension;

}
