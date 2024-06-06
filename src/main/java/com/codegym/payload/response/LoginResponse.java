package com.codegym.payload.response;

import jakarta.validation.constraints.NotBlank;
import org.springframework.lang.Nullable;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class LoginResponse {

    private final Collection<? extends GrantedAuthority> authorities;
    @NotBlank
    private String message;
    @Nullable
    private String token;
    @Nullable
    private int expiresIn;


    public LoginResponse(@NotBlank String message,
                         String token,
                         int expiresIn,
                         Collection<? extends GrantedAuthority> authorities) {
        super();
        this.message = message;
        this.token = token;
        this.expiresIn = expiresIn;
        this.authorities = authorities;
    }

    public LoginResponse(Collection<? extends GrantedAuthority> authorities) {
        super();
        this.authorities = authorities;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
}
