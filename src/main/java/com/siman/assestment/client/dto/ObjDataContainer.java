package com.siman.assestment.client.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ObjDataContainer<T> {
	private int     offset;
	private int     limit;
	private int     total;
	private int     count;
	private List<T> results;
}
