package com.lppnb.brand.exector.query;

import com.alibaba.cola.dto.SingleResponse;
import com.lppnb.brand.dto.PmsBrandGetQry;
import com.lppnb.brand.dto.data.PmsBrandDTO;
import com.lppnb.convertor.PmsBrandConvertor;
import com.lppnb.generator.dataobject.PmsBrandDO;
import com.lppnb.generator.mapper.PmsBrandMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author kaze
 */
@Component
public class PmsBrandGetQryExe {
    @Resource
    private PmsBrandMapper pmsBrandMapper;
    public SingleResponse<PmsBrandDTO> execute(PmsBrandGetQry qry) {
        PmsBrandDO brandDO = pmsBrandMapper.selectByPrimaryKey(qry.getId());
        if (brandDO == null) {
            return SingleResponse.of(null);
        }
        return SingleResponse.of(PmsBrandConvertor.toDataTransferObject(brandDO));
    }
}
