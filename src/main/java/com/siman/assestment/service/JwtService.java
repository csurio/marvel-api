package com.siman.assestment.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {

	public String getUsernameFromToken(String token);
	public boolean isTokenValid(String token, UserDetails userDetails);

}
