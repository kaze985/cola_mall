package com.lppnb.PmsBrand.api;

import com.lppnb.PmsBrand.dto.*;
import com.lppnb.PmsBrand.dto.data.PmsBrandDTO;

import java.util.List;

public interface PmsBrandService {

    int createBrand(PmsBrandAddCmd cmd);

    int updateBrand(PmsBrandUpdateCmd cmd);

    int deleteBrand(PmsBrandDeleteCmd cmd);

    List<PmsBrandDTO> listBrand(PmsBrandPageQry qry);

    PmsBrandDTO getBrand(PmsBrandGetQry qry);
}
