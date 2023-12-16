package com.example.pattern.order;

import com.example.pattern.book.Book;
import com.example.pattern.book.BookRepository;
import com.example.pattern.cart.Cart;
import com.example.pattern.cart.CartRepository;
import com.example.pattern.cart.cartitem.CartItem;
import com.example.pattern.user.User;
import com.example.pattern.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Inventory {

    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    private final CartRepository cartRepository;

    @Autowired
    public Inventory(UserRepository userRepository, BookRepository bookRepository, CartRepository cartRepository) {
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
        this.cartRepository = cartRepository;
    }

    public double calculateTotalAmount(Long userId){
        Optional<Cart> cart = cartRepository.findByUserId(userId);
        if(!cart.isPresent()){
            return 0;
        }
        double totalAmount = 0.0;
        List<CartItem> cartItems = cart.get().getCartItems();
        for (CartItem cartItem : cartItems) {
            Book book = cartItem.getBook();
            int quantity = cartItem.getQuantity();
            double itemTotal = book.getPrice() * quantity;
            totalAmount += itemTotal;
        }
        return totalAmount;
    }

    public boolean CheckInventory(Long userId){
        Optional<Cart> cart = cartRepository.findByUserId(userId);
        if(!cart.isPresent()){
            return false;
        }
        List<CartItem> cartItems = cart.get().getCartItems();
        for (CartItem cartItem : cartItems) {
            Book book = cartItem.getBook();
            int requestedQuantity = cartItem.getQuantity();

            if (book.getNumberOfAvailableUnits() < requestedQuantity) {
                return false;
            }

            book.setNumberOfAvailableUnits(book.getNumberOfAvailableUnits() - requestedQuantity);
            book.setPopularity(book.getPopularity() + + requestedQuantity);
            bookRepository.save(book);
        }
        return true;
    }
}
