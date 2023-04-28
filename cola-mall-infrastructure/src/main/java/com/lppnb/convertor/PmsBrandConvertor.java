package com.lppnb.convertor;

import com.lppnb.PmsBrand.dto.data.PmsBrandDTO;
import com.lppnb.mbg.dataobject.PmsBrand;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

public class PmsBrandConvertor {
    public static PmsBrand toDataObject(PmsBrandDTO dto){
        PmsBrand pmsBrand = new PmsBrand();
        BeanUtils.copyProperties(dto, pmsBrand);
        return pmsBrand;
    }

    public static List<PmsBrand> toDataObjectList(List<PmsBrandDTO> dtoList){
        return dtoList.stream().map(pmsBrandDTO -> {
            PmsBrand pmsBrand = new PmsBrand();
            BeanUtils.copyProperties(pmsBrandDTO, pmsBrand);
            return pmsBrand;
        }).collect(Collectors.toList());
    }
}
