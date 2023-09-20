package com.siman.assestment.service;

import java.util.Map;

import com.siman.assestment.client.dto.CharacterDataWrapper;
import com.siman.assestment.client.dto.ComicDataWrapper;
import com.siman.assestment.controller.request.CharactersRequestPagination;
import com.siman.assestment.controller.request.CharactersRequestParams;

public interface CharacterService {
	
	public CharacterDataWrapper getAll() 
			throws Exception;
	
	public CharacterDataWrapper getAll(
					CharactersRequestPagination page, 
					CharactersRequestParams params)
			throws Exception;
	
	public ComicDataWrapper getCharacterIdComics(
			        Map<String, String>         pathVars,
					CharactersRequestPagination page)
			throws Exception;

}
