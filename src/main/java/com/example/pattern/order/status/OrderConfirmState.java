package com.example.pattern.order.status;

import com.example.pattern.order.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public class OrderConfirmState implements IOrderState{

    private final Order order;

    public OrderConfirmState(Order order) {
        this.order = order;
    }

    @Override
    public void Pending() {
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public void Confirm() {throw new UnsupportedOperationException("Method not implemented");}

    @Override
    public void Cancel() {
        throw new UnsupportedOperationException("Method not implemented");
    }
}
