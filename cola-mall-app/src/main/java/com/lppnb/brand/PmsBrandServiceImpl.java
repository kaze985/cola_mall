package com.lppnb.brand;

import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import com.lppnb.brand.api.PmsBrandService;
import com.lppnb.brand.dto.*;
import com.lppnb.brand.dto.data.PmsBrandDTO;
import com.lppnb.brand.exector.PmsBrandAddCmdExe;
import com.lppnb.brand.exector.PmsBrandDeleteCmdExe;
import com.lppnb.brand.exector.PmsBrandUpdateCmdExe;
import com.lppnb.brand.exector.query.PmsBrandGetQryExe;
import com.lppnb.brand.exector.query.PmsBrandPageQryExe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PmsBrandServiceImpl implements PmsBrandService {
    @Autowired
    private PmsBrandAddCmdExe pmsBrandAddCmdExe;
    @Autowired
    private PmsBrandDeleteCmdExe pmsBrandDeleteCmdExe;
    @Autowired
    private PmsBrandUpdateCmdExe pmsBrandUpdateCmdExe;
    @Autowired
    private PmsBrandGetQryExe pmsBrandGetQryExe;
    @Autowired
    private PmsBrandPageQryExe pmsBrandPageQryExe;

    @Override
    public Response createBrand(PmsBrandAddCmd cmd) {
        return pmsBrandAddCmdExe.execute(cmd);
    }

    @Override
    public Response updateBrand(PmsBrandUpdateCmd cmd) {
        return pmsBrandUpdateCmdExe.execute(cmd);
    }

    @Override
    public Response deleteBrand(PmsBrandDeleteCmd cmd) {
        return pmsBrandDeleteCmdExe.execute(cmd);
    }

    @Override
    public PageResponse<PmsBrandDTO> listBrand(PmsBrandPageQry qry) {
        return pmsBrandPageQryExe.execute(qry);
    }

    @Override
    public SingleResponse<PmsBrandDTO> getBrand(PmsBrandGetQry qry) {
        return pmsBrandGetQryExe.execute(qry);
    }
}

