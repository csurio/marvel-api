package com.siman.assestment.client;

import java.util.Map;

import com.siman.assestment.client.dto.CharacterDataWrapper;
import com.siman.assestment.client.dto.ComicDataWrapper;
import com.siman.assestment.common.exception.MarvelApiClientException;
import com.siman.assestment.controller.request.CharactersRequestPagination;
import com.siman.assestment.controller.request.CharactersRequestParams;

public interface MarvelApiClient {
	
	/*
	 * GET All Characters without filters
	 */
	public CharacterDataWrapper getCharacters()
			throws MarvelApiClientException;
	
	/*
	 * GET All Characters with filters and pagination
	 */
	public CharacterDataWrapper getCharacters(
				CharactersRequestPagination page, 
				CharactersRequestParams     params)
			throws MarvelApiClientException;
	
	/*
	 * GET Character by character id
	 */
	public CharacterDataWrapper getCharacterId(
			Map<String, String> pathVars)
		throws MarvelApiClientException;
	
	/*
	 * GET All Comics of specific character id
	 */
	public ComicDataWrapper getCharacterIdComics(
			Map<String, String>         pathVars,
			CharactersRequestPagination page)
	throws MarvelApiClientException;
}
