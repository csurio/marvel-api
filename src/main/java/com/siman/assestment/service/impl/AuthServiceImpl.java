package com.siman.assestment.service.impl;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.siman.assestment.common.enums.Role;
import com.siman.assestment.controller.request.LoginRequest;
import com.siman.assestment.controller.request.RegisterRequest;
import com.siman.assestment.controller.response.AuthResponse;
import com.siman.assestment.repository.UserRepository;
import com.siman.assestment.repository.entity.User;
import com.siman.assestment.service.AuthService;
import com.siman.assestment.service.JwtService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
	
	private final UserRepository        userRepository;
	private final JwtService            jwtService;
	private final PasswordEncoder       passwordEncoder;
    private final AuthenticationManager authenticationManager;

	@Override
	public AuthResponse login(LoginRequest request) {
		UsernamePasswordAuthenticationToken UserPasswordAuthToken = 
				new UsernamePasswordAuthenticationToken(
						request.getUsername(), 
						request.getPassword());
		
		authenticationManager.authenticate(UserPasswordAuthToken);
		UserDetails user = userRepository.findByUsername(request.getUsername()).orElseThrow();
		String token=jwtService.getToken(user);
		
        return AuthResponse.builder()
            .token(token)
            .build();
	}

	@Override
	public AuthResponse register(RegisterRequest request) {
		
		User user = User.builder()
				.username(request.getUsername())
				.firstname(request.getFirstname())
				.lastname(request.getLastname())
				.password(passwordEncoder.encode( request.getPassword()))
				.email(request.getEmail())
				.phone(request.getPhone())
				.role(Role.USER)
				.build();
		userRepository.save(user);
		
		return AuthResponse.builder()
				.token(jwtService.getToken(user))
				.build();
	}

}
