package com.example.elasticsearch.controller;

import com.example.elasticsearch.dto.ProductCreateRequestDto;
import com.example.elasticsearch.dto.ProductFindAllResponseDto;
import com.example.elasticsearch.dto.ProductResponseDto;
import com.example.elasticsearch.dto.ProductUpdateRequestDto;
import com.example.elasticsearch.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ProductController {

    private final ProductService productService;

    public ProductController(final ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public ResponseEntity<ProductFindAllResponseDto> getAllProducts() {
        return ResponseEntity.status(200).body(productService.getAllProducts());
    }

    @PostMapping("/products")
    public ResponseEntity<ProductResponseDto> insertProduct(@RequestBody @Valid final ProductCreateRequestDto productCreateRequestDto) {
        return ResponseEntity.status(201).body(productService.insertProduct(productCreateRequestDto));
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<ProductResponseDto> updateProduct(@RequestBody @Valid final ProductUpdateRequestDto productUpdateRequestDto,
                                                            @PathVariable("id") final int id) {
        return ResponseEntity.status(200).body(productService.updateProduct(productUpdateRequestDto, id));
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") final int id) {
        productService.deleteProduct(id);
        return ResponseEntity.status(204).build();
    }
}
