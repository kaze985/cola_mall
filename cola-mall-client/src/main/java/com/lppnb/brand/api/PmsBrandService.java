package com.lppnb.brand.api;

import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import com.lppnb.brand.dto.*;
import com.lppnb.brand.dto.data.PmsBrandDTO;

import java.util.List;

public interface PmsBrandService {

    Response createBrand(PmsBrandAddCmd cmd);

    Response updateBrand(PmsBrandUpdateCmd cmd);

    Response deleteBrand(PmsBrandDeleteCmd cmd);

    PageResponse<PmsBrandDTO> listBrand(PmsBrandPageQry qry);

    SingleResponse<PmsBrandDTO> getBrand(PmsBrandGetQry qry);
}
