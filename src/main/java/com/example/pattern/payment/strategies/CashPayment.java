package com.example.pattern.payment.strategies;

public class CashPayment implements IPaymentStrategy{
    @Override
    public boolean processPayment(double amount) {
        return true;
    }
}
