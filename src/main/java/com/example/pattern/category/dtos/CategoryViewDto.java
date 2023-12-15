package com.example.pattern.category.dtos;

import com.example.pattern.book.dtos.BookViewDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryViewDto {
    private Long id;
    private String name;
    private List<BookViewDto> bookViewDtos;
}
