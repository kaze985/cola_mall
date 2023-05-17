package com.lppnb.product;

import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.SingleResponse;
import com.lppnb.product.api.EsProductService;
import com.lppnb.product.dto.EsProductDTO;
import com.lppnb.product.dto.EsProductRelatedInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 搜索商品管理Controller
 * @author kaze
 * @date 2023/5/17 0:53
 */
@RestController
@Api(tags = "EsProductController")
@RequestMapping("/esProduct")
public class EsProductController {
    @Resource
    private EsProductService esProductService;

    @ApiOperation(value = "导入所有数据库中商品到ES")
    @RequestMapping(value = "/importAll", method = RequestMethod.POST)
    public SingleResponse<Integer> importAllList() {
        int count = esProductService.importAll();
        return SingleResponse.of(count);
    }

    @ApiOperation(value = "根据id删除商品")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public SingleResponse<Object> delete(@PathVariable Long id) {
        esProductService.delete(id);
        return SingleResponse.of(null);
    }

    @ApiOperation(value = "根据id批量删除商品")
    @RequestMapping(value = "/delete/batch", method = RequestMethod.POST)
    public SingleResponse<Object> delete(@RequestParam("ids") List<Long> ids) {
        esProductService.delete(ids);
        return SingleResponse.of(null);
    }

    @ApiOperation(value = "根据id创建商品")
    @RequestMapping(value = "/create/{id}", method = RequestMethod.POST)
    public SingleResponse<EsProductDTO> create(@PathVariable Long id) {
        EsProductDTO esProductDTO = esProductService.create(id);
        return SingleResponse.of(esProductDTO);
    }

    @ApiOperation(value = "简单搜索")
    @RequestMapping(value = "/search/simple", method = RequestMethod.GET)
    public PageResponse<EsProductDTO> search(@RequestParam(required = false) String keyword,
                                             @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                             @RequestParam(required = false, defaultValue = "5") Integer pageSize) {
        return esProductService.search(keyword, pageNum, pageSize);
    }

    @ApiOperation(value = "综合搜索、筛选、排序")
    @ApiImplicitParam(name = "sort", value = "排序字段:0->按相关度；1->按新品；2->按销量；3->价格从低到高；4->价格从高到低",
            defaultValue = "1", allowableValues = "0,1,2,3,4", paramType = "query", dataType = "Integer", dataTypeClass = Integer.class)
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public PageResponse<EsProductDTO> search(@RequestParam(required = false) String keyword,
                                             @RequestParam(required = false) Long brandId,
                                             @RequestParam(required = false) Long productCategoryId,
                                             @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                             @RequestParam(required = false, defaultValue = "5") Integer pageSize,
                                             @RequestParam(required = false, defaultValue = "0") Integer sort) {
        return esProductService.search(keyword, brandId, productCategoryId, pageNum, pageSize, sort);
    }

    @ApiOperation(value = "根据商品id推荐商品")
    @RequestMapping(value = "/recommend/{id}", method = RequestMethod.GET)
    public PageResponse<EsProductDTO> recommend(@PathVariable Long id,
                                                         @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                                         @RequestParam(required = false, defaultValue = "5") Integer pageSize) {
        return esProductService.recommend(id, pageNum, pageSize);
    }

    @ApiOperation(value = "获取搜索的相关品牌、分类及筛选属性")
    @RequestMapping(value = "/search/relate", method = RequestMethod.GET)
    public SingleResponse<EsProductRelatedInfo> searchRelatedInfo(@RequestParam(required = false) String keyword) {
        EsProductRelatedInfo productRelatedInfo = esProductService.searchRelatedInfo(keyword);
        return SingleResponse.of(productRelatedInfo);
    }
}
