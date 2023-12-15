package com.example.pattern;

import com.example.pattern.book.BookService;
import com.example.pattern.book.dtos.BookViewDto;
import com.example.pattern.category.CategoryService;
import com.example.pattern.category.dtos.CategoryViewDto;
import com.example.pattern.order.ProxyOrderService;
import com.example.pattern.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MainController {
    private final BookService bookService;
    private final CategoryService categoryService;

    private final UserService userService;

    private final ProxyOrderService proxyOrderService;

    @Autowired
    public MainController(BookService bookService, CategoryService categoryService, UserService userService, ProxyOrderService proxyOrderService) {
        this.bookService = bookService;
        this.categoryService = categoryService;
        this.userService = userService;
        this.proxyOrderService = proxyOrderService;
    }

    @GetMapping("/")
    public String homePage(Model model){
        List<BookViewDto> books = bookService.getAllBooks();
        List<CategoryViewDto> categories = categoryService.getAllCategories();
        model.addAttribute("books", books);
        model.addAttribute("categories", categories);
        return "home";
    }

    @GetMapping("/dashboard")
    public String dashboardPage(Model model){
        int numberOfBooks = bookService.countBooks();
        int numberOfPendingOrders = proxyOrderService.countPendingOrders();
        int numberOfUsers = userService.countUsers();
        model.addAttribute("numberOfBooks", numberOfBooks);
        model.addAttribute("numberOfPendingOrders", numberOfPendingOrders);
        model.addAttribute("numberOfUsers", numberOfUsers);
        return "dashboard";
    }
}
