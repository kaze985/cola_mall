package com.lppnb.PmsBrand.exector;

import com.alibaba.cola.dto.Response;
import com.lppnb.PmsBrand.dto.PmsBrandUpdateCmd;
import org.springframework.stereotype.Component;

@Component
public class PmsBrandUpdateCmdExe {



    public Response execute(PmsBrandUpdateCmd cmd) {
        return Response.buildSuccess();
    }
}
