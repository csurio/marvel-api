package com.siman.assestment.client.impl;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.siman.assestment.client.MarvelApiClient;
import com.siman.assestment.client.dto.CharacterDataWrapper;
import com.siman.assestment.client.dto.ComicDataWrapper;
import com.siman.assestment.common.exception.MarvelApiClientException;
import com.siman.assestment.common.util.MarvelApiUtils;
import com.siman.assestment.controller.request.PaginationRequestParams;
import com.siman.assestment.controller.request.CharactersRequestParams;
import com.siman.assestment.controller.request.ComicsRequestParams;

/** 
 * Marvel Api Client Implementation: 
 * Consume endpoints for get marvel characters and comics.
 * 
 * @author  Carlos Surio
 * @version 1.0
 * @since   20/09/2023
*/
@Service
public class MarvelApiClientImpl implements MarvelApiClient {
	
	private final RestTemplate restTemplate;
	private final String       publicKey;
	private final String       privateKey;
	private final String       baseUrl;
	private final String       charactersPath;
	private final String       charactersIdPath;
	private final String       charactersComicsPath;
	private final String       comicsPath;
	private final String       comicsIdPath;
	
	/**
	 * Constructs a new instance of the MarvelApiClientImpl class with the specified configuration and dependencies.
	 *
	 * @param publicKey            The public API key for accessing the Marvel API.
	 * @param privateKey           The private API key for accessing the Marvel API.
	 * @param baseUrl              The base URL of the Marvel API.
	 * @param charactersPath       The path for retrieving Marvel characters.
	 * @param charactersIdPath     The path for retrieving details of a specific Marvel character by ID.
	 * @param charactersComicsPath The path for retrieving comics associated with a specific Marvel character.
	 * @param comicsPath           The path for retrieving Marvel comics.
	 * @param comicsIdPath         The path for retrieving details of a specific Marvel comic by ID.
	 * @param restTemplate         The RestTemplate instance used for making HTTP requests.
	 */
	public MarvelApiClientImpl(	
			@Value("${marvel.api.public-key}")            String publicKey,
			@Value("${marvel.api.private-key}")           String privateKey,
			@Value("${marvel.api.base-url}")              String baseUrl,
			@Value("${marvel.api.characters.path}")       String charactersPath,
			@Value("${marvel.api.characters-id.path}")    String charactersIdPath,
			@Value("${marvel.api.characters-comics.path}")String charactersComicsPath,
			@Value("${marvel.api.comics.path}")           String comicsPath,
			@Value("${marvel.api.comics-id.path}")        String comicsIdPath,
			RestTemplate restTemplate) {
		
				this.publicKey            = publicKey;
				this.privateKey           = privateKey;
				this.baseUrl              = baseUrl;
				this.charactersPath       = charactersPath;
				this.charactersIdPath     = charactersIdPath;
				this.charactersComicsPath = charactersComicsPath;
				this.comicsPath           = comicsPath;
				this.restTemplate         = restTemplate;
				this.comicsIdPath         = comicsIdPath;
	   		}

