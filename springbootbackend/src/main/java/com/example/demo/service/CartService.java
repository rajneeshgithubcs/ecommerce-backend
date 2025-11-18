package com.example.demo.service;

import com.example.demo.model.Cart;
import com.example.demo.model.CartItem;
import com.example.demo.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class CartService {

    private final CartRepository cartRepository;

    @Autowired
    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    // ================= CREATE OR GET CART =================
    public Cart getOrCreateCart(String userId) {
        return cartRepository.findByUserId(userId)
                .orElseGet(() -> cartRepository.save(
                        new Cart(userId, new ArrayList<>())
                ));
    }

    // ================= ADD TO CART =================
    public Cart addToCart(String userId, String shoeId, int qty) {

        Cart cart = getOrCreateCart(userId);

        Optional<CartItem> existing = cart.getItems()
                .stream()
                .filter(item -> item.getShoeId().equals(shoeId))
                .findFirst();

        if (existing.isPresent()) {
            CartItem item = existing.get();
            item.setQuantity(item.getQuantity() + qty);
        } else {
            cart.getItems().add(new CartItem(shoeId, qty));
        }

        return cartRepository.save(cart);
    }

    // ================= REMOVE FROM CART =================
    public Cart removeFromCart(String userId, String shoeId) {

        Cart cart = getOrCreateCart(userId);
        cart.getItems().removeIf(item -> item.getShoeId().equals(shoeId));

        return cartRepository.save(cart);
    }

    // ================= UPDATE QUANTITY =================
    public Cart updateQuantity(String userId, String shoeId, int qty) {

        Cart cart = getOrCreateCart(userId);
        cart.getItems().forEach(item -> {
            if (item.getShoeId().equals(shoeId)) {
                item.setQuantity(qty);
            }
        });

        return cartRepository.save(cart);
    }

    // ================= CLEAR CART =================
    public void clearCart(String userId) {
        cartRepository.findByUserId(userId).ifPresent(cart -> {
            cart.getItems().clear();
            cartRepository.save(cart);
        });
    }
}
