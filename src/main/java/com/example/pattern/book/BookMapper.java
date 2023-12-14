package com.example.pattern.book;
import com.example.pattern.book.dtos.BookDto;
import com.example.pattern.book.dtos.BookViewDto;
import org.springframework.stereotype.Service;

@Service
public class BookMapper {
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
                .build();
    }
}
