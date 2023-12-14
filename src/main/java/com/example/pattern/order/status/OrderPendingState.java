package com.example.pattern.order.status;

import com.example.pattern.order.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public class OrderPendingState implements IOrderState{

    private final Order order;

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
