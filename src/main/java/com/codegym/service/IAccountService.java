package com.codegym.service;

import com.codegym.dto.StaffDto;
import com.codegym.model.Account;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IAccountService {
    List<Account> findAll();


    void save(Account account);

    void updateS(Account account);

    Account findById(Integer id);

    void delete(Account account);

    Page<Account> getAllPage(StaffDto staffDto);
}
