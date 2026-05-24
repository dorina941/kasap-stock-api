package com.kasap.stock.dto;

public record CreateProductRequest(String name, double price, int stock) {
}
