package com.lppnb.brand.exector;

import com.alibaba.cola.dto.Response;
import com.lppnb.brand.dto.PmsBrandDeleteCmd;
import com.lppnb.generator.mapper.PmsBrandMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author kaze
 */
@Component
public class PmsBrandDeleteCmdExe {
    @Resource
    private PmsBrandMapper pmsBrandMapper;

    public Response execute(PmsBrandDeleteCmd cmd) {
        pmsBrandMapper.deleteByPrimaryKey(cmd.getId());
        return Response.buildSuccess();
    }
}
