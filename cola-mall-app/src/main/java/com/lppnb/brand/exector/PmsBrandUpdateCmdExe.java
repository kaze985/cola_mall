package com.lppnb.brand.exector;

import com.alibaba.cola.dto.Response;
import com.lppnb.brand.dto.PmsBrandUpdateCmd;
import com.lppnb.convertor.PmsBrandConvertor;
import com.lppnb.generator.mapper.PmsBrandMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author kaze
 */
@Component
public class PmsBrandUpdateCmdExe {
    @Resource
    private PmsBrandMapper pmsBrandMapper;
    public Response execute(PmsBrandUpdateCmd cmd) {
        pmsBrandMapper.updateByPrimaryKeySelective(PmsBrandConvertor.toDataObject(cmd.getPmsBrandDTO()));
        return Response.buildSuccess();
    }
}
