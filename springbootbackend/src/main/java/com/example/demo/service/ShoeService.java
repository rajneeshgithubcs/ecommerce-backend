package com.example.demo.service;

import com.example.demo.model.Shoe;
import com.example.demo.repository.ShoeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShoeService {

    @Autowired
    private ShoeRepository shoeRepository;

    public List<Shoe> getAllShoes() {
        return shoeRepository.findAll();
    }

    public Optional<Shoe> getById(String id) {
        return shoeRepository.findById(id);
    }

    public List<Shoe> getByCategory(String category) {
        return shoeRepository.findByCategory(category);
    }

    public Shoe addShoe(Shoe shoe) {
        return shoeRepository.save(shoe);
    }

    // ⭐ SALE SHOES LOGIC
    public List<Shoe> getSaleShoes() {
        return shoeRepository.findByMrpGreaterThan(0);
    }
}
