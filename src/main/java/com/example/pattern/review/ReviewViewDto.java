package com.example.pattern.review;
import com.example.pattern.user.UserViewDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewViewDto {
    private Long id;
    private String content;
    private UserViewDto userViewDto;
}
