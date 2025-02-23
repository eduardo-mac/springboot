package com.example.springboot.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.springboot.model.ProductModel;

public interface ProductRepository extends JpaRepository<ProductModel, UUID> {
}
