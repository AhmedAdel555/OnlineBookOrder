package com.example.pattern.payment;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderPaymentInfoRepository extends JpaRepository<OrderPaymentInfo, Long> {
}
