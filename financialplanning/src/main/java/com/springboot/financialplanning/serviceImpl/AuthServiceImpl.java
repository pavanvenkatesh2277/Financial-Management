package com.springboot.financialplanning.serviceImpl;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.springboot.financialplanning.exception.BadRequestException;
import com.springboot.financialplanning.model.User;
import com.springboot.financialplanning.repository.UserRepository;
import com.springboot.financialplanning.security.JwtTokenProvider;
import com.springboot.financialplanning.security.dto.JWTAuthResponse;
import com.springboot.financialplanning.security.dto.LoginDto;
import com.springboot.financialplanning.security.dto.RegisterDto;
import com.springboot.financialplanning.security.dto.UserDto;
import com.springboot.financialplanning.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {
	private AuthenticationManager authenticationManager;
	private UserRepository userRepo;
	private PasswordEncoder passwordEncoder;
	private JwtTokenProvider jwtTokenProvider;

	@Autowired
	public AuthServiceImpl(AuthenticationManager authenticationManager, UserRepository userRepo,
			PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider) {
		this.authenticationManager = authenticationManager;
		this.userRepo = userRepo;
		this.passwordEncoder = passwordEncoder;
		this.jwtTokenProvider = jwtTokenProvider;
	}



	@Override
	public JWTAuthResponse login(LoginDto dto) {
	    Optional<User> userOptional = userRepo.findByUsernameOrEmail(dto.getUsername(), dto.getUsername());
	    if (userOptional.isPresent()) {
	        User user = userOptional.get();
	        // Verify password
	        if (passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
	            // Passwords match, generate token
	            Authentication authentication = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
	            SecurityContextHolder.getContext().setAuthentication(authentication);
	            String token = jwtTokenProvider.generateToken(authentication);

	            // Create UserDto
	            UserDto userDto = new UserDto();
	            userDto.setEmail(user.getEmail());
	            userDto.setUsername(user.getUsername());
	            userDto.setRole(user.getRole());

	            return new JWTAuthResponse(token, userDto);
	        } else {
	            // Passwords don't match
	            throw new BadCredentialsException("Invalid username or password");
	        }
	    } else {
	        // User not found
	        throw new UsernameNotFoundException("User not found with email or username: " + dto.getUsername());
	    }
	}


	@Override
	public String register(RegisterDto dto) {
		// Check if username already exists
		if (userRepo.existsByUsername(dto.getUsername())) {
			throw new BadRequestException(HttpStatus.BAD_REQUEST, "Username already exists");
		}

		// Check if email already exists
		if (userRepo.existsByEmail(dto.getEmail())) {
			throw new BadRequestException(HttpStatus.BAD_REQUEST, "Email already exists");
		}

		// Create a new user entity
		User user = new User();
		user.setEmail(dto.getEmail());
		user.setUsername(dto.getUsername());
		user.setPassword(passwordEncoder.encode(dto.getPassword()));

	
	    // Save the user entity to the database
	    userRepo.save(user);

	    return "Registration successful!";
	}


}