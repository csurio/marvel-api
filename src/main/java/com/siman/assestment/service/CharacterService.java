package com.siman.assestment.service;

import java.util.Map;

import com.siman.assestment.client.dto.CharacterDataWrapper;
import com.siman.assestment.client.dto.ComicDataWrapper;
import com.siman.assestment.controller.request.PaginationRequestParams;
import com.siman.assestment.controller.request.CharactersRequestParams;
import com.siman.assestment.controller.response.CharacterImageResponse;

public interface CharacterService {
	
	public CharacterDataWrapper getAll() 
			throws Exception;
	
	
	public CharacterDataWrapper getAll(
					PaginationRequestParams page, 
					CharactersRequestParams params)
			throws Exception;
	
	
	public ComicDataWrapper getCharacterIdComics(
			        Map<String, String>         pathVars,
					PaginationRequestParams page)
			throws Exception;
	
	
	public CharacterImageResponse getCharacterIdImage(
					Map<String, String> pathVars)
			throws Exception;

}
