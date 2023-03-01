package com.aerlingus.springbootcrudwebfluxperftest.controller;

import com.aerlingus.springbootcrudwebfluxperftest.model.Product;
import com.aerlingus.springbootcrudwebfluxperftest.service.ProductService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping("/create")
    public Mono<Product> createProduct(@NotNull @Valid @RequestBody Product product) {
        return productService.createProduct(product);
    }

    @GetMapping("/create/{amount}")
    public Flux<Product> createProducts(@PathVariable int amount) {
        return productService.createProducts(amount);
    }

    @GetMapping("/get/{productId}")
    public Mono<Product> getProductById(@PathVariable Long productId) {
        return productService.getProductById(productId);
    }

    @GetMapping("/getAll")
    public Flux<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @PutMapping("/update/{productId}")
    public Mono<Product> updateProduct(@PathVariable Long productId, @NotNull @Valid @RequestBody Product product) {
        return productService.updateProduct(productId, product);
    }

    @DeleteMapping("/delete/{productId}")
    public Mono<Void> deleteProduct(@PathVariable Long productId) {
        return productService.deleteProduct(productId);
    }

    @DeleteMapping("/deleteAll")
    public Mono<Void> deleteAllProducts() {
        return productService.deleteAllProducts();
    }
}
