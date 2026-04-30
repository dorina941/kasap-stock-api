package com.kasap.stock;

import com.kasap.stock.controller.HealthController;
import com.sun.net.httpserver.HttpServer;
import com.kasap.stock.controller.ProductController;

import java.io.IOException;
import java.net.InetSocketAddress;

public class KasapStockApiApplication {

    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        server.createContext("/api/health", new HealthController());
        server.createContext("/api/products", new ProductController());

        server.setExecutor(null);
        server.start();

        System.out.println("Servidor iniciado en http://localhost:8080/api/health");
    }
}