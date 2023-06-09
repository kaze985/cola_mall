package com.lppnb.product;

import cn.hutool.core.util.StrUtil;
import com.alibaba.cola.dto.PageResponse;
import com.lppnb.assembler.EsProductAssembler;
import com.lppnb.dao.EsProductDao;
import com.lppnb.product.api.EsProductService;
import com.lppnb.product.dto.EsProductDTO;
import com.lppnb.product.dto.EsProductRelatedInfo;
import com.lppnb.search.document.EsProduct;
import com.lppnb.search.repository.EsProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AbstractAggregationBuilder;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.filter.ParsedFilter;
import org.elasticsearch.search.aggregations.bucket.nested.ParsedNested;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedLongTerms;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedStringTerms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;


/**
 * 商品搜索管理Service实现类
 *
 * @author kaze
 * @date 2023/5/17 0:47
 */
@Service
@Slf4j
public class EsProductServiceImpl implements EsProductService {
    @Resource
    private EsProductDao productDao;
    @Resource
    private EsProductRepository productRepository;
    @Resource
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Override
    public int importAll() {
        List<EsProduct> esProductList = productDao.getAllEsProductList(null);
        Iterable<EsProduct> esProductIterable = productRepository.saveAll(esProductList);
        Iterator<EsProduct> iterator = esProductIterable.iterator();
        int result = 0;
        while (iterator.hasNext()) {
            result++;
            iterator.next();
        }
        return result;
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public EsProductDTO create(Long id) {
        EsProduct result = null;
        List<EsProduct> esProductList = productDao.getAllEsProductList(id);
        if (esProductList.size() > 0) {
            EsProduct esProduct = esProductList.get(0);
            result = productRepository.save(esProduct);
        }
        return EsProductAssembler.ASSEMBLER.toDataTransferObject(result);
    }

    @Override
    public void delete(List<Long> ids) {
        if (!CollectionUtils.isEmpty(ids)) {
            List<EsProduct> esProductList = new ArrayList<>();
            for (Long id : ids) {
                EsProduct esProduct = new EsProduct();
                esProduct.setId(id);
                esProductList.add(esProduct);
            }
            productRepository.deleteAll(esProductList);
        }
    }

    @Override
    public PageResponse<EsProductDTO> search(String keyword, Integer pageNum, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<EsProduct> result = productRepository.findByNameOrSubTitleOrKeywords(keyword, keyword, keyword, pageable);
        return PageResponse.of(EsProductAssembler.ASSEMBLER.toDataTransferObjects(result.getContent()), (int) result.getTotalElements(), pageSize, pageNum);
    }

    @Override
    public PageResponse<EsProductDTO> search(String keyword, Long brandId, Long productCategoryId, Integer pageNum, Integer pageSize, Integer sort) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        //分页
        nativeSearchQueryBuilder.withPageable(pageable);
        //过滤
        if (brandId != null || productCategoryId != null) {
            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
            if (brandId != null) {
                boolQueryBuilder.must(QueryBuilders.termQuery("brandId", brandId));
            }
            if (productCategoryId != null) {
                boolQueryBuilder.must(QueryBuilders.termQuery("productCategoryId", productCategoryId));
            }
            nativeSearchQueryBuilder.withFilter(boolQueryBuilder);
        }
        //搜索
        if (StrUtil.isEmpty(keyword)) {
            nativeSearchQueryBuilder.withQuery(QueryBuilders.matchAllQuery());
        } else {
            MultiMatchQueryBuilder multiMatchQueryBuilder = QueryBuilders.multiMatchQuery(keyword, "name", "subTitle", "keywords")
                    .field("name", 10F)
                    .field("subTitle", 5F)
                    .field("keywords", 2F);
            nativeSearchQueryBuilder.withQuery(multiMatchQueryBuilder);
        }
        //排序
        if (sort == 1) {
            //按新品从新到旧
            nativeSearchQueryBuilder.withSorts(SortBuilders.fieldSort("id").order(SortOrder.DESC));
        } else if (sort == 2) {
            //按销量从高到低
            nativeSearchQueryBuilder.withSorts(SortBuilders.fieldSort("sale").order(SortOrder.DESC));
        } else if (sort == 3) {
            //按价格从低到高
            nativeSearchQueryBuilder.withSorts(SortBuilders.fieldSort("price").order(SortOrder.ASC));
        } else if (sort == 4) {
            //按价格从高到低
            nativeSearchQueryBuilder.withSorts(SortBuilders.fieldSort("price").order(SortOrder.DESC));
        } else {
            //按相关度
            nativeSearchQueryBuilder.withSorts(SortBuilders.scoreSort().order(SortOrder.DESC));
        }
        NativeSearchQuery searchQuery = nativeSearchQueryBuilder.build();
        log.info("DSL:{}", Objects.requireNonNull(searchQuery.getQuery()));
        SearchHits<EsProduct> searchHits = elasticsearchRestTemplate.search(searchQuery, EsProduct.class);
        if (searchHits.getTotalHits() <= 0) {
            return PageResponse.of(pageSize, pageNum);
        }
        List<EsProduct> searchProductList = searchHits.stream().map(SearchHit::getContent).collect(Collectors.toList());
        return PageResponse.of(EsProductAssembler.ASSEMBLER.toDataTransferObjects(searchProductList), (int) searchHits.getTotalHits(), pageSize, pageNum);
    }

