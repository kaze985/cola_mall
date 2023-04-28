package com.lppnb.PmsBrand.exector.query;

import com.alibaba.cola.dto.Response;
import com.lppnb.PmsBrand.dto.PmsBrandGetQry;
import org.springframework.stereotype.Component;

@Component
public class PmsBrandGetQryExe {

    public Response execute(PmsBrandGetQry qry) {
        return Response.buildSuccess();
    }
}
