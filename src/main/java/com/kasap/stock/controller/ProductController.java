package com.kasap.stock.controller;

import com.kasap.stock.model.Product;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.ArrayList;

public class ProductController implements HttpHandler {
    private static List<Product> products = new ArrayList<>();
    static {
        products.add(new Product(1L, "Laptop Lenovo", 899.99, 12));
        products.add(new Product(2L, "Monitor Samsung", 199.99, 25));
    }
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        System.out.println("Metodo recibido: " + exchange.getRequestMethod());

        if (!"GET".equals(exchange.getRequestMethod())) {
            String response = "{\"message\": \"Producto creado correctamente\"}";

            exchange.getResponseHeaders().add("Content-Type", "application/json");
            exchange.sendResponseHeaders(201, response.getBytes().length);

            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
            return;
        }



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