package com.siman.assestment.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.siman.assestment.client.MarvelApiClient;
import com.siman.assestment.client.dto.ComicDataWrapper;
import com.siman.assestment.common.exception.MarvelApiClientException;
import com.siman.assestment.common.exception.MarvelApiException;
import com.siman.assestment.controller.request.ComicsRequestParams;
import com.siman.assestment.controller.request.PaginationRequestParams;
import com.siman.assestment.service.ComicService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ComicServiceImpl implements ComicService {
	
	private final MarvelApiClient marvelApiClient; 

	@Override
	public ComicDataWrapper getAll() 
			throws MarvelApiException, MarvelApiClientException {
		return marvelApiClient.getComics();
	}

	@Override
	public ComicDataWrapper getAll(PaginationRequestParams page, ComicsRequestParams params) 
			throws MarvelApiException, MarvelApiClientException {
		
		return marvelApiClient.getComics(page, params);
	}

	@Override
	public ComicDataWrapper getComicById(Map<String, String> pathVars) 
			throws MarvelApiException, MarvelApiClientException {
		return marvelApiClient.getComicId(pathVars);
	}

}
