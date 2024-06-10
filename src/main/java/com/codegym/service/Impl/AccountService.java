package com.codegym.service.Impl;

import com.codegym.dto.AccountDto;
import com.codegym.model.Account;
import com.codegym.model.Cote;
import com.codegym.repository.IAccountRepository;
import com.codegym.repository.ICoteRepository;
import com.codegym.service.IAccountService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService implements IAccountService {
    @Autowired
    private IAccountRepository iAccountRepository;

    @Autowired
    private ICoteRepository iCoteRepository;

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
    public Page<Account> findAllPage(Pageable pageable, String name) {
        return iAccountRepository.findAllPage(pageable,"%" +name+"%");
    }

    @Override
    public AccountDto getCurrentAccount() {
        Object principal = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        UserDetails currentUser = (UserDetails) principal;
        AccountDto accountDto = new AccountDto();
        BeanUtils.copyProperties(iAccountRepository.findByUsername(currentUser.getUsername()),
                accountDto);
        return accountDto;
    }
    @Override
    public List<Cote> findCoteManagement(String username) {
        return iCoteRepository.findCoteManagementEmployee(username);
    }

}
