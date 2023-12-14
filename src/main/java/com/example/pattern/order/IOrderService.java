package com.example.pattern.order;

import com.example.pattern.payment.strategies.PaymentStarategy;

import java.util.List;

public interface IOrderService {
    void craeteOrder(Long userId, PaymentStarategy paymentStarategy, String creditCardNumber, String creditCardExpiry, String creditCardCvv);
    void confirmOrder(Long orderId);
    void cancelOrder(Long orderId);
    List<OrderViewDto> getAllOrders();
    List<OrderViewDto> getUserOrders(Long userId);
}
