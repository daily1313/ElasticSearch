package com.example.elasticsearch.util;

import co.elastic.clients.elasticsearch._types.query_dsl.BoolQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.MatchAllQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.MatchQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch._types.query_dsl.TermQuery;
import java.util.ArrayList;
import java.util.List;
import lombok.val;

import java.util.function.Supplier;

public class ElasticSearchUtil {

    public static Supplier<Query> supplier() {
        Supplier<Query> supplier = () -> Query.of(q->q.matchAll(matchAllQuery()));
        return supplier;
    }

    public static MatchAllQuery matchAllQuery() {
        val matchAllQuery = new MatchAllQuery.Builder();
        return matchAllQuery.build();
    }

    public static Supplier<Query> supplierQueryForBoolQuery(final String productName, final Integer qty) {
        Supplier<Query> supplier = () -> Query.of(q->q.bool(boolQuery(productName, qty)));
        return supplier;
    }

    public static BoolQuery boolQuery(final String productName, final Integer qty) {
        val boolQuery = new BoolQuery.Builder();
        return boolQuery.filter(termQuery(productName)).must(matchQuery(qty)).build();
        // 필터 안에 가장 많은 쿼리 반환
    }

    public static List<Query> termQuery(final String productName) {
        final List<Query> terms = new ArrayList<>();
        val termQuery = new TermQuery.Builder();
        terms.add(Query.of(q->q.term(termQuery.field("name").value(productName).build())));
        return terms;
    }

    public static List<Query> matchQuery(final Integer qty) {
        final List<Query> matches = new ArrayList<>();
        val matchQuery = new MatchQuery.Builder();
        matches.add(Query.of(q->q.match(matchQuery.field("name").query(qty).build())));
        return matches;
    }
}
