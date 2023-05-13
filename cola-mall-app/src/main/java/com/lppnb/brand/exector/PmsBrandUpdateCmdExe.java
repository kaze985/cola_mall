package com.lppnb.brand.exector;

import com.alibaba.cola.dto.Response;
import com.lppnb.assembler.PmsBrandAssembler;
import com.lppnb.brand.dto.PmsBrandUpdateCmd;
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
        pmsBrandMapper.updateByPrimaryKeySelective(PmsBrandAssembler.ASSEMBLER.toDataObject(cmd.getPmsBrandDTO()));
        return Response.buildSuccess();
    }
}
