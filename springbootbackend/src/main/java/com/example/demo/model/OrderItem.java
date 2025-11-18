package com.example.demo.model;

public class OrderItem {

    private String shoeId;
    private String name;
    private int quantity;
    private int size;
    private double price;
    private String image;

    public OrderItem() {}

    public OrderItem(String shoeId, String name, int quantity, int size, double price, String image) {
        this.shoeId = shoeId;
        this.name = name;
        this.quantity = quantity;
        this.size = size;
        this.price = price;
        this.image = image;
    }

    // Getters and Setters
    public String getShoeId() { return shoeId; }
    public void setShoeId(String shoeId) { this.shoeId = shoeId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public int getSize() { return size; }
    public void setSize(int size) { this.size = size; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }
}
