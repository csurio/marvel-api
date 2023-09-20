package com.siman.assestment.controller;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.siman.assestment.controller.request.CharactersRequestPagination;
import com.siman.assestment.controller.request.CharactersRequestParams;
import com.siman.assestment.service.CharacterService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

/** 
 * Character Controller: 
 * Expose endpoints for get marvel characters.
 * 
 * @author  Carlos Surio
 * @version 1.0
 * @since   19/09/2023
*/

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/characters")
public class CharacterController {
	
	private final CharacterService characterService;
    
    @GetMapping
	public ResponseEntity<?> getAllCharacters(
								CharactersRequestPagination page,
								CharactersRequestParams     params 
			) throws Exception{
    	
    	return ResponseEntity.ok(
    			characterService.getAll(page, params));
    }
    
    @GetMapping("/{characterId}/comics")
    public ResponseEntity<?> getCharacterIdComics(
    						@PathVariable Map<String, String>         pathVars,
    						              CharactersRequestPagination page
    		)throws Exception{
    	System.out.println("api/v1/characters/" + String.valueOf(pathVars.get("characterId"))+"/comics");
    	return ResponseEntity.ok(
    			characterService.getCharacterIdComics(pathVars, page));
    }
    
    
    @GetMapping("/all")
	public ResponseEntity<?> getCharacters(
			@RequestParam(required = false) String  name,
			@RequestParam(required = false) List<Integer>  stories,
			@RequestParam(required = false) List<Integer>  series,
			@RequestParam(required = false) Integer limit,
			@RequestParam(required = false) Integer offset 
			) throws Exception{
    	
    	return ResponseEntity.ok(
    			characterService.getAll());
    }

}