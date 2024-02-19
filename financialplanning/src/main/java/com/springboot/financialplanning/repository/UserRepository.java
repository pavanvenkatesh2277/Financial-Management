package com.springboot.financialplanning.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.financialplanning.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	
	//User findByUsername(String username);
	
	Optional<User> findByUsername(String username);

    Boolean existsByEmail(String email);

    Optional<User> findByUsernameOrEmail(String username, String email);

    Optional<User> findByEmail(String email);
  	Boolean existsByUsername(String username);

}
