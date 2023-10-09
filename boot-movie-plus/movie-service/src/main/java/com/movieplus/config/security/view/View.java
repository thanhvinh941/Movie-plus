package com.movieplus.config.security.view;

import java.util.HashMap;
import java.util.Map;

import com.movieplus.config.security.MultiSecurityConfig;
import com.movieplus.config.security.MultiSecurityConfig.Role;

public class View {
	public static final Map<Role, Class<?>> MAPPING = new HashMap<>();

    static {
        MAPPING.put(Role.ROLE_ADMIN, Admin.class);
        MAPPING.put(Role.ROLE_USER, User.class);
    }

    public static class User {

    }

    public static class Admin extends User {

    }
}
