package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "shoes")
public class Shoe {

    @Id
    private String id;

    private String name;
    private String category;
    private int price;     // sale price
    private int mrp;       // original price
    private String image;

    public Shoe() {}

    public Shoe(String name, String category, int price, int mrp, String image) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.mrp = mrp;
        this.image = image;
    }

    // GETTERS & SETTERS

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public int getPrice() { return price; }
    public void setPrice(int price) { this.price = price; }

    public int getMrp() { return mrp; }
    public void setMrp(int mrp) { this.mrp = mrp; }

    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }
}
