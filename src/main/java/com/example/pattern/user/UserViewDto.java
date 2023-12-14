package com.example.pattern.user;

import com.example.pattern.cart.CartViewDto;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserViewDto {
    private Long id;
    private String address;
    private String phone;
    private Role role;
}
