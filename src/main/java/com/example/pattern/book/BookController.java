package com.example.pattern.book;

import com.example.pattern.book.dtos.BookDto;
import com.example.pattern.book.dtos.BookViewDto;
import com.example.pattern.category.CategoryService;
import com.example.pattern.category.dtos.CategoryViewDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class BookController {
    private final BookService bookService;
    private final CategoryService categoryService;

    private final BookMapper bookMapper;

    @Autowired
    public BookController(BookService bookService, CategoryService categoryService, BookMapper bookMapper) {
        this.bookService = bookService;
        this.categoryService = categoryService;
        this.bookMapper = bookMapper;
    }

    @GetMapping("/books/categories/{categoryName}")
    public String getCategoryBooks(@PathVariable("categoryName") String categoryName, Model model){
        List<BookViewDto> books = bookService.getAllCategoryBooks(categoryName);
        List<CategoryViewDto> categories = categoryService.getAllCategories();
        model.addAttribute("books", books);
        model.addAttribute("categories", categories);
        return "home";
    }

    @GetMapping("/books/{bookId}")
    public String getBookDetails(@PathVariable("bookId") Long bookId, Model model){
        BookViewDto bookViewDto = bookService.getBookById(bookId);
        model.addAttribute("bookViewDto", bookViewDto);
        return "book-details";
    }

    @GetMapping("/add-book")
    public String addBookPage(Model model){
        BookDto bookDto = new BookDto();
        List<CategoryViewDto> categories = categoryService.getAllCategories();
        model.addAttribute("bookDto", bookDto);
        model.addAttribute("categories", categories);
        return "add-book";
    }

    @PostMapping("/books/add")
    public String addBook(@Valid @ModelAttribute("bookDto") BookDto bookDto, BindingResult result, RedirectAttributes redirectAttributes){
        if(result.hasErrors()){
            redirectAttributes.addFlashAttribute("failedmessage", "something being wrong");
            return "redirect:/add-book";
        }
        bookService.addBook(bookDto);
        return "redirect:/dashboard/books";
    }

    @GetMapping("/edit-book/{bookId}")
    public String editBookPage(@PathVariable("bookId") Long bookId, Model model){
        BookDto bookDto = bookMapper.MapToBookDto(bookService.getBookById(bookId));
        List<CategoryViewDto> categories = categoryService.getAllCategories();
        model.addAttribute("bookDto", bookDto);
        model.addAttribute("categories", categories);
        return "edit-book";
    }

    @PostMapping("/books/edit")
    public String editBook(@Valid @ModelAttribute("bookDto") BookDto bookDto, BindingResult result, RedirectAttributes redirectAttributes){
        System.out.println(bookDto);
        if(result.hasErrors()){
            redirectAttributes.addFlashAttribute("failedmessage", "something being wrong");
            return "redirect:/edit-book/"+bookDto.getId();
        }
        bookService.editBook(bookDto);
        return "redirect:/dashboard/books";
    }

    @PostMapping("/books/{bookId}/delete")
    public String deleteBook(@PathVariable("bookId") Long bookId){
        bookService.deleteBook(bookId);
        return "redirect:/dashboard/books";
    }

    @GetMapping("/dashboard/books")
    public String getBooksDashboard(Model model){
        List<BookViewDto> books = bookService.getAllBooks();
        model.addAttribute("books", books);
        return "books-table";
    }
}
