package com.codegym.service;

import com.codegym.dto.AccountDto;
import com.codegym.model.Account;
import com.codegym.model.Cote;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IAccountService {
    List<Account> findAll();

    void save(Account account);

    void updateS(Account account);

    Account findById(Integer id);

    void delete(Account account);

    Page<Account> findAllPage(Pageable pageable,
                              String name);

    AccountDto getCurrentAccount();

//    List<Cote> findCoteManagement(Integer id);

    void findByDetail(Account account);

    List<Cote> findAllCote();
}
