package com.codegym.service.Impl;

import com.codegym.model.Account;
import com.codegym.repository.IAccountRepository;
import com.codegym.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService implements IAccountService {
    @Autowired
    private IAccountRepository iAccountRepository;


    @Override
    public List<Account> findAll() {
        return iAccountRepository.findAll();
    }

    @Override
    public void save(Account account) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        iAccountRepository.save(account);
    }

    @Override
    public void updateS(Account account) {
        iAccountRepository.update(account);
    }

    @Override
    public Account findById(Integer id) {
        return iAccountRepository.findById(id).get();
    }

    @Override
    public void delete(Account account) {
        iAccountRepository.delete(account);
    }

}
