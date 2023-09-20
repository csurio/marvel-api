package com.siman.assestment.client.impl;

import java.net.URI;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.siman.assestment.client.MarvelApiClient;
import com.siman.assestment.client.dto.CharacterDataWrapper;
import com.siman.assestment.client.dto.ComicDataWrapper;
import com.siman.assestment.common.util.MarvelApiUtils;
import com.siman.assestment.controller.request.CharactersRequestPagination;
import com.siman.assestment.controller.request.CharactersRequestParams;


@Service
public class MarvelApiClientImpl implements MarvelApiClient {
	
	private final RestTemplate restTemplate;
	private final String       publicKey;
	private final String       privateKey;
	private final String       baseUrl;
	private final String       charactersPath;
	private final String       charactersComicsPath;
	private final String       comicsPath;
	
	public MarvelApiClientImpl(	
			@Value("${marvel.api.public-key}")            String publicKey,
			@Value("${marvel.api.private-key}")           String privateKey,
			@Value("${marvel.api.base-url}")              String baseUrl,
			@Value("${marvel.api.characters.path}")       String charactersPath,
			@Value("${marvel.api.characters-comics.path}")String charactersComicsPath,
			@Value("${marvel.api.comics.path}")           String comicsPath,
			RestTemplate restTemplate) {
		
				this.publicKey            = publicKey;
				this.privateKey           = privateKey;
				this.baseUrl              = baseUrl;
				this.charactersPath       = charactersPath;
				this.charactersComicsPath = charactersComicsPath;
				this.comicsPath           = comicsPath;
				this.restTemplate         = restTemplate;
	   		}

	@Override
	public CharacterDataWrapper getCharacters() throws Exception {
		final String endpoint   = StringUtils.join(
        							this.baseUrl, 
        							this.charactersPath);
		
		String url = UriComponentsBuilder.fromUriString(endpoint)
						.queryParams(getApiKeysParams())
						.build()
						.toUriString();
		
		CharacterDataWrapper response = restTemplate.getForObject(url,CharacterDataWrapper.class);
		return response;
	}


	@Override
	public CharacterDataWrapper getCharacters(
									CharactersRequestPagination page, 
									CharactersRequestParams     params)
								throws Exception {
		
		String path = this.charactersPath;
		String url  = buildUrl(path, page, params, new HashMap<String, String>());
		CharacterDataWrapper response = restTemplate.getForObject(url,CharacterDataWrapper.class);
		return response;
	}
	
	@Override
	public ComicDataWrapper getCharacterIdComics(
						Map<String, String>         pathVars, 
						CharactersRequestPagination page)
								throws Exception {
				
		
		String url = buildUrl(this.charactersComicsPath, page, null, pathVars);
		ComicDataWrapper response = restTemplate.getForObject(url,ComicDataWrapper.class);
		return response;
	}
	
	
	private String buildUrl(String path, CharactersRequestPagination page, CharactersRequestParams params,
			Map<String, String> pathVars) throws Exception {

		String endpoint = StringUtils.join(this.baseUrl, path);

		if (!pathVars.isEmpty()) {
			endpoint = UriComponentsBuilder.fromUriString(endpoint)
							.buildAndExpand(pathVars)
							.toUriString();
		}

		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(endpoint);
		if (params != null) {
			if (params.getName() != null)
				builder.queryParam("name", params.getName());
			if (params.getNameStartWith() != null)
				builder.queryParam("nameStartWith", params.getNameStartWith());
			if (params.getFormat() != null)
				builder.queryParam("format", params.getFormat());
			if (params.getFormatType() != null)
				builder.queryParam("formatType", params.getFormatType());
			if (params.getTitle() != null)
				builder.queryParam("title", params.getTitle());
			if (params.getTitleStratWith() != null)
				builder.queryParam("titleStratWith", params.getTitleStratWith());
			if (params.getComics() != null)
				builder.queryParam("comics", StringUtils.join(params.getComics(), ","));
			if (params.getSeries() != null)
				builder.queryParam("series", StringUtils.join(params.getSeries(), ","));
		}
		if (page != null) {
			if (page.getLimit() != null)
				builder.queryParam("limit", page.getLimit());
			if (page.getOffset() != null)
				builder.queryParam("offset", page.getOffset());
		}
		builder.queryParams(getApiKeysParams());
		String url = builder.build().toUriString();
		return url;
	}
	
	
	private MultiValueMap<String, String> getApiKeysParams()throws Exception {
		MultiValueMap<String, String> apiKeyParams = new LinkedMultiValueMap<>();
		String timestamp  = String.valueOf(System.currentTimeMillis());
		String hashApiKey = MarvelApiUtils.generateMD5(
										timestamp + 
										this.privateKey +  
										this.publicKey);
		
		apiKeyParams.add("ts"    , timestamp);
		apiKeyParams.add("apikey", publicKey);
		apiKeyParams.add("hash"  , hashApiKey);
	
		return apiKeyParams;
	}
	
	
}
