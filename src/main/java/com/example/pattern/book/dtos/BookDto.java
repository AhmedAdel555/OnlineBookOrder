package com.example.pattern.book.dtos;

import com.example.pattern.book.BookStatus;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookDto {
    private Long id;
    @NotBlank(message = "title is required")
    private String title;
    @NotBlank(message = "number of available units is required")
    private int numberOfAvailableUnits;
    @NotBlank(message = "price is required")
    private double price;
    @NotBlank(message = "edition is required")
    private String edition;
    @NotBlank(message = "cover is required")
    private String coverUrl;
    @NotBlank(message = "status is required")
    private BookStatus bookStatus;
    @NotBlank(message = "category is required")
    private String categoryName;

}
