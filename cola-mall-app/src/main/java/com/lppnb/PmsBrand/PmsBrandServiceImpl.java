package com.lppnb.PmsBrand;

import com.lppnb.PmsBrand.api.PmsBrandService;
import com.lppnb.PmsBrand.dto.*;
import com.lppnb.PmsBrand.dto.data.PmsBrandDTO;
import com.lppnb.PmsBrand.exector.PmsBrandAddCmdExe;
import com.lppnb.PmsBrand.exector.PmsBrandDeleteCmdExe;
import com.lppnb.PmsBrand.exector.PmsBrandUpdateCmdExe;
import com.lppnb.PmsBrand.exector.query.PmsBrandGetQryExe;
import com.lppnb.PmsBrand.exector.query.PmsBrandPageQryExe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public int createBrand(PmsBrandAddCmd cmd) {
        return 0;
    }

    @Override
    public int updateBrand(PmsBrandUpdateCmd cmd) {
        return 0;
    }

    @Override
    public int deleteBrand(PmsBrandDeleteCmd cmd) {
        return 0;
    }

    @Override
    public List<PmsBrandDTO> listBrand(PmsBrandPageQry qry) {
        return null;
    }

    @Override
    public PmsBrandDTO getBrand(PmsBrandGetQry qry) {
        return null;
    }
}

