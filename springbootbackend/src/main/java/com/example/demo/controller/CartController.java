package com.example.demo.controller;

import com.example.demo.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin(origins = "*")
public class CartController {

    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getCart(@PathVariable String userId) {
        return ResponseEntity.ok(cartService.getOrCreateCart(userId));
    }

    @PostMapping("/{userId}/add")
    public ResponseEntity<?> addToCart(
            @PathVariable String userId,
            @RequestBody Map<String, Object> body) {

        String shoeId = (String) body.get("shoeId");
        int qty = (int) body.getOrDefault("quantity", 1);

        return ResponseEntity.ok(cartService.addToCart(userId, shoeId, qty));
    }

    @PostMapping("/{userId}/remove")
    public ResponseEntity<?> removeFromCart(
            @PathVariable String userId,
            @RequestBody Map<String, String> body) {

        String shoeId = body.get("shoeId");
        return ResponseEntity.ok(cartService.removeFromCart(userId, shoeId));
    }

    @PostMapping("/{userId}/update")
    public ResponseEntity<?> updateQty(
            @PathVariable String userId,
            @RequestBody Map<String, Object> body) {

        String shoeId = (String) body.get("shoeId");
        int qty = (int) body.get("quantity");

        return ResponseEntity.ok(cartService.updateQuantity(userId, shoeId, qty));
    }
}
