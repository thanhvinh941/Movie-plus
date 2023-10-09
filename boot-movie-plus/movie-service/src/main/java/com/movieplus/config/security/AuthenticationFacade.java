package com.movieplus.config.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.movieplus.config.security.dto.UserSession;

@Component
public class AuthenticationFacade implements IAuthenticationFacade<UserSession> {

    @Override
    public UserSession getAuthentication() {
        return (UserSession) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
