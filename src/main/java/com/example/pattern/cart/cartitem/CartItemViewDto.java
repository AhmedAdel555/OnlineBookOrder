package com.example.pattern.cart.cartitem;

import com.example.pattern.book.dtos.BookViewDto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CartItemViewDto {
    private Long id;
    private int quantity;
    private BookViewDto bookViewDtoDto;
}
