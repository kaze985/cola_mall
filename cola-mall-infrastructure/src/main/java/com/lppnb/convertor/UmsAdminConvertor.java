package com.lppnb.convertor;


import com.lppnb.admin.dto.UmsAdminDTO;
import com.lppnb.generator.dataobject.UmsAdminDO;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author one
 */
public class UmsAdminConvertor {
    public static UmsAdminDO toDataObject(UmsAdminDTO umsAdminDTO){
        UmsAdminDO pmsBrandDO = new UmsAdminDO();
        BeanUtils.copyProperties(umsAdminDTO, pmsBrandDO);
        return pmsBrandDO;
    }


    public static UmsAdminDTO toDataTransferObject(UmsAdminDO umsAdminDO){
        UmsAdminDTO umsAdminDTO = new UmsAdminDTO();
        BeanUtils.copyProperties(umsAdminDO, umsAdminDTO);
        return umsAdminDTO;
    }

    public static List<UmsAdminDO> toDataObjects(List<UmsAdminDTO> umsAdminDTOList){
        return umsAdminDTOList.stream().map(umsAdminDTO -> {
            UmsAdminDO umsAdminDO = new UmsAdminDO();
            BeanUtils.copyProperties(umsAdminDTO, umsAdminDO);
            return umsAdminDO;
        }).collect(Collectors.toList());
    }

    public static List<UmsAdminDTO> toDataTransferObjects(List<UmsAdminDO> umsAdminDOList){
        return umsAdminDOList.stream().map(umsAdminDO -> {
            UmsAdminDTO umsAdminDTO = new UmsAdminDTO();
            BeanUtils.copyProperties(umsAdminDO, umsAdminDTO);
            return umsAdminDTO;
        }).collect(Collectors.toList());
    }
}
