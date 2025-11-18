package com.example.demo.model;

public class CartItem {

    private String shoeId;
    private int quantity;

    public CartItem() {}

    public CartItem(String shoeId, int quantity) {
        this.shoeId = shoeId;
        this.quantity = quantity;
    }

    public String getShoeId() { return shoeId; }
    public void setShoeId(String shoeId) { this.shoeId = shoeId; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
}
