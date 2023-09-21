package com.siman.assestment.controller;

import java.util.Map;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.siman.assestment.controller.request.ComicsRequestParams;
import com.siman.assestment.controller.request.PaginationRequestParams;
import com.siman.assestment.service.ComicService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

/** 
 * Comic Controller: 
 * Expose endpoints for get marvel characters.
 * 
 * @author  Carlos Surio
 * @version 1.0
 * @since   19/09/2023
*/

@Tag(name="COMICS - Marvel API", description = "Fetch all information for marvel comics ")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/comics")
public class ComicController {
	
	private final ComicService comicService;
	
	@Operation(description="Get all comics with some filters and pagination", summary = "Fetches lists of comics with optional filters.")
	@ApiResponse(responseCode = "200", description = "OK")
	@ApiResponse(responseCode = "409", description = "Invalid or unrecognized parameter.", content = @Content)	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getAllComics(
			@ParameterObject PaginationRequestParams page,
			@ParameterObject ComicsRequestParams     params 
			) throws Exception{
		
		return ResponseEntity.ok(comicService.getAll(page, params));
		
	}
	
	@Operation(description="Get comics for it specific id", summary = "Fetch a specific comic by id.")
	@ApiResponse(responseCode = "200", description = "OK")
	@ApiResponse(responseCode = "409", description = "Invalid or unrecognized parameter.", content = @Content)	
	@GetMapping(value = "/{comicId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getComicById(@PathVariable Map<String, String> pathVars)
    		throws Exception{
    	return ResponseEntity.ok(comicService.getComicById(pathVars));
    }

}
