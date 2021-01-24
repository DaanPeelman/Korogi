package com.korogi.rest.security;

import static lombok.AccessLevel.PUBLIC;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import com.korogi.core.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

/**
 * @author Daan Peelman
 */
@Value
@AllArgsConstructor(access = PUBLIC)
@Builder(builderMethodName = "newUserPrincipal")
public class UserPrincipal implements OAuth2User, UserDetails {
    private String username;
    private String email;
    private boolean enabled;


    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return new HashMap<>();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
    }

    @Override
    public String getName() {
        return username;
    }

    public static UserPrincipal create(User user) {
        return newUserPrincipal()
                .username(user.getProviderId())
                .email(user.getEmail())
                .enabled(user.getActivated())
                .build();
    }

    public static UserPrincipal initialUserPrincipal(String username, String email) {
        return newUserPrincipal()
                .username(username)
                .email(email)
                .enabled(false)
                .build();
    }
}
