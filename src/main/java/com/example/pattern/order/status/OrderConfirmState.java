package com.example.pattern.order.status;

import com.example.pattern.order.Order;

public class OrderConfirmState implements IOrderState{

    private Order order;

    public OrderConfirmState(Order order) {
        this.order = order;
    }

    @Override
    public void Pending() {
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public void Confirm() {
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public void Cancel() {
        order.setStatus(OrderStatus.CANCELLED);
    }
}
