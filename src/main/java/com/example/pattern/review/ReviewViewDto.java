package com.example.pattern.review;
import com.example.pattern.user.UserViewDto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReviewViewDto {
    private Long id;
    private String content;
    private UserViewDto userViewDto;
}
