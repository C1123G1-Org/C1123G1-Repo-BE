package com.codegym.service;

import com.codegym.dto.DeleteContactInfoRequest;
import com.codegym.model.ContactInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IContactInfoService {
    Page<ContactInfo> findAll(Pageable pageable);
    ContactInfo addContactInfo(ContactInfo contactInfo);

    void deleteContactInfo(DeleteContactInfoRequest deleteContactInfoRequest);
}
