package com.codegym.repository;

import com.codegym.model.ContactInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IContactInfoRepository extends JpaRepository<ContactInfo, Integer> {
    public Page<ContactInfo> findAllByOrderByTimeDesc(Pageable pageable);
}
