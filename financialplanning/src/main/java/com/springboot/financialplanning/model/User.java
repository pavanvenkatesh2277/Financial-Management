package com.springboot.financialplanning.model;

import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.enums.Role;

@Entity

public class User implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)

	private int id;
	@Column(nullable = false, unique = true)

	private String username;

	@Column(nullable = false, unique = true)

	private String email;

	@Column(nullable = false)

	private String password;

	@Enumerated(EnumType.STRING)
	private Role role;



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
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



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public Role getRole() {
		return role;
	}



	public void setRole(Role role) {
		this.role = role;
	}




	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		//here we must convert user role in Authority
		 String userRole = role.name();
				SimpleGrantedAuthority sga=new SimpleGrantedAuthority(userRole);
				Collection<GrantedAuthority> list=new ArrayList<>();
				list.add(sga);
				return list;
	}



	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}


	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}


	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}


	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
    

}
