package com.lppnb.brand.exector;

import com.alibaba.cola.dto.Response;
import com.lppnb.brand.dto.PmsBrandAddCmd;
import com.lppnb.convertor.PmsBrandConvertor;
import com.lppnb.generator.mapper.PmsBrandMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PmsBrandAddCmdExe {
    @Autowired
    private PmsBrandMapper pmsBrandMapper;
    public Response execute(PmsBrandAddCmd cmd) {
        pmsBrandMapper.insertSelective(PmsBrandConvertor.toDataObject(cmd.getPmsBrandDTO()));
        return Response.buildSuccess();
    }
}
