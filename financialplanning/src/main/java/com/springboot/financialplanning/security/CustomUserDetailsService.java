package com.springboot.financialplanning.security;



import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.springboot.financialplanning.model.User;
import com.springboot.financialplanning.repository.UserRepository;



@Service

public class CustomUserDetailsService implements UserDetailsService {
 
	private UserRepository userRepository;

	// Constructor DI
 
	public CustomUserDetailsService(UserRepository userRepository) {

		super();

		this.userRepository = userRepository;

	}
 
	@Override
	public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
	    User user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
	            .orElseThrow(() -> new UsernameNotFoundException("User not found by Username or Email"));
//
//	    // Handle null roles gracefully
//	    String roles = user.getRole();
//	    Set<GrantedAuthority> authorities = new HashSet<>();
//	    if (roles != null) {
//	        // Split roles string and map to GrantedAuthority
//	        authorities = Arrays.stream(roles.split(","))
//	                .map(SimpleGrantedAuthority::new)
//	                .collect(Collectors.toSet());
//	    }
//
	    return user;
//	    return new org.springframework.security.core.userdetails.User(
//	            usernameOrEmail,
//	            user.getPassword()
////	            authorities
//, null
//	    );
	}



}