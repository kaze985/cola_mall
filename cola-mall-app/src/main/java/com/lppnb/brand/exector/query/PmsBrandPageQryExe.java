package com.lppnb.brand.exector.query;

import com.alibaba.cola.dto.PageResponse;
import com.github.pagehelper.PageHelper;
import com.lppnb.brand.dto.PmsBrandPageQry;
import com.lppnb.brand.dto.data.PmsBrandDTO;
import com.lppnb.convertor.PmsBrandConvertor;
import com.lppnb.generator.dataobject.PmsBrandDO;
import com.lppnb.generator.mapper.PmsBrandMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author kaze
 */
@Component
public class PmsBrandPageQryExe {
    @Resource
    private PmsBrandMapper pmsBrandMapper;
    public PageResponse<PmsBrandDTO> execute(PmsBrandPageQry qry) {
        PageHelper.startPage(qry.getPageNum(), qry.getPageSize());
        List<PmsBrandDO> dos = pmsBrandMapper.selectAll();
        if (dos == null) {
            return PageResponse.of(null, 0, qry.getPageSize(), qry.getPageNum());
        }
        int count = pmsBrandMapper.count();
        List<PmsBrandDTO> dtos = PmsBrandConvertor.toDataTransferObjects(dos);
        return PageResponse.of(dtos, count, qry.getPageSize(), qry.getPageNum());
    }
}
