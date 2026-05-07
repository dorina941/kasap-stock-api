package com.kasap.stock.controller;

import com.kasap.stock.model.Product;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class ProductController implements HttpHandler {
    private static final List<Product> products = new ArrayList<>();

    static {
        products.add(new Product(1L, "Laptop Lenovo", 899.99, 12));
        products.add(new Product(2L, "Monitor Samsung", 199.99, 25));
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        System.out.println("Metodo recibido: " + exchange.getRequestMethod());

        if ("GET".equals(exchange.getRequestMethod())) {
            String response = buildProductsJson();
            sendJsonResponse(exchange, 200, response);
            return;
        }

        if ("POST".equals(exchange.getRequestMethod())) {
            Product newProduct = new Product(
                    (long) (products.size() + 1),
                    "Producto nuevo",
                    99.99,
                    10
            );

            products.add(newProduct);

            String response = "{"
                    + "\"message\": \"Producto creado correctamente\", "
                    + "\"product\": " + buildProductJson(newProduct)
                    + "}";

            sendJsonResponse(exchange, 201, response);
            return;
        }

        String response = "{\"message\": \"Metodo no permitido\"}";
        sendJsonResponse(exchange, 405, response);
    }

    private String buildProductsJson() {
        StringBuilder responseBuilder = new StringBuilder();
        responseBuilder.append("[");

        for (int i = 0; i < products.size(); i++) {
            responseBuilder.append(buildProductJson(products.get(i)));

            if (i < products.size() - 1) {
                responseBuilder.append(",");
            }
        }

        responseBuilder.append("]");
        return responseBuilder.toString();
    }

    private String buildProductJson(Product product) {
        return "{"
                + "\"id\": " + product.getId() + ", "
                + "\"name\": \"" + product.getName() + "\", "
                + "\"price\": " + product.getPrice() + ", "
                + "\"stock\": " + product.getStock()
                + "}";
    }

    private void sendJsonResponse(HttpExchange exchange, int statusCode, String response) throws IOException {
        byte[] responseBytes = response.getBytes(StandardCharsets.UTF_8);

        exchange.getResponseHeaders().add("Content-Type", "application/json; charset=UTF-8");
        exchange.sendResponseHeaders(statusCode, responseBytes.length);

        try (OutputStream os = exchange.getResponseBody()) {
            os.write(responseBytes);
        }
    }
}