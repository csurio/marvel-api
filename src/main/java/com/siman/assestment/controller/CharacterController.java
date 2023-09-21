package com.siman.assestment.controller;

import java.util.Map;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.siman.assestment.controller.request.PaginationRequestParams;
import com.siman.assestment.controller.request.CharactersRequestParams;
import com.siman.assestment.service.CharacterService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

/** 
 * Character Controller: 
 * Expose endpoints for get marvel characters.
 * 
 * @author  Carlos Surio
 * @version 1.0
 * @since   19/09/2023
*/


@Tag(name="CHARACTERS - Marvel API", description = "Fetch all information for your favorites marvel characters")
@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/characters")
public class CharacterController {
	
	private final CharacterService characterService;
    
	@Operation(description="Get all characters with some filters and pagination", summary = "Fetches lists of characters with optional filters.")
	@ApiResponse(responseCode = "200", description = "OK")
	@ApiResponse(responseCode = "500", description = "Internal Server Error.", content = @Content)
	@ApiResponse(responseCode = "400", description = "Bad Request.", content = @Content)
	@ApiResponse(responseCode = "409", description = "Invalid or unrecognized parameter.", content = @Content)	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getAllCharacters(
							@ParameterObject PaginationRequestParams page,
							@ParameterObject CharactersRequestParams params 
			) throws Exception{
    	
    	return ResponseEntity.ok(
    			characterService.getAll(page, params));
    }
    
	@Operation(description="Get comics for specific character id", summary = "Fetches lists of comic of specific characters by id.")
	@ApiResponse(responseCode = "200", description = "OK")
	@ApiResponse(responseCode = "409", description = "Invalid or unrecognized parameter.", content = @Content)	
	@GetMapping(value = "/{characterId}/comics", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getCharacterIdComics(
    						@PathVariable    Map<String, String>     pathVars,
    						@ParameterObject PaginationRequestParams page
    		)throws Exception{
    	return ResponseEntity.ok(
    			characterService.getCharacterIdComics(pathVars, page));
    }
    
	
	@Operation(description="Get image for specific character id", summary = "Fetch description and image url of specific characters by id.")
	@ApiResponse(responseCode = "200", description = "OK")
	@ApiResponse(responseCode = "409", description = "Invalid or unrecognized parameter.", content = @Content)	
	@GetMapping(value = "/{characterId}/image", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getCharacterIdImage(
							@PathVariable Map<String, String> pathVars
			)throws Exception{
    	return ResponseEntity.ok(
    			characterService.getCharacterIdImage(pathVars));
    	
    }
}
