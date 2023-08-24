package com.example.elasticsearch.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ProductFindAllResponseDto {

    private List<ProductResponseDto> products;

    public static ProductFindAllResponseDto toDto(final List<ProductResponseDto> products) {
        return new ProductFindAllResponseDto(products);
    }
}
