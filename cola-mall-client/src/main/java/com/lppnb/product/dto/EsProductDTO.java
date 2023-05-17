package com.lppnb.product.dto;

import com.alibaba.cola.dto.DTO;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author kaze
 * @date 2023/5/17 22:44
 */
@Data
public class EsProductDTO extends DTO {
    private Long id;
    private String productSn;
    private Long brandId;
    private String brandName;
    private Long productCategoryId;
    private String productCategoryName;
    private String pic;
    private String name;
    private String subTitle;
    private String keywords;
    private BigDecimal price;
    private Integer sale;
    private Integer newStatus;
    private Integer recommandStatus;
    private Integer stock;
    private Integer promotionType;
    private Integer sort;
//    private List<EsProductAttributeValue> attrValueList;
}
