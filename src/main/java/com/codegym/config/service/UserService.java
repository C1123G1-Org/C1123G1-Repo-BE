package com.codegym.config.service;

import com.codegym.config.UserPrinciple;
import com.codegym.model.Account;
import com.codegym.repository.IAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private IAccountRepository accountRepository;

    public Account findByUsername(String name) {
        return accountRepository.findByUsername(name);
    }

    public UserDetails loadUserByUsername(String username) {
        return UserPrinciple.build(accountRepository.findByUsername(username));
    }
}
