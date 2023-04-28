package com.lppnb.PmsBrand.exector;

import com.alibaba.cola.dto.Response;
import com.lppnb.PmsBrand.dto.PmsBrandAddCmd;
import org.springframework.stereotype.Component;

@Component
public class PmsBrandAddCmdExe {



    public Response execute(PmsBrandAddCmd cmd) {
        return Response.buildSuccess();
    }
}
