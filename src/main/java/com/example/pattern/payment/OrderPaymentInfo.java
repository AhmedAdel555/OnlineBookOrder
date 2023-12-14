package com.example.pattern.payment;


import com.example.pattern.order.Order;
import com.example.pattern.payment.strategies.PaymentStarategy;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "orders_payment_info")
public class OrderPaymentInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private double totalPrice;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PaymentStarategy paymentStarategy;
    private String creditCardNumber;
    private LocalDate creditCardExpiry;
    private String creditCardCvv;
    @OneToOne
    @JoinColumn(name = "order_id", nullable=false)
    private Order order;
}
