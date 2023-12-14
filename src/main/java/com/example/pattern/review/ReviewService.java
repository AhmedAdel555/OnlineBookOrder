package com.example.pattern.review;

import com.example.pattern.book.Book;
import com.example.pattern.book.BookRepository;
import com.example.pattern.user.User;
import com.example.pattern.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewService {
    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;

    @Autowired
    public ReviewService(UserRepository userRepository, BookRepository bookRepository, ReviewRepository reviewRepository, ReviewMapper reviewMapper) {
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
        this.reviewRepository = reviewRepository;
        this.reviewMapper = reviewMapper;
    }

    public void addReview(Long userId, Long bookId, String content){
        User user = userRepository.findById(userId).orElseThrow();

        Book book = bookRepository.findById(bookId).orElseThrow();

        Review review = Review.builder()
                .user(user)
                .content(content)
                .book(book)
                .build();

        reviewRepository.save(review);

        user.getReviews().add(review);
        book.getReviews().add(review);

        userRepository.save(user);
        bookRepository.save(book);
    }

    public List<ReviewViewDto> getAllBookReview(Long bookId){
        List<Review> reviews = reviewRepository.findAllBookReviewsForBook(bookId);
        return reviews.stream().map((r) -> reviewMapper.MapToReviewViewDto(r)).collect(Collectors.toList());
    }
}
