package com.example.elasticsearch.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ProductCreateRequestDto {

    @NotNull
    private int id;

    @NotBlank
    private String productName;

    @NotNull
    private int quantity;

    @NotNull
    private double price;
}
