package com.siman.assestment.client;

import com.siman.assestment.client.dto.CharacterDataWrapper;
import com.siman.assestment.controller.request.CharactersRequestPagination;
import com.siman.assestment.controller.request.CharactersRequestParams;

public interface MarvelApiClient {
	
	public CharacterDataWrapper getCharacters()
			throws Exception;
	
	public CharacterDataWrapper getCharacters(
				CharactersRequestPagination page, 
				CharactersRequestParams     params)
			throws Exception;
}
