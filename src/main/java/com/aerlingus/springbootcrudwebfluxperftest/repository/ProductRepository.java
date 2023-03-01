package com.aerlingus.springbootcrudwebfluxperftest.repository;


import com.aerlingus.springbootcrudwebfluxperftest.model.Product;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ProductRepository extends ReactiveCrudRepository<Product, Long> {
}
