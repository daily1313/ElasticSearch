package com.example.elasticsearch.repository;

import com.example.elasticsearch.document.Product;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends ElasticsearchRepository<Product, Long>, CrudRepository<Product, Long> {
    List<Product> findAll();
    Product findById(int id);
}
