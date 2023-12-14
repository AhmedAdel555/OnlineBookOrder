package com.example.pattern.payment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderPaymentInfoRepository extends JpaRepository<OrderPaymentInfo, Long> {
}
