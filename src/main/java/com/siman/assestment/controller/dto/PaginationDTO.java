package com.siman.assestment.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class PaginationDTO {
	private int     offset;
	private int     limit;
	private int     total;
	private int     count;
}
