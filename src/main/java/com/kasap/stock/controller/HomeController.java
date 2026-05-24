package com.kasap.stock.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping(value = "/", produces = MediaType.TEXT_HTML_VALUE)
    public String home() {
        return """
                <!DOCTYPE html>
                <html lang="es">
                <head>
                    <meta charset="UTF-8">
                    <title>Kasap Stock API</title>
                </head>
                <body>
                    <h1>Kasap Stock API</h1>
                    <p>Servidor Spring Boot funcionando correctamente.</p>

                    <h2>Endpoints disponibles</h2>
                    <ul>
                        <li><a href="/api/health">Health</a></li>
                        <li><a href="/api/products">Productos</a></li>
                    </ul>

                    <p>Para crear productos con POST usa PowerShell, Postman o curl.exe.</p>
                </body>
                </html>
                """;
    }
}
