package com.example.demo.service;

import com.example.demo.model.Order;
import com.example.demo.model.OrderItem;
import com.example.demo.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    // ====================================================
    // ✅ CREATE ORDER (Correct mapping + REAL transactionId)
    // ====================================================
    public Order createOrder(
            String userId,
            List<Map<String, Object>> items,
            String address,
            String paymentMethod,
            Integer totalPrice,
            String transactionId   // ⭐ RECEIVED from frontend
    ) {

        List<OrderItem> orderItems = new ArrayList<>();

        for (Map<String, Object> item : items) {

            OrderItem orderItem = new OrderItem();

            orderItem.setShoeId((String) item.get("id"));
            orderItem.setName((String) item.get("name"));
            orderItem.setImage((String) item.get("image"));

            orderItem.setQuantity(Integer.parseInt(item.get("quantity").toString()));
            orderItem.setSize(Integer.parseInt(item.get("size").toString()));

            orderItem.setPrice(Double.parseDouble(item.get("price").toString()));

            orderItems.add(orderItem);
        }

        // Build final Order object
        Order order = new Order();
        order.setUserId(userId);
        order.setItems(orderItems);
        order.setAddress(address);
        order.setPaymentMethod(paymentMethod);
        order.setTotalPrice(totalPrice);

        // ⭐ If transactionId is null (COD), generate fallback safe value
        if (transactionId == null || transactionId.isBlank()) {
            transactionId = "COD-" + UUID.randomUUID().toString().substring(0, 8);
        }

        order.setTransactionId(transactionId);

        order.setStatus("CONFIRMED");
        order.setOrderDate(LocalDateTime.now());

        return orderRepository.save(order);
    }

    // ====================================================
    // GET ALL ORDERS FOR USER
    // ====================================================
    public List<Order> getOrdersForUser(String userId) {
        return orderRepository.findByUserId(userId);
    }

    // ====================================================
    // DELETE ORDER
    // ====================================================
    public boolean deleteOrder(String orderId, String userId) {

        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if (optionalOrder.isEmpty()) return false;

        Order order = optionalOrder.get();

        if (!order.getUserId().equals(userId)) return false;

        orderRepository.delete(order);
        return true;
    }

    // ====================================================
    // CANCEL ORDER
    // ====================================================
    public boolean cancelOrder(String orderId, String userId) {

        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if (optionalOrder.isEmpty()) return false;

        Order order = optionalOrder.get();

        if (!order.getUserId().equals(userId)) return false;

        order.setStatus("CANCELLED");
        orderRepository.save(order);

        return true;
    }
}
