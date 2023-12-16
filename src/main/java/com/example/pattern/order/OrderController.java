package com.example.pattern.order;

import com.example.pattern.payment.strategies.PaymentStarategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class OrderController {
    private final ProxyOrderService proxyOrderService;

    private final Inventory inventory;

    @Autowired
    public OrderController(ProxyOrderService proxyOrderService, Inventory inventory) {
        this.proxyOrderService = proxyOrderService;
        this.inventory = inventory;
    }

    @GetMapping("/dashboard/orders")
    public String showAllPendingOrders(Model model){
        List<OrderViewDto> orders = proxyOrderService.getAllOrders();
        model.addAttribute("orders", orders);
        return "orders-table";
    }

    @GetMapping("/purchase")
    public String purchase(Model model){
        double totalPrice = inventory.calculateTotalAmount(1L);
        model.addAttribute("totalPrice", totalPrice);
        return "purchase";
    }

    @GetMapping("/my-orders")
    public String showMyOrders(Model model){
        List<OrderViewDto> orders = proxyOrderService.getUserOrders(1L);
        model.addAttribute("orders", orders);
        return "myorders";
    }

    @PostMapping("/orders/create")
    public String createOrder(@RequestParam PaymentStarategy paymentStarategy,@RequestParam String creditCardNumber,@RequestParam String creditCardExpiry, @RequestParam String creditCardCvv){
        proxyOrderService.craeteOrder(1L, paymentStarategy, creditCardNumber,creditCardExpiry, creditCardCvv);
        return "redirect:/purchase";
    }

    @PostMapping("/orders/confirm/{orderId}")
    public String confirmOrder(@PathVariable("orderId") Long orderId) {
        // Logic to confirm the order
        proxyOrderService.confirmOrder(orderId);
        return "redirect:/dashboard/orders";
    }

    @PostMapping("/orders/cancel/{orderId}")
    public String cancelOrder(@PathVariable Long orderId) {
        // Logic to cancel the order
        proxyOrderService.cancelOrder(orderId);
        return "redirect:/dashboard/orders";
    }


}
