package com.example.demo.service;

import com.example.demo.model.Wishlist;
import com.example.demo.repository.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class WishlistService {

    private final WishlistRepository wishlistRepository;

    @Autowired
    public WishlistService(WishlistRepository wishlistRepository) {
        this.wishlistRepository = wishlistRepository;
    }

    // ================= CREATE OR GET WISHLIST =================
    public Wishlist getOrCreate(String userId) {
        return wishlistRepository.findByUserId(userId)
                .orElseGet(() -> wishlistRepository.save(
                        new Wishlist(userId, new ArrayList<>())
                ));
    }

    // ================= ADD TO WISHLIST =================
    public Wishlist add(String userId, String shoeId) {

        Wishlist wishlist = getOrCreate(userId);

        if (!wishlist.getShoeIds().contains(shoeId)) {
            wishlist.getShoeIds().add(shoeId);
        }

        return wishlistRepository.save(wishlist);
    }

    // ================= REMOVE FROM WISHLIST =================
    public Wishlist remove(String userId, String shoeId) {

        Wishlist wishlist = getOrCreate(userId);

        wishlist.getShoeIds().removeIf(id -> id.equals(shoeId));

        return wishlistRepository.save(wishlist);
    }
}
