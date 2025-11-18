package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "shoes")
public class Shoe {

    @Id
    private String id;

    private String name;
    private String category;
    private double price;
    private double mrp;      // 👈 NEW FIELD
    private String image;

    public Shoe() {}

    // Constructor WITHOUT ID – MRP auto-calculated
    public Shoe(String name, String category, double price, String image) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.mrp = Math.round(price * 1.20);   // 👈 MRP = 20% more
        this.image = image;
    }

    // Constructor WITH ID – MRP auto-calculated
    public Shoe(String id, String name, String category, double price, String image) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.mrp = Math.round(price * 1.20);
        this.image = image;
    }

    // ======================
    // Getters & Setters
    // ======================

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public double getPrice() { return price; }
    public void setPrice(double price) {
        this.price = price;
        this.mrp = Math.round(price * 1.20); // Update MRP when price changes
    }

    public double getMrp() { return mrp; }
    public void setMrp(double mrp) { this.mrp = mrp; }

    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }
}
