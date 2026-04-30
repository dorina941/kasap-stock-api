package com.kasap.stock.controller;

import com.kasap.stock.model.Product;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class ProductController implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        List<Product> products = List.of(
                new Product(1L, "Laptop Lenovo", 899.99, 12),
                new Product(2L, "Monitor Samsung", 199.99, 25),
                new Product(3L, "Teclado Logitech", 49.99, 40)
        );

        String response = "["
                + "{\"id\": 1, \"name\": \"Laptop Lenovo\", \"price\": 899.99, \"stock\": 12},"
                + "{\"id\": 2, \"name\": \"Monitor Samsung\", \"price\": 199.99, \"stock\": 25},"
                + "{\"id\": 3, \"name\": \"Teclado Logitech\", \"price\": 49.99, \"stock\": 40}"
                + "]";

        exchange.getResponseHeaders().add("Content-Type", "application/json");
        exchange.sendResponseHeaders(200, response.getBytes().length);

        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}