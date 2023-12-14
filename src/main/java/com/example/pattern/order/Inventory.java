package com.example.pattern.order;

import com.example.pattern.book.Book;
import com.example.pattern.book.BookRepository;
import com.example.pattern.cart.Cart;
import com.example.pattern.cart.cartitem.CartItem;
import com.example.pattern.user.User;
import com.example.pattern.user.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Inventory {

    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    public Inventory(UserRepository userRepository, BookRepository bookRepository) {
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    public double calculateTotalAmount(Cart cart){
        double totalAmount = 0.0;
        List<CartItem> cartItems = cart.getCartItems();
        for (CartItem cartItem : cartItems) {
            Book book = cartItem.getBook();
            int quantity = cartItem.getQuantity();
            double itemTotal = book.getPrice() * quantity;
            totalAmount += itemTotal;
        }
        return totalAmount;
    }

    public boolean CheckInventory(Long userId){
        User user =  userRepository.findById(userId).orElseThrow();
        Cart cart = user.getCart();
        List<CartItem> cartItems = cart.getCartItems();
        for (CartItem cartItem : cartItems) {
            Book book = cartItem.getBook();
            int requestedQuantity = cartItem.getQuantity();

            if (book.getNumberOfAvailableUnits() < requestedQuantity) {
                return false;
            }

            book.setNumberOfAvailableUnits(book.getNumberOfAvailableUnits() - requestedQuantity);
            bookRepository.save(book);
        }
        return true;
    }
}