	/**
	 * GET All Characters without filters
	 * Retrieves information about Marvel characters from the API.
	 *
	 * @return A CharacterDataWrapper object containing information about the characters.
	 * @throws MarvelApiClientException If an error occurs while calling the Marvel API or processing the response.
	 */
	@Override
	public CharacterDataWrapper getCharacters() throws MarvelApiClientException {
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

	/**
	 * GET All Characters with filters and pagination
	 * Retrieves information about Marvel characters from the API with optional pagination and filtering.
	 *
	 * @param page   Pagination parameters for specifying the page size and offset.
	 * @param params Additional filtering parameters for character retrieval.
	 * 
	 * @return A CharacterDataWrapper object containing information about the characters.
	 * @throws MarvelApiClientException If an error occurs while calling the Marvel API or processing the response.
	 */
	@Override
	public CharacterDataWrapper getCharacters(
									PaginationRequestParams page, 
									CharactersRequestParams params)
								throws MarvelApiClientException {
		
		String path = this.charactersPath;
		MultiValueMap<String, String> queryParams = getCharacterParams(params);
		
		String url  = buildUrl(path, page, queryParams, new HashMap<String, String>());
		CharacterDataWrapper response = restTemplate.getForObject(url,CharacterDataWrapper.class);
		
		return response;
	}
	
	/**
	 * GET Comics by character id
	 * Retrieves comics associated with a specific Marvel character by ID.
	 *
	 * @param pathVars              A map of path variables required for constructing the URL, containing the character ID.
	 * @param page                  Pagination parameters for specifying the page size and offset.
	 * 
	 * @return A ComicDataWrapper object containing information about the character's comics.
	 * @throws MarvelApiClientException If an error occurs while calling the Marvel API or processing the response.
	 */
	@Override
	public ComicDataWrapper getCharacterIdComics(
						Map<String, String>     pathVars, 
						PaginationRequestParams page)
								throws MarvelApiClientException {
				
		
		String url = buildUrl(this.charactersComicsPath, page, null, pathVars);
		ComicDataWrapper response = restTemplate.getForObject(url,ComicDataWrapper.class);
		return response;
	}
	
	/**
	 * GET specific character id
	 * Retrieves details of a specific Marvel character by ID.
	 *
	 * @param pathVars A map of path variables required for constructing the URL, typically containing the character ID.
	 * 
	 * @return A CharacterDataWrapper object containing information about the specific character.
	 * @throws MarvelApiClientException If an error occurs while calling the Marvel API or processing the response.
	 */
	@Override
	public CharacterDataWrapper getCharacterId(
						Map<String, String> pathVars) 
								throws MarvelApiClientException {
		String url = buildUrl(this.charactersIdPath, null, null, pathVars);
		CharacterDataWrapper response = restTemplate.getForObject(url,CharacterDataWrapper.class);
		return response;
	}
	
	/**
	 * GET All comics without filters
	 * Retrieves information about Marvel comics from the API.
	 *
	 * @return A ComicDataWrapper object containing information about the comics.
	 * @throws MarvelApiClientException If an error occurs while calling the Marvel API or processing the response.
	 */
	@Override
	public ComicDataWrapper getComics() throws MarvelApiClientException {
		final String endpoint   = StringUtils.join(
				this.baseUrl, 
				this.comicsPath);
		
		String url = UriComponentsBuilder.fromUriString(endpoint)
				.queryParams(getApiKeysParams())
				.build()
				.toUriString();

		ComicDataWrapper response = restTemplate.getForObject(url,ComicDataWrapper.class);
		return response;
	}

	/**
	 * GET All Comics with filters and pagination
	 * Retrieves information about Marvel comics from the API with optional pagination and filtering.
	 *
	 * @param page   Pagination parameters for specifying the page size and offset.
	 * @param params Additional filtering parameters for comic retrieval.
	 * 
	 * @return A CharacterDataWrapper object containing information about the comics.
	 * @throws MarvelApiClientException If an error occurs while calling the Marvel API or processing the response.
	 */
	@Override
	public ComicDataWrapper getComics(PaginationRequestParams page, ComicsRequestParams params)
			throws MarvelApiClientException {
		
		String path = this.comicsPath;
		MultiValueMap<String, String> queryParams = getComicParams(params);
		
		String url  = buildUrl(path, page, queryParams, new HashMap<String, String>());
		ComicDataWrapper response = restTemplate.getForObject(url,ComicDataWrapper.class);
		
		return response;
	}
	
	/**
	 * GET specific comic by id
	 * Retrieves details of a specific Marvel comic by ID.
	 *
	 * @param pathVars A map of path variables required for constructing the URL, typically containing the comic ID.
	 * 
	 * @return A ComicDataWrapper object containing information about the specific comic.
	 * @throws MarvelApiClientException If an error occurs while calling the Marvel API or processing the response.
	 */
	@Override
	public ComicDataWrapper getComicId(Map<String, String> pathVars) throws MarvelApiClientException {
		String url = buildUrl(this.comicsIdPath, null, null, pathVars);
		ComicDataWrapper response = restTemplate.getForObject(url,ComicDataWrapper.class);
		return response;
	}
	
	/*
	 * Private Methods
	 * 
	 */
	
	private String buildUrl(
						String path, 
						PaginationRequestParams       page, 
						MultiValueMap<String, String> params,
						Map<String, String>           pathVars) 
								throws MarvelApiClientException {

		String endpoint = StringUtils.join(this.baseUrl, path);

		if (!pathVars.isEmpty()) {
			endpoint = UriComponentsBuilder.fromUriString(endpoint)
							.buildAndExpand(pathVars)
							.toUriString();
		}

		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(endpoint);
		if (params != null) {
			builder.queryParams(params);
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
	
	
	private MultiValueMap<String, String> getApiKeysParams() throws MarvelApiClientException {
		MultiValueMap<String, String> apiKeyParams = new LinkedMultiValueMap<>();
		String timestamp  = String.valueOf(System.currentTimeMillis());
		String hashApiKey;
		try {
			hashApiKey = MarvelApiUtils.generateMD5(
											timestamp + 
											this.privateKey +  
											this.publicKey);
		} catch (NoSuchAlgorithmException e) {
			throw new MarvelApiClientException("401",e.getMessage(),HttpStatus.UNAUTHORIZED);
		}
		
		apiKeyParams.add("ts"    , timestamp);
		apiKeyParams.add("apikey", publicKey);
		apiKeyParams.add("hash"  , hashApiKey);
	
		return apiKeyParams;
	}
	
	private MultiValueMap<String, String> getCharacterParams(CharactersRequestParams params){
		MultiValueMap<String, String> queryParams = null;
		if (params != null) {
			queryParams = new LinkedMultiValueMap<>();
			if (params.getName() != null)
				queryParams.add("name", params.getName());
			if (params.getNameStartWith() != null)
				queryParams.add("nameStartWith", params.getNameStartWith());
			if (params.getComics() != null)
				queryParams.add("comics", StringUtils.join(params.getComics(), ","));
			if (params.getSeries() != null)
				queryParams.add("series", StringUtils.join(params.getSeries(), ","));
		}
		return queryParams;
	}
	
	private MultiValueMap<String, String> getComicParams(ComicsRequestParams params){
		MultiValueMap<String, String> queryParams = null;
		if (params != null) {
			queryParams = new LinkedMultiValueMap<>();
			if (params.getFormat() != null)
				queryParams.add("format", params.getFormat());
			if (params.getFormatType() != null)
				queryParams.add("formatType", params.getFormatType());
			if (params.getTitle() != null)
				queryParams.add("title", params.getTitle());
			if (params.getTitleStratWith() != null)
				queryParams.add("titleStratWith", params.getTitleStratWith());
		}
		return queryParams;
	}

	
}
