package com.example.pattern.cart;

import com.example.pattern.cart.cartitem.CartItemMapper;
import com.example.pattern.user.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class CartMapper{

    private final CartItemMapper cartItemMapper;

    @Autowired
    public CartMapper(CartItemMapper cartItemMapper) {
        this.cartItemMapper = cartItemMapper;
    }

    public CartViewDto MapToCartViewDto(Cart cart) {
        return CartViewDto.builder()
                .id(cart.getId())
                .cartItemsDto(cart.getCartItems().stream().map((cItem) -> cartItemMapper.MapToCartItemViewDto(cItem)).collect(Collectors.toList()))
                .build();
    }
}
