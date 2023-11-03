package com.example.elasticsearch.service;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import com.example.elasticsearch.document.Product;
import com.example.elasticsearch.util.ElasticSearchUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.function.Supplier;


@Service
public class ElasticSearchService {

    private final ElasticsearchClient elasticsearchClient;

    public ElasticSearchService(final ElasticsearchClient elasticsearchClient) {
        this.elasticsearchClient = elasticsearchClient;
    }

    public SearchResponse<Product> matchAllServices() throws IOException {
        Supplier<Query> supplier = ElasticSearchUtil.supplier();
        SearchResponse<Product> searchResponse = elasticsearchClient.search(s -> s.index("product_index")
                .query(supplier.get()), Product.class);
        System.out.println("elasticsearch query is" + supplier.get().toString());
        return searchResponse;
    }
}
