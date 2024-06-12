package com.codegym.service.Impl;

import com.codegym.dto.DeleteContactInfoRequest;
import com.codegym.model.ContactInfo;
import com.codegym.repository.IContactInfoRepository;
import com.codegym.service.IContactInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ContactInfoService implements IContactInfoService {
    @Autowired
    private IContactInfoRepository iContactInfoRepository;

    @Override
    public Page<ContactInfo> findAll(Pageable pageable) {
        return iContactInfoRepository.findAllByOrderByTimeDesc(pageable);
    }

    @Override
    public ContactInfo addContactInfo(ContactInfo contactInfo) {

        return iContactInfoRepository.save(contactInfo);
    }

    @Override
    public void deleteContactInfo(DeleteContactInfoRequest deleteContactInfoRequest) {
        for(int id : deleteContactInfoRequest.getIdList()){
            iContactInfoRepository.deleteById(id);
        }
    }


}
