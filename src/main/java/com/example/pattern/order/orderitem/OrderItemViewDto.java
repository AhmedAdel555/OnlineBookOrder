package com.example.pattern.order.orderitem;

import com.example.pattern.book.dtos.BookViewDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderItemViewDto {
    private Long id;
    private int quantity;
    private BookViewDto bookViewDtoDto;
}
