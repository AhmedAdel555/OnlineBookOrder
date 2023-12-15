package com.example.pattern.user;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class UserMapper{

    public UserViewDto MapToUserViewDto(User user) {
        return UserViewDto.builder()
                .id(user.getId())
                .userName(user.getUserName())
                .address(user.getAddress())
                .phone(user.getPhone())
                .role(user.getRole())
                .build();
    }
}
