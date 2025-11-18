package com.example.demo.controller;

import com.example.demo.model.Shoe;
import com.example.demo.service.ShoeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shoes")
@CrossOrigin(origins = "*")
public class ShoeController {

    private final ShoeService shoeService;

    @Autowired
    public ShoeController(ShoeService shoeService) {
        this.shoeService = shoeService;
    }

    @GetMapping
    public List<Shoe> getAllShoes() {
        return shoeService.getAllShoes();
    }

    @GetMapping("/{id}")
    public Shoe getShoeById(@PathVariable String id) {
        return shoeService.getById(id)
                .orElseThrow(() -> new RuntimeException("Shoe not found"));
    }

    @GetMapping("/category/{category}")
    public List<Shoe> getByCategory(@PathVariable String category) {
        return shoeService.getByCategory(category);
    }

    @PostMapping
    public Shoe addShoe(@RequestBody Shoe shoe) {
        return shoeService.addShoe(shoe);
    }

    // ⭐ NEW SALE ENDPOINT
    @GetMapping("/sale")
    public List<Shoe> getSaleShoes() {
        return shoeService.getSaleShoes();
    }
}
