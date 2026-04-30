package com.kasap.stock.controller;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.io.OutputStream;

public class HealthController implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String response = "Kasap API OK 🚀";

        exchange.sendResponseHeaders(200, response.getBytes().length);
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
