package com.codegym.repository;

import com.codegym.model.Account;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface IAccountRepository extends JpaRepository<Account, Integer> {
    @Modifying
    @Transactional
    @Query(value = "UPDATE account SET `code` = :#{#account.code}, `email` = :#{#account.email}, " +
            "`full_name` = :#{#account.fullName}, `gender` = :#{#account.gender}, `identity_code` =:#{#account.identityCode}, " +
            "`password` = :#{#account.password}, " +
            "`status` = :#{#account.status}, " +
            "`username` = :#{#account.username} WHERE (`id` = :#{#account.id});", nativeQuery = true)
    void update(Account account);
}
