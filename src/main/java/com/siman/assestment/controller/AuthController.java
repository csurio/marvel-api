package com.siman.assestment.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.siman.assestment.controller.request.LoginRequest;
import com.siman.assestment.controller.request.RegisterRequest;

import lombok.RequiredArgsConstructor;

/** 
 * Auth Controller: 
 * Expose endpoints for register and login security API.
 * 
 * @author  Carlos Surio
 * @version 1.0
 * @since   21/09/2023
*/
@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {
    
    @PostMapping(value = "login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> login(@RequestBody LoginRequest request)
    {
        return ResponseEntity.ok("authService.login(request)");
    }

    @PostMapping(value = "register", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> register(@RequestBody RegisterRequest request)
    {
        return ResponseEntity.ok("authService.register(request)");
    }

}
