package com.example.pattern.payment;

import com.example.pattern.payment.strategies.CashPayment;
import com.example.pattern.payment.strategies.CreditCardPayment;
import com.example.pattern.payment.strategies.IPaymentStrategy;
import com.example.pattern.payment.strategies.PaymentStarategy;

public class PaymentFactory {

    public IPaymentStrategy getPaymentStrategy(OrderPaymentInfo orderPaymentInfo){
        if(String.valueOf(orderPaymentInfo.getPaymentStarategy()).equalsIgnoreCase(String.valueOf(PaymentStarategy.CASH))){
            return new CashPayment();
        }
        else if (String.valueOf(orderPaymentInfo.getPaymentStarategy()).equalsIgnoreCase(String.valueOf(PaymentStarategy.CREDITCARD))){
                return new CreditCardPayment(orderPaymentInfo.getCreditCardNumber(), orderPaymentInfo.getCreditCardExpiry(), orderPaymentInfo.getCreditCardCvv());

        }
        return null;
    }
}
