package com.siman.assestment.controller.request;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CharactersRequestParams {
	private String        name;
	private String        nameStartWith;
	private List<Integer> comics;
	private List<Integer> series;
	private String        format;
	private String        formatType;
	private String        title;
	private String        titleStratWith;
}
