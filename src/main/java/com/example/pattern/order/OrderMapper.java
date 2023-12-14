package com.example.pattern.order;

import com.example.pattern.order.orderitem.OrderItemMapper;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class OrderMapper {
    private OrderItemMapper orderItemMapper;

    public OrderMapper(OrderItemMapper orderItemMapper) {
        this.orderItemMapper = orderItemMapper;
    }

    public OrderViewDto MapToOrderViewDto(Order order){
        return OrderViewDto.builder()
                .id(order.getId())
                .totalPrice(order.getTotalPrice())
                .status(order.getStatus())
                .orderPaymentInfo(order.getOrderPaymentInfo().getPaymentStarategy())
                .orderItemViewDtos(order.getOrderItems().stream().map((o) -> orderItemMapper.MapToOrderItemViewDto(o)).collect(Collectors.toList()))
                .build();
    }
}
