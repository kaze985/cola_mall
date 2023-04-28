package com.lppnb.PmsBrand.exector;

import com.alibaba.cola.dto.Response;
import com.lppnb.PmsBrand.dto.PmsBrandDeleteCmd;
import org.springframework.stereotype.Component;

@Component
public class PmsBrandDeleteCmdExe {



    public Response execute(PmsBrandDeleteCmd cmd) {
        return Response.buildSuccess();
    }
}
