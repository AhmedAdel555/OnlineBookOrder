package com.example.pattern.book;
import com.example.pattern.category.Category;
import com.example.pattern.category.CategoryRepository;
import com.example.pattern.book.dtos.BookDto;
import com.example.pattern.book.dtos.BookViewDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;
    private final BookMapper bookMapper;

    @Autowired
    public BookService(BookRepository bookRepository, CategoryRepository categoryRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.categoryRepository = categoryRepository;
        this.bookMapper = bookMapper;
    }

    public void addBook(BookDto bookDto){
        Category category = categoryRepository.findCategoryByName(bookDto.getCategoryName()).orElseThrow();
        Book book = Book.builder()
                .id(bookDto.getId())
                .title(bookDto.getTitle())
                .numberOfAvailableUnits(bookDto.getNumberOfAvailableUnits())
                .bookStatus(bookDto.getBookStatus())
                .price(bookDto.getPrice())
                .edition(bookDto.getEdition())
                .coverUrl(bookDto.getCoverUrl())
                .category(category)
                .build();
        bookRepository.save(book);
    }

    public void editBook(BookDto bookDto){
        Book book = bookRepository.findById(bookDto.getId()).orElseThrow();
        Category category = categoryRepository.findCategoryByName(bookDto.getCategoryName().strip()).orElseThrow();
        book.setTitle(bookDto.getTitle());
        book.setNumberOfAvailableUnits(bookDto.getNumberOfAvailableUnits());
        book.setPrice(bookDto.getPrice());
        book.setEdition(bookDto.getEdition());
        book.setCoverUrl(bookDto.getCoverUrl());
        book.setBookStatus(bookDto.getBookStatus());
        book.setCategory(category);
        bookRepository.save(book);
    }

    public void deleteBook(Long bookId){
        boolean exist = bookRepository.existsById(bookId);
        if(!exist){
            throw new NoSuchElementException();
        }
        bookRepository.deleteById(bookId);
    }

    public List<BookViewDto> getAllBooks(){
        List<Book> books = bookRepository.findAllBooksSortedByPopularity();
        return books.stream().map((b) -> bookMapper.MapToBookViewDto(b)).collect(Collectors.toList());
    }

    public List<BookViewDto> getAllCategoryBooks(String categoryName){
        List<Book> books = bookRepository.findAllByCategoryName(categoryName);
        return books.stream().map((b) -> bookMapper.MapToBookViewDto(b)).collect(Collectors.toList());
    }

    public BookViewDto getBookById(Long bookId){
        Book book = bookRepository.findById(bookId).orElseThrow();
        return bookMapper.MapToBookViewDto(book);
    }

    public int countBooks(){
        return bookRepository.countBooks();
    }
}
