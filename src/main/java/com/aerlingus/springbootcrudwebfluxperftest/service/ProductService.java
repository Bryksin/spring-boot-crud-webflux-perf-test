package com.aerlingus.springbootcrudwebfluxperftest.service;

import com.aerlingus.springbootcrudwebfluxperftest.model.Product;
import com.aerlingus.springbootcrudwebfluxperftest.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Random;
import java.util.stream.IntStream;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final Random random = new Random();

    public Mono<Product> createProduct(Product product) {
        return productRepository.save(product);
    }

    public Flux<Product> createProducts(int amount) {
        return productRepository.saveAll(IntStream.range(0, amount).mapToObj(i -> Product.builder()
                .productName("productName_" + i)
                .productDescription("productDescription_" + i)
                .productPrice(Math.round(random.nextFloat() * 9999.99f) / 100.0f)
                .productStock(random.nextInt(1000) +1)
                .build()).toList());
    }

    public Mono<Product> getProductById(Long productId) {
        return productRepository.findById(productId)
                .switchIfEmpty(Mono.error(new ResponseStatusException(NOT_FOUND, "Product with id: " + productId + " not found")));
    }

    public Flux<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Mono<Product> updateProduct(Long productId, Product product) {
        return getProductById(productId)
                .flatMap(existingProduct -> {
                    existingProduct.setProductName(product.getProductName());
                    existingProduct.setProductDescription(product.getProductDescription());
                    existingProduct.setProductPrice(product.getProductPrice());
                    existingProduct.setProductStock(product.getProductStock());
                    return productRepository.save(existingProduct);
                });

    }

    public Mono<Void> deleteProduct(Long productId) {
        return getProductById(productId)
                .flatMap(productRepository::delete);
    }

    public Mono<Void> deleteAllProducts() {
        return productRepository.deleteAll();
    }
}
