package com.codegym.payload.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PasswordRequest {
    private String oldPassword;
    private String newPassword;
    private String confirmPassword;
}
