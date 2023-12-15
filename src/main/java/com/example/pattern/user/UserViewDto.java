package com.example.pattern.user;

import com.example.pattern.cart.CartViewDto;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserViewDto {
    private Long id;
    private String userName;
    private String address;
    private String phone;
    private Role role;
}
