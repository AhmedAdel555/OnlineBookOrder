package com.example.pattern.cart.cartitem;

import com.example.pattern.book.BookMapper;
import com.example.pattern.cart.CartMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class CartItemMapper{

    private final BookMapper bookMapper;

    @Autowired
    public CartItemMapper(BookMapper bookMapper) {
        this.bookMapper = bookMapper;
    }

    public CartItemViewDto MapToCartItemViewDto(CartItem cartItem) {
        return CartItemViewDto.builder()
                .id(cartItem.getId())
                .quantity(cartItem.getQuantity())
                .bookViewDtoDto(bookMapper.MapToBookViewDto(cartItem.getBook()))
                .build();
    }
}
