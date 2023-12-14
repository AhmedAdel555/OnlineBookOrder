package com.example.pattern.order.status;

import com.example.pattern.order.Order;
import jdk.jshell.spi.ExecutionControl;

public class OrderCancelState implements IOrderState{
    private Order order;

    public OrderCancelState(Order order) {
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
        throw new UnsupportedOperationException("Method not implemented");
    }
}
