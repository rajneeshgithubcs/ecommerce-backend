package com.example.demo.repository;

import com.example.demo.model.Shoe;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface ShoeRepository extends MongoRepository<Shoe, String> {

    List<Shoe> findByCategory(String category);

    // ⭐ SALE SHOES → where MRP > 0 (all shoes with sale price)
    List<Shoe> findByMrpGreaterThan(double price);
}
