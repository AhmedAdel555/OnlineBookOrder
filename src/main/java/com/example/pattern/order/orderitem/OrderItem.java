package com.example.pattern.order.orderitem;

import com.example.pattern.book.Book;
import com.example.pattern.cart.Cart;
import com.example.pattern.order.Order;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "order_items")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private int quantity;
    @ManyToOne
    @JoinColumn(name = "book_id", nullable=false)
    private Book book;
    @ManyToOne
    @JoinColumn(name = "order_id", nullable=false)
    private Order order;
    @CreationTimestamp
    private LocalDateTime createdOn;
    @UpdateTimestamp
    private LocalDateTime updatedOn;
}
