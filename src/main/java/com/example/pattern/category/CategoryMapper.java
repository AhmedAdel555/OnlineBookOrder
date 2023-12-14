package com.example.pattern.category;

import com.example.pattern.category.dtos.CategoryViewDto;
import com.example.pattern.book.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class CategoryMapper{

    private final BookMapper bookMapper;

    @Autowired
    public CategoryMapper(BookMapper bookMapper) {
        this.bookMapper = bookMapper;
    }

    public CategoryViewDto mapToCategoryViewDto(Category category) {
        return CategoryViewDto.builder()
                .id(category.getId())
                .name(category.getName())
                .bookViewDtos(category.getBooks().stream().map(bookMapper::MapToBookViewDto).collect(Collectors.toList()))
                .build();
    }
}
