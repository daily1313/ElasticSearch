package com.example.elasticsearch.document;

import com.example.elasticsearch.dto.ProductUpdateRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Document(indexName = "product_index")
public class Product {

    private int id;
    private String productName;
    private int quantity;
    private double price;

    public void updateProduct(final String productName, final int quantity, final double price) {
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
    }
}
