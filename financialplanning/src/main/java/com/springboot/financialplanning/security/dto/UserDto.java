package com.springboot.financialplanning.security.dto;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.enums.Role;

public class UserDto {
	
	
	private String username;
	private String email;
	
	@Enumerated(EnumType.STRING)
	private Role role;
	//private String role;
	
	public UserDto() {
		super();
		// TODO Auto-generated constructor stub
	}
//	public UserDto(String username, String email, String role) {
//		super();
//		this.username = username;
//		this.email = email;
//		this.role = role;
//	}
//	public String getUsername() {
//		return username;
//	}
//	public void setUsername(String username) {
//		this.username = username;
//	}
//	public String getEmail() {
//		return email;
//	}
//	public void setEmail(String email) {
//		this.email = email;
//	}
//	public String getRole() {
//		return role;
//	}
//	public void setRole(String role) {
//		this.role = role;
//	}
	public UserDto(String username, String email, Role role) {
		super();
		this.username = username;
		this.email = email;
		this.role = role;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	
	
}