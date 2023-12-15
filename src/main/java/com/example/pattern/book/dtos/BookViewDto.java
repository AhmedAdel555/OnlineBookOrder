package com.example.pattern.book.dtos;

import com.example.pattern.book.BookStatus;
import com.example.pattern.review.Review;
import com.example.pattern.review.ReviewViewDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookViewDto {
    private Long id;
    private String title;
    private int numberOfAvailableUnits;
    private double price;
    private String edition;
    private String coverUrl;
    private BookStatus bookStatus;
    private int popularity;
    private String categoryName;
    private List<ReviewViewDto> reviewViewDtos = new ArrayList<>();
}
