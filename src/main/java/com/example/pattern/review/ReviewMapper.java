package com.example.pattern.review;

import com.example.pattern.user.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewMapper {
    private final UserMapper userMapper;

    @Autowired
    public ReviewMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public ReviewViewDto MapToReviewViewDto(Review review){
        return ReviewViewDto.builder()
                .id(review.getId())
                .userViewDto(userMapper.MapToUserViewDto(review.getUser()))
                .content(review.getContent())
                .build();
    }
}
