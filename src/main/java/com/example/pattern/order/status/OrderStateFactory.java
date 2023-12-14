package com.example.pattern.order.status;

import com.example.pattern.order.Order;

public class OrderStateFactory {
    public IOrderState getOrderState(Order order){
        if(order.getStatus().equals(OrderStatus.CANCELLED)){
            return new OrderCancelState(order);
        }
        else if(order.getStatus().equals(OrderStatus.CONFIRMED)){
            return new OrderConfirmState(order);
        }
        return new OrderPendingState(order);
    }
}
