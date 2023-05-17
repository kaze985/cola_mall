package com.lppnb.product.api;

import com.alibaba.cola.dto.PageResponse;
import com.lppnb.product.dto.EsProductDTO;
import com.lppnb.product.dto.EsProductRelatedInfo;

import java.util.List;

/**
 * 商品搜索管理Service
 * @author kaze
 * @date 2023/5/17 0:08
 */
public interface EsProductService {
    /**
     * 从数据库中导入所有商品到ES
     */
    int importAll();

    /**
     * 根据id删除商品
     */
    void delete(Long id);

    /**
     * 根据id创建商品
     */
    EsProductDTO create(Long id);

    /**
     * 批量删除商品
     */
    void delete(List<Long> ids);

    /**
     * 根据关键字搜索名称或者副标题
     */
    PageResponse<EsProductDTO> search(String keyword, Integer pageNum, Integer pageSize);

    /**
     * 根据关键字搜索名称或者副标题复合查询
     */
    PageResponse<EsProductDTO> search(String keyword, Long brandId, Long productCategoryId, Integer pageNum, Integer pageSize,Integer sort);

    /**
     * 根据商品id推荐相关商品
     */
    PageResponse<EsProductDTO> recommend(Long id, Integer pageNum, Integer pageSize);

    /**
     * 获取搜索词相关品牌、分类、属性
     */
    EsProductRelatedInfo searchRelatedInfo(String keyword);
}
