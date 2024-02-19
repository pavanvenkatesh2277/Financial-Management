package com.springboot.financialplanning.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.financialplanning.security.dto.JWTAuthResponse;
import com.springboot.financialplanning.security.dto.LoginDto;
import com.springboot.financialplanning.security.dto.RegisterDto;
import com.springboot.financialplanning.service.AuthService;


@RestController
public class AuthController {

	
	@Autowired
	private AuthService authService;
	

	@PostMapping(value= {"/login","signin"})
	public ResponseEntity<JWTAuthResponse> login (@RequestBody LoginDto dto){
		
		JWTAuthResponse token=authService.login(dto);
		
		
		return ResponseEntity.ok(token);
		
	}
	

	@PostMapping(value= {"/signup","register"})
	public ResponseEntity<String> register (@RequestBody RegisterDto dto){
		
		String token=authService.register(dto);
		
		
		return ResponseEntity.ok(token);
		
	}
}
