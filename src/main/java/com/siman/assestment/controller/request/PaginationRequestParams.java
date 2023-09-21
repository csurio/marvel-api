package com.siman.assestment.controller.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class PaginationRequestParams {
	private Integer limit;
	private Integer offset;
}
