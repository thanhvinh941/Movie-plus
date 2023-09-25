package com.movieplus.config;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFacade implements IAuthenticationFacade<UserSession> {

    @Override
    public UserSession getAuthentication() {
        return (UserSession) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
