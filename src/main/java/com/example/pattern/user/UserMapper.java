package com.example.pattern.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class UserMapper{

    @Autowired
    public UserMapper() {
    }

    public UserViewDto MapToUserViewDto(User user) {
        return UserViewDto.builder()
                .id(user.getId())
                .address(user.getAddress())
                .phone(user.getPhone())
                .role(user.getRole())
                .build();
    }
}
