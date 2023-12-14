package com.example.pattern.review;

import com.example.pattern.user.UserMapper;
import org.springframework.stereotype.Service;

@Service
public class ReviewMapper {
    private UserMapper userMapper;

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
