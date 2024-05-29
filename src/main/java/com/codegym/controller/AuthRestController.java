package com.codegym.controller;

import com.codegym.config.service.JwtResponse;
import com.codegym.config.service.JwtService;
import com.codegym.config.service.UserService;
import com.codegym.model.Account;
import com.codegym.repository.IAccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/auth")
@Slf4j
public class AuthRestController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private IAccountRepository IAccountRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Account user) {
        Authentication authentication
                = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtService.generateTokenLogin(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Account currentUser = IAccountRepository.findByUsername(user.getUsername());

        return ResponseEntity.ok(new JwtResponse(currentUser.getId(), jwt, userDetails.getUsername(), userDetails.getUsername(), userDetails.getAuthorities()));
    }

    @GetMapping("/getInfo")
    public ResponseEntity<?> getInfo(@RequestHeader("Authorization")String token) {
        String newToken = token.substring(7);
        String userName = jwtService.getUsernameFromJwtToken(newToken);
        System.out.println(userName);
        return ResponseEntity.ok(userName);
    }


    @GetMapping("/logout")
    public ResponseEntity<?> logoutSuccessful(){
        SecurityContextHolder.clearContext();
        return ResponseEntity.ok("ok dang xuat");
    }
}
