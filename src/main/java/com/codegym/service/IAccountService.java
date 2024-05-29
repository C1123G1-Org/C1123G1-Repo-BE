package com.codegym.service;

import com.codegym.model.Account;

import java.util.List;

public interface IAccountService {
    List<Account> findAll();


    void save(Account account);

    void updateS(Account account);

    Account findById(Integer id);

    void delete(Account account);

}
