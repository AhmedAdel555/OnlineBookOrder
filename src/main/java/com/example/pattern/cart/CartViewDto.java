package com.example.pattern.cart;

import com.example.pattern.cart.cartitem.CartItemViewDto;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CartViewDto {
    private Long id;
    private List<CartItemViewDto> cartItemsDto;
}
