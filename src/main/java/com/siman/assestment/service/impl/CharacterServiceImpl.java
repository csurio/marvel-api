package com.siman.assestment.service.impl;

import org.springframework.stereotype.Service;

import com.siman.assestment.client.MarvelApiClient;
import com.siman.assestment.client.dto.CharacterDataWrapper;
import com.siman.assestment.controller.request.CharactersRequestPagination;
import com.siman.assestment.controller.request.CharactersRequestParams;
import com.siman.assestment.service.CharacterService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService{
	
	private final MarvelApiClient marvelApiClient;

	@Override
	public CharacterDataWrapper getAll() 
			throws Exception {
		CharacterDataWrapper wrapper = marvelApiClient.getCharacters();
		return wrapper;
	}

	@Override
	public CharacterDataWrapper getAll(
				CharactersRequestPagination page, 
				CharactersRequestParams     params)
			throws Exception {
		
		CharacterDataWrapper wrapper = marvelApiClient.getCharacters(page, params);
		return wrapper;	}

}
