package com.example.demo.service;

import com.example.demo.model.Order;
import com.example.demo.model.OrderItem;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.ShoeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ShoeRepository shoeRepository;

    // ====================================================
    // ✅ CREATE ORDER — Matches frontend format fully
    // ====================================================
    public Order createOrder(String userId, List<Map<String, Object>> items) {

        List<OrderItem> orderItems = new ArrayList<>();

        for (Map<String, Object> item : items) {

            String shoeId = (String) item.get("id");
            String name = (String) item.get("name");
            int quantity = (int) item.get("quantity");
            int size = (int) item.get("size");
            double price = Double.parseDouble(item.get("price").toString());
            String image = (String) item.get("image");

            // 🔥 Create order item with ALL required fields
            OrderItem orderItem = new OrderItem(
                    shoeId,
                    name,
                    quantity,
                    size,
                    price,
                    image
            );

            orderItems.add(orderItem);
        }

        // Calculate total bill
        double totalAmount = orderItems.stream()
                .mapToDouble(i -> i.getPrice() * i.getQuantity())
                .sum();

        // Create and save order
        Order order = new Order(userId, orderItems, totalAmount);

        return orderRepository.save(order);
    }

    // ====================================================
    // ✅ GET ALL ORDERS FOR USER
    // ====================================================
    public List<Order> getOrdersForUser(String userId) {
        return orderRepository.findByUserId(userId);
    }
    // ====================================================
// ❌ DELETE ORDER COMPLETELY
// ====================================================
    public boolean deleteOrder(String orderId, String userId) {

        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if (optionalOrder.isEmpty()) return false;

        Order order = optionalOrder.get();

        // Check user ownership
        if (!order.getUserId().equals(userId)) return false;

        // Delete from DB
        orderRepository.delete(order);
        return true;
    }


    // ====================================================
    // ❌ CANCEL ORDER (Only if user owns it)
    // ====================================================
    public boolean cancelOrder(String orderId, String userId) {

        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if (optionalOrder.isEmpty()) return false;

        Order order = optionalOrder.get();

        if (!order.getUserId().equals(userId)) return false;

        order.setStatus("Canceled");
        orderRepository.save(order);

        return true;
    }
}
