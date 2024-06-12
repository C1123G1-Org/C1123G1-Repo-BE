package com.codegym.service.Impl;

import com.codegym.dto.AccountDto;
import com.codegym.dto.account_information_DTO.AccountRequestDTO;
import com.codegym.dto.account_information_DTO.AccountResponseDTO;
import com.codegym.model.Account;
import com.codegym.payload.request.PasswordRequest;
import com.codegym.payload.response.PasswordResponse;
import com.codegym.repository.IAccountRepository;
import com.codegym.service.IAccountInformationService;
import com.codegym.service.IAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountInformationService implements IAccountInformationService {
    private final IAccountService accountService;

    private final IAccountRepository accountRepository;

    private final PasswordEncoder bCryptPasswordEncoder;

    @Override
    public ResponseEntity<AccountResponseDTO> getCurrentAccountInformation() {
        AccountDto accountDto = accountService.getCurrentAccount();
        AccountResponseDTO accountResponseDTO = new AccountResponseDTO();
        BeanUtils.copyProperties(accountDto,
                accountResponseDTO);
        return new ResponseEntity<>(accountResponseDTO,
                HttpStatus.OK);
    }

    @Override
    public ResponseEntity<AccountResponseDTO> editCurrentAccountInformation(AccountRequestDTO newInformation) {
        AccountDto accountDto = accountService.getCurrentAccount();
        Account currentAccount = accountRepository.findByUsername(accountDto.getUsername());
        BeanUtils.copyProperties(newInformation,
                currentAccount);
        accountRepository.save(currentAccount);
        AccountResponseDTO accountResponseDTO = new AccountResponseDTO();
        BeanUtils.copyProperties(currentAccount,
                accountResponseDTO);
        return new ResponseEntity<>(accountResponseDTO,
                HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PasswordResponse> editPassword(PasswordRequest passwordRequest) {
        AccountDto accountDto = accountService.getCurrentAccount();
        if (bCryptPasswordEncoder.matches(passwordRequest.getOldPassword(),
                accountDto.getPassword())) {
            if (passwordRequest
                    .getNewPassword()
                    .equals(passwordRequest.getConfirmPassword())) {
                Account editedAccount = accountRepository.findByUsername(accountDto.getUsername());
                editedAccount.setPassword(bCryptPasswordEncoder.encode(passwordRequest.getNewPassword()));
                accountRepository.save(editedAccount);
                return new ResponseEntity<>(new PasswordResponse("Mật khẩu đã được thay đổi!"),
                        HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new PasswordResponse("Xác nhận mật khẩu mới không trùng khớp!"),
                        HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>(new PasswordResponse("Mật khẩu cũ không đúng!"),
                    HttpStatus.BAD_REQUEST);
        }
    }
}
