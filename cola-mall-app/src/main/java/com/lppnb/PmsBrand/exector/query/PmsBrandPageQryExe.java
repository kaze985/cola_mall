package com.lppnb.PmsBrand.exector.query;

import com.alibaba.cola.dto.Response;
import com.lppnb.PmsBrand.dto.PmsBrandPageQry;
import org.springframework.stereotype.Component;

@Component
public class PmsBrandPageQryExe {

    public Response execute(PmsBrandPageQry qry) {
        return Response.buildSuccess();
    }
}