    @Override
    public PageResponse<EsProductDTO> recommend(Long id, Integer pageNum, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        List<EsProduct> esProductList = productDao.getAllEsProductList(id);
        if (esProductList.size() == 0) {
            return PageResponse.of(pageSize, pageNum);
        }
        EsProduct esProduct = esProductList.get(0);
        String keyword = esProduct.getName();
        Long brandId = esProduct.getBrandId();
        Long productCategoryId = esProduct.getProductCategoryId();
        //根据商品标题、品牌、分类进行搜索
        MultiMatchQueryBuilder multiMatchQueryBuilder = QueryBuilders.multiMatchQuery(keyword, "name", "subTitle", "keywords")
                .field("name", 8F)
                .field("subTitle", 2F)
                .field("keywords", 2F);
        BoolQueryBuilder query = QueryBuilders.boolQuery()
                .should(multiMatchQueryBuilder)
                .should(QueryBuilders.termQuery("brandId", brandId).boost(5F))
                .should(QueryBuilders.termQuery("productCategoryId", productCategoryId).boost(3F));
        //用于过滤掉相同的商品
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        boolQueryBuilder.mustNot(QueryBuilders.termQuery("id", id));
        //构建查询条件
        NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();
        builder.withQuery(query);
        builder.withFilter(boolQueryBuilder);
        builder.withPageable(pageable);
        NativeSearchQuery searchQuery = builder.build();
        log.info("DSL:{}", Objects.requireNonNull(searchQuery.getQuery()));
        SearchHits<EsProduct> search = elasticsearchRestTemplate.search(searchQuery, EsProduct.class);
        List<EsProduct> esProducts = search.stream().map(SearchHit::getContent).collect(Collectors.toList());
        return PageResponse.of(EsProductAssembler.ASSEMBLER.toDataTransferObjects(esProducts), (int) search.getTotalHits(), pageSize, pageNum);
    }

    @Override
    public EsProductRelatedInfo searchRelatedInfo(String keyword) {
        NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();
        //搜索条件
        if (StrUtil.isEmpty(keyword)) {
            builder.withQuery(QueryBuilders.matchAllQuery());
        } else {
            builder.withQuery(QueryBuilders.multiMatchQuery(keyword, "name", "subTitle", "keywords"));
        }
        //聚合搜索品牌名称
        builder.withAggregations(AggregationBuilders.terms("brandNames").field("brandName"));
        //集合搜索分类名称
        builder.withAggregations(AggregationBuilders.terms("productCategoryNames").field("productCategoryName"));
        //聚合搜索商品属性，去除type=1的属性
        AbstractAggregationBuilder aggregationBuilder = AggregationBuilders.nested("allAttrValues", "attrValueList")
                .subAggregation(AggregationBuilders.filter("productAttrs", QueryBuilders.termQuery("attrValueList.type", 1))
                        .subAggregation(AggregationBuilders.terms("attrIds")
                                .field("attrValueList.productAttributeId")
                                .subAggregation(AggregationBuilders.terms("attrValues")
                                        .field("attrValueList.value"))
                                .subAggregation(AggregationBuilders.terms("attrNames")
                                        .field("attrValueList.name"))));
        builder.withAggregations(aggregationBuilder);
        NativeSearchQuery searchQuery = builder.build();
        SearchHits<EsProduct> searchHits = elasticsearchRestTemplate.search(searchQuery, EsProduct.class);
        return convertProductRelatedInfo(searchHits);
    }

    /**
     * 将返回结果转换为对象
     */
    private EsProductRelatedInfo convertProductRelatedInfo(SearchHits<EsProduct> response) {
        EsProductRelatedInfo productRelatedInfo = new EsProductRelatedInfo();
        Map<String, Aggregation> aggregationMap = ((Aggregations) Objects.requireNonNull(response.getAggregations()).aggregations()).asMap();
        //设置品牌
        Aggregation brandNames = aggregationMap.get("brandNames");
        List<String> brandNameList = new ArrayList<>();
        for (int i = 0; i < ((Terms) brandNames).getBuckets().size(); i++) {
            brandNameList.add(((Terms) brandNames).getBuckets().get(i).getKeyAsString());
        }
        productRelatedInfo.setBrandNames(brandNameList);
        //设置分类
        Aggregation productCategoryNames = aggregationMap.get("productCategoryNames");
        List<String> productCategoryNameList = new ArrayList<>();
        for (int i = 0; i < ((Terms) productCategoryNames).getBuckets().size(); i++) {
            productCategoryNameList.add(((Terms) productCategoryNames).getBuckets().get(i).getKeyAsString());
        }
        productRelatedInfo.setProductCategoryNames(productCategoryNameList);
        //设置参数
        Aggregation productAttrs = aggregationMap.get("allAttrValues");
        List<? extends Terms.Bucket> attrIds = ((ParsedLongTerms) ((ParsedFilter) ((ParsedNested) productAttrs).getAggregations().get("productAttrs")).getAggregations().get("attrIds")).getBuckets();
        List<EsProductRelatedInfo.ProductAttr> attrList = new ArrayList<>();
        for (Terms.Bucket attrId : attrIds) {
            EsProductRelatedInfo.ProductAttr attr = new EsProductRelatedInfo.ProductAttr();
            attr.setAttrId((Long) attrId.getKey());
            List<String> attrValueList = new ArrayList<>();
            List<? extends Terms.Bucket> attrValues = ((ParsedStringTerms) attrId.getAggregations().get("attrValues")).getBuckets();
            List<? extends Terms.Bucket> attrNames = ((ParsedStringTerms) attrId.getAggregations().get("attrNames")).getBuckets();
            for (Terms.Bucket attrValue : attrValues) {
                attrValueList.add(attrValue.getKeyAsString());
            }
            attr.setAttrValues(attrValueList);
            if (!CollectionUtils.isEmpty(attrNames)) {
                String attrName = attrNames.get(0).getKeyAsString();
                attr.setAttrName(attrName);
            }
            attrList.add(attr);
        }
        productRelatedInfo.setProductAttrs(attrList);
        return productRelatedInfo;
    }
}
