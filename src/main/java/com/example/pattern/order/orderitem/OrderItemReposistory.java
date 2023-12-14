package com.example.pattern.order.orderitem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemReposistory extends JpaRepository<OrderItem, Long> {
}
