package com.example.pattern.auth.dtos;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterDto {
    @NotBlank(message = "user name is required")
    private String userName;
    @NotBlank(message = "password is required")
    private String password;
    @NotBlank(message = "address is required")
    private String address;
    @NotBlank(message = "phone is required")
    private String phone;
}
