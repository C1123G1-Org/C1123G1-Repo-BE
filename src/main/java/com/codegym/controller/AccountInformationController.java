package com.codegym.controller;

import com.codegym.dto.account_information_DTO.AccountRequestDTO;
import com.codegym.dto.account_information_DTO.AccountResponseDTO;
import com.codegym.payload.request.PasswordRequest;
import com.codegym.payload.response.PasswordResponse;
import com.codegym.service.IAccountInformationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/account-information")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:3000")
public class AccountInformationController {

    private final IAccountInformationService accountInformationService;

    @GetMapping
    public ResponseEntity<AccountResponseDTO> getAccountInformation() {
        return accountInformationService.getCurrentAccountInformation();
    }

    @PutMapping
    public ResponseEntity<AccountResponseDTO> editAccountInformation(@RequestBody AccountRequestDTO updatedInformation) {
        return accountInformationService.editCurrentAccountInformation(updatedInformation);
    }

    @PutMapping("/password")
    public ResponseEntity<PasswordResponse> editPassword(@RequestBody PasswordRequest passwordRequest) {
        return accountInformationService.editPassword(passwordRequest);
    }

}
