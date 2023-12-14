package com.example.pattern.order.status;

public interface IOrderState {
    void Pending();
    void Confirm();
    void Cancel();
}
