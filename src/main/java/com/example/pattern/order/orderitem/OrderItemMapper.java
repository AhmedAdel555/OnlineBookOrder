package com.example.pattern.order.orderitem;
import com.example.pattern.book.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderItemMapper {
    private final BookMapper bookMapper;

    @Autowired
    public OrderItemMapper(BookMapper bookMapper) {
        this.bookMapper = bookMapper;
    }

    public OrderItemViewDto MapToOrderItemViewDto(OrderItem orderItem) {
        return OrderItemViewDto.builder()
                .id(orderItem.getId())
                .quantity(orderItem.getQuantity())
                .bookViewDtoDto(bookMapper.MapToBookViewDto(orderItem.getBook()))
                .build();
    }
}
