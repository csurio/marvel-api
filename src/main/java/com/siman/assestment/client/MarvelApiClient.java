package com.siman.assestment.client;

import java.util.Map;

import com.siman.assestment.client.dto.CharacterDataWrapper;
import com.siman.assestment.client.dto.ComicDataWrapper;
import com.siman.assestment.common.exception.MarvelApiClientException;
import com.siman.assestment.controller.request.PaginationRequestParams;
import com.siman.assestment.controller.request.CharactersRequestParams;
import com.siman.assestment.controller.request.ComicsRequestParams;

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
				PaginationRequestParams page, 
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
			PaginationRequestParams page)
	throws MarvelApiClientException;
	
	
	/*
	 * GET All Comics without filters
	 */
	public ComicDataWrapper getComics()
			throws MarvelApiClientException;
	
	/*
	 * GET All Comics with filters and pagination
	 */
	public ComicDataWrapper getComics(
				PaginationRequestParams page, 
				ComicsRequestParams     params)
			throws MarvelApiClientException;
	
	/*
	 * GET Comic by character id
	 */
	public ComicDataWrapper getComicId(
			Map<String, String> pathVars)
		throws MarvelApiClientException;
}
