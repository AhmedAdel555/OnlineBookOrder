package com.example.pattern.book;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.example.pattern.category.Category;
import com.example.pattern.cart.cartitem.CartItem;
import com.example.pattern.order.orderitem.OrderItem;
import com.example.pattern.review.Review;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private int numberOfAvailableUnits;
    @Column(nullable = false)
    private double price;
    @Column(nullable = false)
    private String edition;
    @Column(nullable = false)
    private String coverUrl;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private BookStatus bookStatus;
    @Column(nullable = false)
    private int popularity;
    @CreationTimestamp
    private LocalDateTime createdOn;
    @UpdateTimestamp
    private LocalDateTime updatedOn;
    @ManyToOne
    @JoinColumn(name="category_id", nullable=false)
    private Category category;
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> cartItems = new ArrayList<>();
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems = new ArrayList<>();
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews = new ArrayList<>();
}
