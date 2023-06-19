package com.se.thanhvinh.simple.config;

import java.util.Collection;

import org.springframework.beans.BeanUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
@Data
public class UserPrincipal implements UserDetails {

	private static final long serialVersionUID = 1L;

	private String id;

	private String name;

	private String email;

	private String imageUrl;
	
	private Boolean emailVerified;
	
	@JsonIgnore
	private String password;

	public static UserPrincipal buid(UserDto user) {
		UserPrincipal userDetailsImpl = new UserPrincipal();
		BeanUtils.copyProperties(user, userDetailsImpl);
		
		return userDetailsImpl;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled() {
		return this.emailVerified;
	}

}
