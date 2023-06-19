package com.se.thanhvinh.simple.domain.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserDetailsService {

	UserDetails loadUserByUsername(String username);

}
