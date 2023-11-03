package com.example.elasticsearch.controller;

import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import com.example.elasticsearch.document.Product;
import com.example.elasticsearch.dto.ProductCreateRequestDto;
import com.example.elasticsearch.dto.ProductFindAllResponseDto;
import com.example.elasticsearch.dto.ProductResponseDto;
import com.example.elasticsearch.dto.ProductUpdateRequestDto;
import com.example.elasticsearch.service.ElasticSearchService;
import com.example.elasticsearch.service.ProductService;
import jakarta.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ProductController {

    private final ProductService productService;
    private final ElasticSearchService elasticSearchService;

    public ProductController(final ProductService productService, final ElasticSearchService elasticSearchService) {
        this.productService = productService;
        this.elasticSearchService = elasticSearchService;
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

    @GetMapping("/matchAllProducts")
    public ResponseEntity<List<Product>> matchAllProducts() throws IOException {
        SearchResponse<Product> searchResponse = elasticSearchService.matchAllServices();
        List<Hit<Product>> listOfHits = searchResponse.hits().hits();
        List<Product> listOfProducts = new ArrayList<>();
        for(Hit<Product> hit : listOfHits) {
            listOfProducts.add(hit.source());
        }
        return ResponseEntity.status(200)
                .body(listOfProducts);
    }
}
