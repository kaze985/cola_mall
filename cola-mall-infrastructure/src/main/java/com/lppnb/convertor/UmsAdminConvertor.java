package com.lppnb.convertor;


import com.lppnb.UmsAdmin.dto.UmsAdminDTO;
import com.lppnb.mbg.dataobject.UmsAdmin;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

public class UmsAdminConvertor {
    public static UmsAdmin toDataObject(UmsAdminDTO dto){
        UmsAdmin pmsBrand = new UmsAdmin();
        BeanUtils.copyProperties(dto, pmsBrand);
        return pmsBrand;
    }

    public static List<UmsAdmin> toDataObjectList(List<UmsAdminDTO> dtoList){
        return dtoList.stream().map(pmsBrandDTO -> {
            UmsAdmin pmsBrand = new UmsAdmin();
            BeanUtils.copyProperties(pmsBrandDTO, pmsBrand);
            return pmsBrand;
        }).collect(Collectors.toList());
    }
}
