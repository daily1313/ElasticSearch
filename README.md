## Elastic Search
- 실시간 분산 검색 및 분석 엔진
- JSON 문서를 저장하고 거의 실시간으로(NRT, Near Real Time) 검색, 분석, 시각화하는데 사용되는 오픈 소스 솔루션
- 로그 및 이벤트 데이터, 텍스트 데이터, 지리 정보 등 대량의 데이터를 실시간으로 검색하고 분석하는데 사용
- Elastic Search는 Apache Lucene(자바 언어로 이루어진 정보 검색 라이브러리 자유-오픈 소스 소프트웨어) 기반으로 작동하고, 대량의 데이터를 빠르고 정확하게 쿼리하고 분석하는데 사용
- 검색을 위해 단독으로 사용되는 경우도 있지만, ELK(Elasticsearch / Logstash / Kibana) Stack으로 사용되기도 함

## ELK Stack

<img width="600" alt="스크린샷 2023-08-24 오후 10 39 18" src="https://github.com/daily1313/ElasticSearch/assets/88074556/3bf33dd8-52ab-4921-ac9c-f1f178fc0a91">

- Logstash : 다양한 소스(DB)의 로그 데이터를 수집, 필터링 및 가공을 통해 Elasticsearch로 전달(Collect & Transform)
- ElasticSearch: Logstash로부터 받은 데이터를 검색 및 분석을 통한 필요한 정보 획득 (Search & Analyze)
- Kibana : Elasticsearch로 부터 데이터 시각화 및 모니터링 (Visualize & Manage)

## ElasticSearch RDBMS 비교 

<img width="600" alt="스크린샷 2023-08-24 오후 10 46 04" src="https://github.com/daily1313/ElasticSearch/assets/88074556/ad4ea7fd-111a-4c35-bb70-09f21e7c38bd">

1. 데이터 모델
- RDBMS: 관계형 데이터베이스는 정형화된 데이터를 저장하고 관리하는 데 사용됩니다. 데이터는 테이블로 구성되며, 각 테이블은 미리 정의된 스키마에 따라 열(Column)을 가집니다.
- Elasticsearch: Elasticsearch는 비정형화된 데이터나 텍스트 기반의 검색에 최적화된 데이터를 저장하고 검색하는 데 사용됩니다. JSON 형식의 도큐먼트를 저장하며, 각 도큐먼트는 유연한 스키마를 가집니다.

2. 검색 및 쿼리
- RDBMS: RDBMS는 SQL(Query Language)을 사용하여 정형화된 데이터에 대한 복잡한 쿼리 및 조인을 수행하는 데 특화되어 있습니다.
- Elasticsearch: Elasticsearch는 풀 텍스트 검색 및 검색 엔진 기능에 특화되어 있습니다. Elasticsearch Query DSL을 사용하여 비정형 데이터의 검색 및 필터링을 수행할 수 있습니다.

3. 스케일링 및 분산
- RDBMS: RDBMS는 주로 수직적 확장(Vertical Scaling)을 통해 성능을 향상시킵니다. 더 강력한 하드웨어로 업그레이드하거나 리소스를 추가할 수 있습니다.
- Elasticsearch: Elasticsearch는 수평적 확장(Horizontal Scaling)에 더 적합합니다. 여러 노드에 데이터를 분산하여 처리 능력과 용량을 확장할 수 있습니다.

4. 인덱싱과 검색 성능
- RDBMS: RDBMS는 기본적으로 인덱스를 사용하여 데이터에 접근하며, 쿼리의 복잡성에 따라 성능이 달라집니다.
- Elasticsearch: Elasticsearch는 역색인(inverted index)을 사용하여 풀 텍스트 검색을 지원하며, 대용량 텍스트 데이터의 검색에 매우 빠른 성능을 제공합니다.

5. 데이터 일관성
- RDBMS: RDBMS는 ACID 트랜잭션을 지원하여 데이터 일관성을 보장합니다.
- Elasticsearch: Elasticsearch는 높은 가용성과 검색 성능을 중요시하기 때문에 일부 데이터 일관성을 희생할 수 있습니다.

6. 용도
- RDBMS: 정형화된 데이터(예: 주문, 회원 등)의 관리에 적합합니다.
- Elasticsearch: 텍스트 검색, 로그 분석, 모니터링 데이터와 같은 비정형 데이터의 저장 및 검색에 적합합니다

## ElasticSearch의 구성요소

- 클러스터(cluster): 여러 대의 서버나 노드가 함께 동작하여 데이터를 저장하고 처리하는 분산 환경
- 노드(node): Elasticsearch를 구성하는 하나의 단위 프로세스
- 인덱스(index): 동일한 유형의 문서들을 저장하는 논리적인 데이터 저장 공간, 각 문서는 인덱스 내에 저장
- 문서(Document): Json 형식으로 표현되는 단위 데이터, 인덱스 내 저장되며, 각 문서는 고유한 식별자(ID)를 가짐
- 샤드(Shard): 데이터를 여러 조각으로 나누어 저장, 병렬 처리 및 확장성 지원
- 매핑(Mapping): 인덱스 내에서 문서의 구조 및 데이터 타입 정의
- 쿼리(Query): 데이터 검색 및 분석을 위한 질의 언어
- 집계(Aggregation): 데이터를 그룹화하고 요약하는 기능, 복잡한 분석 및 집계 작업을 수행