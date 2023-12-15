package com.example.pattern.cart;

import com.example.pattern.order.Inventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CartController {

    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/cart")
    public String ShowCart(Model model){
        CartViewDto cartViewDto = cartService.ShowCart(1L);
        model.addAttribute("cart", cartViewDto);
        return "shoping-cart";
    }

    @PostMapping("/add-to-cart")
    public String addToCart(@RequestParam Long bookId, @RequestParam int quantity){
        if(bookId == null || quantity <= 0){
            return "redirect:/cart";
        }
        cartService.AddToCart(1L, bookId, quantity);
        return "redirect:/cart";
    }

    @PostMapping("/delete-from-cart/{cartItemId}")
    public String deleteFromCart(@PathVariable("cartItemId") Long cartItemId){
        cartService.deleteFromCart(1L ,cartItemId);
        return "redirect:/cart";
    }
}
