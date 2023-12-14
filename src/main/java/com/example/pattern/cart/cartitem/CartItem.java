package com.example.pattern.cart.cartitem;

import com.example.pattern.book.Book;
import com.example.pattern.cart.Cart;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "cart_items")
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private int quantity;
    @ManyToOne
    @JoinColumn(name = "book_id", nullable=false)
    private Book book;
    @ManyToOne
    @JoinColumn(name = "cart_id", nullable=false)
    private Cart cart;
}
