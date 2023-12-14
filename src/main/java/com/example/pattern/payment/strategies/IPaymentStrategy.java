package com.example.pattern.payment.strategies;

public interface IPaymentStrategy {
    boolean processPayment(double amount);
}
