package com.example.pattern.book.dtos;

import com.example.pattern.book.BookStatus;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookDto {
    private Long id;
    @NotBlank(message = "title is required")
    private String title;
    @Min(value = 1, message = "Number of available units should be a positive number")
    private int numberOfAvailableUnits;
    @DecimalMin(value = "0.0", message = "Price should be a positive number")
    private double price;
    @NotBlank(message = "edition is required")
    private String edition;
    @NotBlank(message = "cover is required")
    private String coverUrl;
    @NotNull(message = "Status is required")
    private BookStatus bookStatus;
    @NotBlank(message = "category is required")
    private String categoryName;
}
