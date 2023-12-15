package com.example.pattern.book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("SELECT b FROM Book b ORDER BY b.popularity DESC")
    List<Book> findAllBooksSortedByPopularity();

    @Query("SELECT b FROM Book b WHERE b.category.name = :categoryName")
    List<Book> findAllByCategoryName(String categoryName);

    @Query("SELECT COUNT(b) FROM Book b")
    int countBooks();
}
