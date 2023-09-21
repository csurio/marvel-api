package com.siman.assestment.service;

import com.siman.assestment.controller.request.LoginRequest;
import com.siman.assestment.controller.request.RegisterRequest;
import com.siman.assestment.controller.response.AuthResponse;

public interface AuthService {
	public AuthResponse login(LoginRequest request);
	public AuthResponse register(RegisterRequest request);
}
