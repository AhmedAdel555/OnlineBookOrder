package com.example.pattern.review;

import com.example.pattern.book.Book;
import com.example.pattern.user.User;
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
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String content;
    @ManyToOne
    @JoinColumn(name = "book_id", nullable=false)
    private Book book;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
