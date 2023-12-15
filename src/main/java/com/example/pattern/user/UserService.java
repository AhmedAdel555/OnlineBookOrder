package com.example.pattern.user;

import com.example.pattern.auth.dtos.RegisterDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public int countUsers(){
        return userRepository.countUsers();
    }

    public void addUser(RegisterDto registerDto){
        User user = new User();
        user.setUserName(registerDto.getUserName());
        user.setAddress(registerDto.getAddress());
        user.setPassword(registerDto.getPassword());
        user.setPhone(registerDto.getPhone());
        userRepository.save(user);
    }
}
