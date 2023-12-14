package com.example.pattern.category.dtos;

import com.example.pattern.book.dtos.BookViewDto;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CategoryViewDto {
    private Long id;
    private String name;
    private List<BookViewDto> bookViewDtos;
}
