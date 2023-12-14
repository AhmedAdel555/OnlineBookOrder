package com.example.pattern.payment.strategies;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CreditCardPayment implements IPaymentStrategy{
    private final String cardNumber;
    private final LocalDate expirationDate;
    private final String cvv;

    public CreditCardPayment(String cardNumber, LocalDate expirationDate, String cvv) {
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.cvv = cvv;
    }

    @Override
    public boolean processPayment(double amount) {
        if(LocalDate.now().isAfter(this.expirationDate) && this.cardNumber != null && this.cvv != null){
            System.out.println("successfully payment with");
            return true;
        }
        return false;
    }


}
