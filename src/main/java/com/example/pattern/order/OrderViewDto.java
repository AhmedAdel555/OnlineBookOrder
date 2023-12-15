package com.example.pattern.order;

import com.example.pattern.order.orderitem.OrderItemViewDto;
import com.example.pattern.order.status.OrderStatus;
import com.example.pattern.payment.strategies.PaymentStarategy;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderViewDto {
    private Long id;
    private double totalPrice;
    private OrderStatus status;
    private PaymentStarategy orderPaymentInfo;
    private List<OrderItemViewDto> orderItemViewDtos;
}
