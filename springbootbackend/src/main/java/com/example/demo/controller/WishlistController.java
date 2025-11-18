package com.example.demo.controller;

import com.example.demo.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/wishlist")
@CrossOrigin(origins = "*")
public class WishlistController {

    private final WishlistService wishlistService;

    @Autowired
    public WishlistController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getWishlist(@PathVariable String userId) {
        return ResponseEntity.ok(wishlistService.getOrCreate(userId));
    }

    @PostMapping("/{userId}/add")
    public ResponseEntity<?> addWishlist(
            @PathVariable String userId,
            @RequestBody Map<String, String> body) {

        String shoeId = body.get("shoeId");
        return ResponseEntity.ok(wishlistService.add(userId, shoeId));
    }

    @PostMapping("/{userId}/remove")
    public ResponseEntity<?> removeWishlist(
            @PathVariable String userId,
            @RequestBody Map<String, String> body) {

        String shoeId = body.get("shoeId");
        return ResponseEntity.ok(wishlistService.remove(userId, shoeId));
    }
}
