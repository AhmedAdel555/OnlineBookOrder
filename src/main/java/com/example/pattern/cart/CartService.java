package com.example.pattern.cart;

import com.example.pattern.book.Book;
import com.example.pattern.book.BookRepository;
import com.example.pattern.cart.cartitem.CartItem;
import com.example.pattern.user.User;
import com.example.pattern.user.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartService {
    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final CartMapper cartMapper;

    @Autowired
    public CartService(CartRepository cartRepository, UserRepository userRepository, BookRepository bookRepository, CartMapper cartMapper) {
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
        this.cartMapper = cartMapper;
    }


    public void AddToCart(Long userId, Long bookId, int quantity){

        User user = userRepository.findById(userId).orElseThrow();
        Book book = bookRepository.findById(bookId).orElseThrow();

        Optional<Cart> cartFromDB = cartRepository.findByUserId(userId);
        Cart cart = null;
        if (!cartFromDB.isPresent()) {
            cart = new Cart();
            cart.setUser(user);
        }
        else{
            cart = cartFromDB.get();
        }

        CartItem existingCartItem = cart.getCartItems().stream()
                .filter(item -> item.getBook().getId().equals(book.getId()))
                .findFirst()
                .orElse(null);

        if (existingCartItem != null) {
            existingCartItem.setQuantity(quantity + existingCartItem.getQuantity());
        }
        else {
            CartItem newCartItem = new CartItem();
            newCartItem.setBook(book);
            newCartItem.setQuantity(quantity);
            cart.getCartItems().add(newCartItem);
            newCartItem.setCart(cart);
        }
        // Save the changes to the database
        cartRepository.save(cart);
    }

    public void deleteFromCart(Long userId, Long cartItemId) {
        User user = userRepository.findById(userId).orElseThrow();

        Cart cart = cartRepository.findByUserId(userId).orElseThrow();
        if (cart == null) {
            throw new RuntimeException("User's cart not found");
        }

        CartItem cartItemToDelete = cart.getCartItems().stream()
                .filter(item -> item.getId().equals(cartItemId))
                .findFirst()
                .orElseThrow();

        cart.getCartItems().remove(cartItemToDelete);
        cartItemToDelete.setCart(null);
        cartRepository.save(cart);
    }

    public CartViewDto ShowCart(Long userId){
        Optional<Cart> cart = cartRepository.findByUserId(userId);
        if(!cart.isPresent()){
            return null;
        }
        return cartMapper.MapToCartViewDto(cart.get());
    }
}
