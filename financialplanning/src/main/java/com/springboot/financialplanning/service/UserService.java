package com.springboot.financialplanning.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.springboot.financialplanning.model.User;
import com.springboot.financialplanning.repository.UserRepository;

@Service
public class UserService  {

	@Autowired
	private UserRepository userRepository;

	public User insert(User user) {
		return userRepository.save(user);
	}

	public void save(User user) {
		// TODO Auto-generated method stub
		
	}

	

	
}
