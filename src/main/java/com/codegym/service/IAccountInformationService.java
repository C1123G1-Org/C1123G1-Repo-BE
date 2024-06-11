package com.codegym.service;

import com.codegym.dto.account_information_DTO.AccountRequestDTO;
import com.codegym.dto.account_information_DTO.AccountResponseDTO;
import com.codegym.payload.request.PasswordRequest;
import com.codegym.payload.response.PasswordResponse;
import org.springframework.http.ResponseEntity;

public interface IAccountInformationService {
    ResponseEntity<AccountResponseDTO> getCurrentAccountInformation();

    ResponseEntity<AccountResponseDTO> editCurrentAccountInformation(AccountRequestDTO accountRequestDTO);

    ResponseEntity<PasswordResponse> editPassword(PasswordRequest passwordRequest);
}
