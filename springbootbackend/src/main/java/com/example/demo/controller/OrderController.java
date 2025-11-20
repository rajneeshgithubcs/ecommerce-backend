package com.example.demo.controller;

import com.example.demo.model.Order;
import com.example.demo.service.OrderService;
import com.example.demo.security.JwtUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "*")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private JwtUtils jwtUtil;

    // ----------------------------------------------------
    // ✅ CREATE ORDER (Handles transactionId properly)
    // ----------------------------------------------------
    @PostMapping("/create")
    public ResponseEntity<?> createOrder(
            @RequestHeader("Authorization") String authHeader,
            @RequestBody Map<String, Object> body) {

        try {
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                return ResponseEntity.status(401).body(Map.of("message", "Missing token"));
            }

            String token = authHeader.substring(7);
            String userId = jwtUtil.extractUserId(token);

            if (userId == null) {
                return ResponseEntity.status(401).body(Map.of("message", "Invalid token"));
            }

            List<Map<String, Object>> items =
                    (List<Map<String, Object>>) body.get("items");

            String address = (String) body.get("address");
            String paymentMethod = (String) body.get("paymentMethod");
            Integer totalPrice = (Integer) body.get("totalPrice");
            String transactionId = (String) body.get("transactionId");  // ⭐ Coming from frontend

            if (items == null || items.isEmpty()) {
                return ResponseEntity.badRequest().body(Map.of("message", "Order has no items"));
            }

            // ⭐ Create Order
            Order order = orderService.createOrder(
                    userId,
                    items,
                    address,
                    paymentMethod,
                    totalPrice,
                    transactionId   // ⭐ VERY IMPORTANT
            );

            return ResponseEntity.ok(order);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500)
                    .body(Map.of("error", "Order creation failed", "details", e.getMessage()));
        }
    }

    // ----------------------------------------------------
    // ✅ FETCH USER ORDERS
    // ----------------------------------------------------
    @GetMapping("/my-orders")
    public ResponseEntity<?> getMyOrders(@RequestHeader("Authorization") String authHeader) {

        try {
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                return ResponseEntity.status(401).body(Map.of("message", "Missing token"));
            }

            String token = authHeader.substring(7);
            String userId = jwtUtil.extractUserId(token);

            if (userId == null) {
                return ResponseEntity.status(401).body(Map.of("message", "Invalid token"));
            }

            List<Order> orders = orderService.getOrdersForUser(userId);
            return ResponseEntity.ok(orders);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500)
                    .body(Map.of("error", "Failed to fetch orders", "details", e.getMessage()));
        }
    }

    // ----------------------------------------------------
    // ❌ DELETE ORDER
    // ----------------------------------------------------
    @DeleteMapping("/delete/{orderId}")
    public ResponseEntity<?> deleteOrder(
            @RequestHeader("Authorization") String authHeader,
            @PathVariable String orderId) {

        try {
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                return ResponseEntity.status(401).body(Map.of("message", "Missing token"));
            }

            String token = authHeader.substring(7);
            String userId = jwtUtil.extractUserId(token);

            boolean deleted = orderService.deleteOrder(orderId, userId);

            if (!deleted) {
                return ResponseEntity.badRequest()
                        .body(Map.of("message", "Unauthorized or Order not found"));
            }

            return ResponseEntity.ok(Map.of("message", "Order removed"));

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500)
                    .body(Map.of("error", "Failed to delete order"));
        }
    }

    // ----------------------------------------------------
    // ❌ CANCEL ORDER
    // ----------------------------------------------------
    @PatchMapping("/cancel/{orderId}")
    public ResponseEntity<?> cancelOrder(
            @RequestHeader("Authorization") String authHeader,
            @PathVariable String orderId) {

        try {
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                return ResponseEntity.status(401).body(Map.of("message", "Missing token"));
            }

            String token = authHeader.substring(7);
            String userId = jwtUtil.extractUserId(token);

            boolean success = orderService.cancelOrder(orderId, userId);

            if (!success) {
                return ResponseEntity.badRequest()
                        .body(Map.of("message", "Order not found or unauthorized"));
            }

            return ResponseEntity.ok(Map.of("message", "Order canceled"));

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500)
                    .body(Map.of("error", "Failed to cancel order", "details", e.getMessage()));
        }
    }
}
