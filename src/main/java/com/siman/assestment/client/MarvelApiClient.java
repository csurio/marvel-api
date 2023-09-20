package com.siman.assestment.client;

import java.util.Map;

import com.siman.assestment.client.dto.CharacterDataWrapper;
import com.siman.assestment.client.dto.ComicDataWrapper;
import com.siman.assestment.controller.request.CharactersRequestPagination;
import com.siman.assestment.controller.request.CharactersRequestParams;

public interface MarvelApiClient {
	
	public CharacterDataWrapper getCharacters()
			throws Exception;
	
	public CharacterDataWrapper getCharacters(
				CharactersRequestPagination page, 
				CharactersRequestParams     params)
			throws Exception;
	
	public ComicDataWrapper getCharacterIdComics(
			Map<String, String>         pathVars,
			CharactersRequestPagination page)
	throws Exception;
}
