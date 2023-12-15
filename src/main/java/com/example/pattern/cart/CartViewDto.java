package com.example.pattern.cart;

import com.example.pattern.cart.cartitem.CartItemViewDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartViewDto {
    private Long id;
    private List<CartItemViewDto> cartItemsDto;
}
