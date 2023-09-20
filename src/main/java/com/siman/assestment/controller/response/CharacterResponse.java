package com.siman.assestment.controller.response;

import java.util.List;

import com.siman.assestment.common.ApiResponse;
import com.siman.assestment.controller.dto.CharacterDTO;
import com.siman.assestment.controller.dto.PaginationDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CharacterResponse extends ApiResponse {
	private PaginationDTO      pagination;
	private List<CharacterDTO> results;
}
