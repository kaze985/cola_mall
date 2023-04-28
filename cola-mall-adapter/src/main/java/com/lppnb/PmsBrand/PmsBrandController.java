package com.lppnb.PmsBrand;

import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.SingleResponse;
import com.lppnb.PmsBrand.api.PmsBrandService;
import com.lppnb.PmsBrand.dto.*;
import com.lppnb.PmsBrand.dto.data.PmsBrandDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "PmsBrandController")
@Slf4j
@RestController
@RequestMapping("/brand")
public class PmsBrandController {
    @Autowired
    private PmsBrandService pmsBrandService;

    @ApiOperation("添加品牌")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public SingleResponse<PmsBrandDTO> createBrand(@RequestBody PmsBrandDTO dto) {
        PmsBrandAddCmd cmd = new PmsBrandAddCmd();
        cmd.setPmsBrandDTO(dto);
        return null;
    }

    @ApiOperation("更新指定id品牌信息")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public SingleResponse<PmsBrandDTO> updateBrand(@PathVariable("id") Long id, @RequestBody PmsBrandDTO dto) {
        dto.setId(id);
        PmsBrandUpdateCmd cmd = new PmsBrandUpdateCmd();
        cmd.setPmsBrandDTO(dto);
        return null;
    }

    @ApiOperation("删除指定id的品牌")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public SingleResponse<PmsBrandDTO> deleteBrand(@PathVariable("id") Long id) {
        PmsBrandDeleteCmd cmd = new PmsBrandDeleteCmd();
        cmd.setId(id);
        return null;
    }

    @ApiOperation("分页查询品牌列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public PageResponse<PmsBrandDTO> listBrand(@RequestParam(value = "pageNum", defaultValue = "1")
                                               @ApiParam("页码") Integer pageNum,
                                               @RequestParam(value = "pageSize", defaultValue = "3")
                                               @ApiParam("每页数量") Integer pageSize) {
        PmsBrandPageQry qry = new PmsBrandPageQry();
        qry.setPageNum(pageNum);
        qry.setPageSize(pageSize);
        return null;
    }

    @ApiOperation("获取指定id的品牌详情")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public SingleResponse<PmsBrandDTO> brand(@PathVariable("id") Long id) {
        PmsBrandGetQry qry = new PmsBrandGetQry();
        qry.setId(id);
        return null;
    }
}
