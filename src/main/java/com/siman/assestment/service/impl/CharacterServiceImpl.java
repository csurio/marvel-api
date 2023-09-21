package com.siman.assestment.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.siman.assestment.client.MarvelApiClient;
import com.siman.assestment.client.dto.CharacterDataWrapper;
import com.siman.assestment.client.dto.ComicDataWrapper;
import com.siman.assestment.common.exception.MarvelApiClientException;
import com.siman.assestment.common.exception.MarvelApiException;
import com.siman.assestment.controller.request.CharactersRequestPagination;
import com.siman.assestment.controller.request.CharactersRequestParams;
import com.siman.assestment.controller.response.CharacterImageResponse;
import com.siman.assestment.service.CharacterService;
import com.siman.assestment.service.mapper.CharacterMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService{
	
	private final MarvelApiClient marvelApiClient;
	private final CharacterMapper characterMapper;

	@Override
	public CharacterDataWrapper getAll() 
			throws MarvelApiException, MarvelApiClientException {
		CharacterDataWrapper wrapper;
		
		wrapper = marvelApiClient.getCharacters();
		
		return wrapper;
	}

	
	@Override
	public CharacterDataWrapper getAll(
				CharactersRequestPagination page, 
				CharactersRequestParams     params)
			throws MarvelApiException, MarvelApiClientException {
		
		CharacterDataWrapper wrapper = marvelApiClient.getCharacters(page, params);
		return wrapper;	}

	
	@Override
	public ComicDataWrapper getCharacterIdComics(
				Map<String, String>         pathVars, 
				CharactersRequestPagination page)
			throws MarvelApiException, MarvelApiClientException {
		
		ComicDataWrapper wrapper = marvelApiClient.getCharacterIdComics(pathVars, page);
		return wrapper;
	}


	@Override
	public CharacterImageResponse getCharacterIdImage(
				Map<String, String> pathVars) 
			throws MarvelApiException, MarvelApiClientException {
		
		CharacterDataWrapper wrapper = marvelApiClient.getCharacterId(pathVars);
		
		CharacterImageResponse response = CharacterImageResponse.builder()
				.status(wrapper.getStatus())
				.code(wrapper.getCode())
				.description(wrapper.getData().getResults().get(0).getDescription())
				.image(wrapper.getData().getResults().get(0).getThumbnail().getPath()+ "." +
					   wrapper.getData().getResults().get(0).getThumbnail().getExtension())
				.build();
				
		return response;
	}

}
