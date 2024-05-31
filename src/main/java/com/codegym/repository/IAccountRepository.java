package com.codegym.repository;

import com.codegym.model.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IAccountRepository extends JpaRepository<Account, Integer> {

    @Query(value = " select * from account ", nativeQuery = true)
    Page<Account> findAllPage(Pageable pageable);

    @Query(value = "select * from account WHERE username =:username", nativeQuery = true)
    List<Account> findByName(@Param("username") String name);


}
