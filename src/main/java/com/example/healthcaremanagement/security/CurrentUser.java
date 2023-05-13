package com.example.healthcaremanagement.security;
import com.example.healthcaremanagement.entity.UserEntity;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

public class CurrentUser extends User {

    private UserEntity user;

    public CurrentUser(UserEntity user) {
        super(user.getEmail(), user.getPassword(),
                AuthorityUtils.NO_AUTHORITIES);
        this.user = user;
    }

    public UserEntity getUser() {
        return user;
    }
}