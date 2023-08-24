package com.example.elasticsearch.service;

import com.example.elasticsearch.document.Product;
import com.example.elasticsearch.dto.ProductCreateRequestDto;
import com.example.elasticsearch.dto.ProductFindAllResponseDto;
import com.example.elasticsearch.dto.ProductResponseDto;
import com.example.elasticsearch.dto.ProductUpdateRequestDto;
import com.example.elasticsearch.exception.ProductNotFoundException;
import com.example.elasticsearch.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductService {

    private static final String PRODUCT_NOT_FOUND_MESSAGE = "상품을 찾을 수 없습니다";
    private final ProductRepository productRepository;

    public ProductService(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional(readOnly = true)
    public ProductFindAllResponseDto getAllProducts() {
        List<Product> products = productRepository.findAll();
        checkFindProductListIsEmpty(products);
        List<ProductResponseDto> allProducts = products
                .stream()
                .map(ProductResponseDto::toDto)
                .collect(Collectors.toList());
        return ProductFindAllResponseDto.toDto(allProducts);
    }

    public ProductResponseDto insertProduct(final ProductCreateRequestDto productCreateRequestDto) {
        Product product = new Product(
                productCreateRequestDto.getId(),
                productCreateRequestDto.getProductName(),
                productCreateRequestDto.getQuantity(),
                productCreateRequestDto.getPrice()
        );
        productRepository.save(product);
        return ProductResponseDto.toDto(product);
    }

    public ProductResponseDto updateProduct(final ProductUpdateRequestDto productUpdateRequestDto, final int id) {
        Product product = productRepository.findById(id);
        checkFindProductIsEmpty(product);
        product.updateProduct(productUpdateRequestDto);
        productRepository.save(product);
        return ProductResponseDto.toDto(product);
    }

    public void deleteProduct(final int id) {
        Product product = productRepository.findById(id);
        checkFindProductIsEmpty(product);
        productRepository.delete(product);
    }

    private void checkFindProductIsEmpty(final Product product) {
        if(product == null) {
            throw new ProductNotFoundException(PRODUCT_NOT_FOUND_MESSAGE);
        }
    }

    private void checkFindProductListIsEmpty(final List<Product> products) {
        if(products.isEmpty()) {
            throw new ProductNotFoundException(PRODUCT_NOT_FOUND_MESSAGE);
        }
    }
}
