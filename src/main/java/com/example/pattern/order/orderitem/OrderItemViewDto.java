package com.example.pattern.order.orderitem;

import com.example.pattern.book.dtos.BookViewDto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderItemViewDto {
    private Long id;
    private int quantity;
    private BookViewDto bookViewDtoDto;
}
