package com.example.elasticsearch.dto;

import com.example.elasticsearch.document.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ProductResponseDto {

    private int id;
    private String productName;
    private int quantity;
    private double price;

    public static ProductResponseDto toDto(final Product product) {
        return new ProductResponseDto(product.getId(), product.getProductName(), product.getQuantity(), product.getPrice());
    }
}
