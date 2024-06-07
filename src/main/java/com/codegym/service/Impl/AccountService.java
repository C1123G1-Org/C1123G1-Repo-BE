package com.codegym.service.Impl;

import com.codegym.dto.StaffDto;
import com.codegym.model.Account;
import com.codegym.repository.IAccountRepository;
import com.codegym.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
        iAccountRepository.save(account);
    }

    @Override
    public void updateS(Account account) {
        iAccountRepository.save(account);
    }

    @Override
    public Account findById(Integer id) {
        return iAccountRepository.findById(id).get();
    }

    @Override
    public void delete(Account account) {
        iAccountRepository.delete(account);
    }

    @Override
    public Page<Account> findAllPage(Pageable pageable,String name) {
        return iAccountRepository.findAllPage(pageable,"%" +name+"%");
    }







}
