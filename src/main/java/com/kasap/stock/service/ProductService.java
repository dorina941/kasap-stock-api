package com.kasap.stock.service;

import com.kasap.stock.dto.CreateProductRequest;
import com.kasap.stock.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class ProductService {

    private final AtomicLong nextId = new AtomicLong(3);
    private final List<Product> products = new CopyOnWriteArrayList<>();

    public ProductService() {
        products.add(new Product(1L, "Laptop Lenovo", 899.99, 12));
        products.add(new Product(2L, "Monitor Samsung", 199.99, 25));
    }

    public List<Product> findAll() {
        return List.copyOf(products);
    }

    public Product create(CreateProductRequest request) {
        Product product = new Product(
                nextId.getAndIncrement(),
                request.name(),
                request.price(),
                request.stock()
        );

        products.add(product);
        return product;
    }
}
