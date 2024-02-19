package com.springboot.financialplanning.service;

import com.springboot.financialplanning.security.dto.JWTAuthResponse;
import com.springboot.financialplanning.security.dto.LoginDto;
import com.springboot.financialplanning.security.dto.RegisterDto;

public interface AuthService {
	JWTAuthResponse login(LoginDto dto);
	String register(RegisterDto dto);
}
