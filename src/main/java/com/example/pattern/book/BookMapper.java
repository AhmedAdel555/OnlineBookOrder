package com.example.pattern.book;
import com.example.pattern.book.dtos.BookDto;
import com.example.pattern.book.dtos.BookViewDto;
import com.example.pattern.review.ReviewMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class BookMapper {
    private ReviewMapper reviewMapper;

    @Autowired
    public BookMapper(ReviewMapper reviewMapper) {
        this.reviewMapper = reviewMapper;
    }

    public BookDto MapToBookDto(Book book){
        return BookDto.builder()
                .id(book.getId())
                .title(book.getTitle())
                .numberOfAvailableUnits(book.getNumberOfAvailableUnits())
                .bookStatus(book.getBookStatus())
                .price(book.getPrice())
                .edition(book.getEdition())
                .coverUrl(book.getCoverUrl())
                .categoryName(book.getCategory().getName())
                .build();
    }

    public BookViewDto MapToBookViewDto(Book book){
        return BookViewDto.builder()
                .id(book.getId())
                .title(book.getTitle())
                .numberOfAvailableUnits(book.getNumberOfAvailableUnits())
                .bookStatus(book.getBookStatus())
                .price(book.getPrice())
                .edition(book.getEdition())
                .coverUrl(book.getCoverUrl())
                .popularity(book.getPopularity())
                .categoryName(book.getCategory().getName())
                .reviewViewDtos(book.getReviews().stream().map(r -> reviewMapper.MapToReviewViewDto(r)).collect(Collectors.toList()))
                .build();
    }

    public BookDto MapToBookDto(BookViewDto bookViewDto){
        return BookDto.builder()
                .id(bookViewDto.getId())
                .title(bookViewDto.getTitle())
                .numberOfAvailableUnits(bookViewDto.getNumberOfAvailableUnits())
                .bookStatus(bookViewDto.getBookStatus())
                .price(bookViewDto.getPrice())
                .edition(bookViewDto.getEdition())
                .coverUrl(bookViewDto.getCoverUrl())
                .categoryName(bookViewDto.getCategoryName())
                .build();
    }
}
