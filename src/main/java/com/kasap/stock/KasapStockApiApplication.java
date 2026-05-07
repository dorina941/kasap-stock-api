package com.kasap.stock;

import com.kasap.stock.controller.HealthController;
import com.kasap.stock.controller.ProductController;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

public class KasapStockApiApplication {

    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        server.createContext("/", exchange -> {
            String response = """
                    <!DOCTYPE html>
                    <html lang="es">
                    <head>
                        <meta charset="UTF-8">
                        <title>Kasap Stock API</title>
                    </head>
                    <body>
                        <h1>Kasap Stock API</h1>
                        <p>Servidor funcionando correctamente.</p>

                        <h2>Endpoints disponibles</h2>
                        <ul>
                            <li><a href="/api/health">Health</a></li>
                            <li><a href="/api/products">Productos</a></li>
                        </ul>

                        <p>Para crear productos con POST usa PowerShell, Postman o curl.exe.</p>
                    </body>
                    </html>
                    """;

            byte[] responseBytes = response.getBytes(StandardCharsets.UTF_8);

            exchange.getResponseHeaders().add("Content-Type", "text/html; charset=UTF-8");
            exchange.sendResponseHeaders(200, responseBytes.length);

            try (OutputStream os = exchange.getResponseBody()) {
                os.write(responseBytes);
            }
        });

        server.createContext("/api/health", new HealthController());
        server.createContext("/api/products", new ProductController());

        server.setExecutor(null);
        server.start();

        System.out.println("Servidor iniciado correctamente.");
        System.out.println("Pagina principal: http://localhost:8080");
        System.out.println("Health: http://localhost:8080/api/health");
        System.out.println("Productos: http://localhost:8080/api/products");
    }
}