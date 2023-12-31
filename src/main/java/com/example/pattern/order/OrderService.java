package com.example.pattern.order;
import com.example.pattern.book.Book;
import com.example.pattern.cart.Cart;
import com.example.pattern.cart.CartRepository;
import com.example.pattern.cart.cartitem.CartItem;
import com.example.pattern.cart.cartitem.CartItemRepository;
import com.example.pattern.order.orderitem.OrderItem;
import com.example.pattern.order.status.IOrderState;
import com.example.pattern.order.status.OrderStateFactory;
import com.example.pattern.order.status.OrderStatus;
import com.example.pattern.payment.OrderPaymentInfo;
import com.example.pattern.payment.OrderPaymentInfoRepository;
import com.example.pattern.payment.PaymentFactory;
import com.example.pattern.payment.strategies.IPaymentStrategy;
import com.example.pattern.payment.strategies.PaymentStarategy;
import com.example.pattern.user.User;
import com.example.pattern.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService implements IOrderService{

    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final OrderPaymentInfoRepository orderPaymentInfoRepository;
    private final PaymentFactory paymentFactory;
    private final OrderMapper orderMapper;
    private final OrderStateFactory orderStateFactory;
    private final CartRepository cartRepository;

    @Autowired
    public OrderService(UserRepository userRepository, OrderRepository orderRepository, OrderPaymentInfoRepository orderPaymentInfoRepository, PaymentFactory paymentFactory, OrderMapper orderMapper, OrderStateFactory orderStateFactory, CartRepository cartRepository) {
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
        this.orderPaymentInfoRepository = orderPaymentInfoRepository;
        this.paymentFactory = paymentFactory;
        this.orderMapper = orderMapper;
        this.orderStateFactory = orderStateFactory;
        this.cartRepository = cartRepository;
    }

    @Override
    public void craeteOrder(Long userId, PaymentStarategy paymentStarategy, String creditCardNumber, String creditCardExpiry, String creditCardCvv) {
        User user =  userRepository.findById(userId).orElseThrow();
        Optional<Cart> cart = cartRepository.findByUserId(userId);
        if(!cart.isPresent()){
            return;
        }
        Order order = new Order();
        order.setUser(user);
        List<CartItem> cartItems = cart.get().getCartItems();
        List<OrderItem> orderItems = new ArrayList<>();
        double totalPrice = 0;
        Book book;
        int quantity = 0;
        for(int i = 0 ; i < cartItems.size(); i++){
            book = cartItems.get(i).getBook();
            quantity = cartItems.get(i).getQuantity();
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setBook(book);
            orderItem.setQuantity(quantity);
            orderItems.add(orderItem);
            double itemTotal = book.getPrice() * quantity;
            totalPrice += itemTotal;
        }

        order.setOrderItems(orderItems);
        order.setTotalPrice(totalPrice);
        order.setStatus(OrderStatus.PENDING);

        orderRepository.save(order);

        List<CartItem> itemsToRemove = new ArrayList<>();

        for (CartItem cartItem : cart.get().getCartItems()) {
            itemsToRemove.add(cartItem);
        }

        for (CartItem cartItem : itemsToRemove) {
            cart.get().getCartItems().remove(cartItem);
            cartItem.setCart(null);
            cartRepository.save(cart.get());
        }

        // Save payment information
        OrderPaymentInfo paymentInfo = new OrderPaymentInfo();
        paymentInfo.setOrder(order);
        paymentInfo.setTotalPrice(totalPrice);

        // Set credit card information if applicable
        if (String.valueOf(paymentStarategy).equalsIgnoreCase(String.valueOf(PaymentStarategy.CREDITCARD))) {
            paymentInfo.setCreditCardNumber(creditCardNumber.strip());
            paymentInfo.setCreditCardExpiry(parseExpirationDate(creditCardExpiry.strip()));
            paymentInfo.setCreditCardCvv(creditCardCvv.strip());
        }
        paymentInfo.setPaymentStarategy(paymentStarategy);
        orderPaymentInfoRepository.save(paymentInfo);
    }

    @Override
    public void confirmOrder(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow();
        IOrderState state = orderStateFactory.getOrderState(order);
        state.Confirm();
        IPaymentStrategy paymentStrategy = paymentFactory.getPaymentStrategy(order.getOrderPaymentInfo());
        paymentStrategy.processPayment(order.getTotalPrice());
        orderRepository.save(order);
    }

    @Override
    public void cancelOrder(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow();
        IOrderState state = orderStateFactory.getOrderState(order);
        state.Cancel();
        orderRepository.save(order);
    }

    @Override
    public List<OrderViewDto> getAllOrders() {
        List<Order> orders = orderRepository.findPendingOrders();
        return orders.stream().map(o -> orderMapper.MapToOrderViewDto(o)).collect(Collectors.toList());
    }

    @Override
    public List<OrderViewDto> getUserOrders(Long userId) {
        List<Order> orders = orderRepository.findOrdersByUser(userId);
        return orders.stream().map(o -> orderMapper.MapToOrderViewDto(o)).collect(Collectors.toList());
    }

    @Override
    public int countPendingOrders() {
        return orderRepository.countPendingOrders();
    }


    private LocalDate parseExpirationDate(String expirationDate) {
        return LocalDate.parse(expirationDate);
    }

}
