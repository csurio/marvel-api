package com.siman.assestment.client.impl;

import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.siman.assestment.client.MarvelApiClient;
import com.siman.assestment.client.dto.CharacterDataWrapper;
import com.siman.assestment.common.util.MarvelApiUtils;
import com.siman.assestment.controller.request.CharactersRequestPagination;
import com.siman.assestment.controller.request.CharactersRequestParams;


@Service
public class MarvelApiClientImpl implements MarvelApiClient {
	
	private final RestTemplate restTemplate;
	private final String       publicKey;
	private final String       privateKey;
	private final String       baseUrl;
	private final String       charactersEndpoint;
	
	public MarvelApiClientImpl(	
			@Value("${marvel.api.public-key}")         String publicKey,
			@Value("${marvel.api.private-key}")        String privateKey,
			@Value("${marvel.api.base-url}")           String baseUrl,
			@Value("${marvel.api.characters-endpoint}")String charactersEndpoint,
			RestTemplate restTemplate) {
				this.publicKey          = publicKey;
				this.privateKey         = privateKey;
				this.baseUrl            = baseUrl;
				this.charactersEndpoint = charactersEndpoint;
				this.restTemplate       = restTemplate;
	   		}

	@Override
	public CharacterDataWrapper getCharacters() throws Exception {
		
		final String endpoint   = StringUtils.join(
        							this.baseUrl, 
        							this.charactersEndpoint);
		
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
			
		String url = buildUrl(page, params);
		CharacterDataWrapper response = restTemplate.getForObject(url,CharacterDataWrapper.class);
		return response;
	}
	
	private MultiValueMap<String, String> getApiKeysParams() 
			throws Exception {
		
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
	
	private String buildUrl(
						CharactersRequestPagination page, 
						CharactersRequestParams     params) 
			throws Exception {
		
		final String endpoint   = StringUtils.join(
        								this.baseUrl, 
        								this.charactersEndpoint);
		
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(endpoint);
		
		if(params != null) {
			if(params.getName()!= null)
				builder.queryParam("name", params.getName());
			if(params.getComics()!= null)
				builder.queryParam("comics", StringUtils.join(params.getComics(), ","));
			if(params.getSeries()!= null)
				builder.queryParam("series", StringUtils.join(params.getSeries(), ","));
		}
		
		if(page != null) {
			if(page.getLimit()!=null)
				builder.queryParam("limit", page.getLimit());
			if(page.getOffset()!=null)
				builder.queryParam("offset", page.getOffset());
		}
		
		builder.queryParams(getApiKeysParams());
		
		String url = builder.build().toUriString();
		
		return url;
	}
		

}
