package com.example.pattern.payment.strategies;

import org.springframework.stereotype.Service;

public class CashPayment implements IPaymentStrategy{
    @Override
    public boolean processPayment(double amount) {
        return true;
    }
}
