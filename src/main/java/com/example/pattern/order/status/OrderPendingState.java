package com.example.pattern.order.status;

import com.example.pattern.order.Order;

public class OrderPendingState implements IOrderState{

    private Order order;

    public OrderPendingState(Order order){
        this.order = order;
    }
    @Override
    public void Pending() {
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public void Confirm() {
        order.setStatus(OrderStatus.CONFIRMED);
    }

    @Override
    public void Cancel() {
        order.setStatus(OrderStatus.CANCELLED);
    }
}
