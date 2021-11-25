package com.capstone.danjinae.user.entity;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

public class AuthUser extends User {
    private static final long serialVersionUiD = 1L;

    public AuthUser(com.capstone.danjinae.user.entity.User user) {
        super(user.getPhone(), "{noop}" + user.getPassword(),
                AuthorityUtils.createAuthorityList(user.getRole().toString()));
    }
}