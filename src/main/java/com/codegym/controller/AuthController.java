package com.codegym.controller;

import com.codegym.payload.request.LoginRequest;
import com.codegym.payload.response.LoginResponse;
import com.codegym.secure.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Value("${app.jwtExpirationInMs}")
    private int jwtExpirationInMs;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                    loginRequest.getPassword()));

            SecurityContextHolder
                    .getContext()
                    .setAuthentication(authentication);

            String token = tokenProvider.generateToken(authentication);
            if (token == null) {
                jwtExpirationInMs = 0;
            }

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            return new ResponseEntity<>(new LoginResponse("Đăng nhập thành công!",
                    token,
                    jwtExpirationInMs,
                    userDetails.getAuthorities(),
                    userDetails.getUsername()),
                    HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new LoginResponse("Đăng nhập thất bại!",
                    null,
                    0,
                    null,
                    null),
                    HttpStatus.BAD_REQUEST);
        }
    }
}
