package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "wishlists")
public class Wishlist {

    @Id
    private String id;

    private String userId;
    private List<String> shoeIds; // stores shoe id list

    public Wishlist() {}

    public Wishlist(String userId, List<String> shoeIds) {
        this.userId = userId;
        this.shoeIds = shoeIds;
    }

    // getters & setters

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public List<String> getShoeIds() { return shoeIds; }
    public void setShoeIds(List<String> shoeIds) { this.shoeIds = shoeIds; }
}
