package com.example.pattern.order;
import com.example.pattern.payment.strategies.PaymentStarategy;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProxyOrderService implements IOrderService{

    private final OrderService orderService;

    private final Inventory inventory;

    public ProxyOrderService(OrderService orderService, Inventory inventory) {
        this.orderService = orderService;
        this.inventory = inventory;
    }

    @Override
    public void craeteOrder(Long userId, PaymentStarategy paymentStarategy, String creditCardNumber, String creditCardExpiry, String creditCardCvv) {
        if(inventory.CheckInventory(userId)){
            orderService.craeteOrder(userId, paymentStarategy, creditCardNumber, creditCardExpiry, creditCardCvv);
        }
    }

    @Override
    public void confirmOrder(Long orderId) {
        orderService.confirmOrder(orderId);
    }

    @Override
    public void cancelOrder(Long orderId) {
        orderService.cancelOrder(orderId);
    }

    @Override
    public List<OrderViewDto> getAllOrders() {
        return orderService.getAllOrders();
    }

    @Override
    public List<OrderViewDto> getUserOrders(Long userId) {
        return orderService.getUserOrders(userId);
    }
}
