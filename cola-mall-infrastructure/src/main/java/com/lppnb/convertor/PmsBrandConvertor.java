package com.lppnb.convertor;

import com.lppnb.brand.dto.data.PmsBrandDTO;
import com.lppnb.generator.dataobject.PmsBrandDO;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author one
 */
public class PmsBrandConvertor {
    public static PmsBrandDO toDataObject(PmsBrandDTO pmsBrandDTO){
        PmsBrandDO pmsBrandDO = new PmsBrandDO();
        BeanUtils.copyProperties(pmsBrandDTO, pmsBrandDO);
        return pmsBrandDO;
    }

    public static PmsBrandDTO toDataTransferObject(PmsBrandDO pmsBrandDO){
        PmsBrandDTO pmsBrandDTO = new PmsBrandDTO();
        BeanUtils.copyProperties(pmsBrandDO, pmsBrandDTO);
        return pmsBrandDTO;
    }

    public static List<PmsBrandDO> toDataObjects(List<PmsBrandDTO> pmsBrandDTOList){
        return pmsBrandDTOList.stream().map(pmsBrandDTO -> {
            PmsBrandDO pmsBrandDO = new PmsBrandDO();
            BeanUtils.copyProperties(pmsBrandDTO, pmsBrandDO);
            return pmsBrandDO;
        }).collect(Collectors.toList());
    }

    public static List<PmsBrandDTO> toDataTransferObjects(List<PmsBrandDO> pmsBrandDOList){
        return pmsBrandDOList.stream().map(pmsBrandDO -> {
            PmsBrandDTO pmsBrandDTO = new PmsBrandDTO();
            BeanUtils.copyProperties(pmsBrandDO, pmsBrandDTO);
            return pmsBrandDTO;
        }).collect(Collectors.toList());
    }
}
