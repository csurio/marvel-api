package com.siman.assestment.controller.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ComicsRequestParams {
	private String        format;
	private String        formatType;
	private String        title;
	private String        titleStratWith;
}
