package com.lppnb.convertor;

import com.lppnb.generator.dataobject.UmsResourceDO;
import com.lppnb.resource.dto.UmsResourceDTO;

import java.util.List;
import java.util.stream.Collectors;

public class UmsResourceConvertor {

    public static UmsResourceDO toDataObject(UmsResourceDTO umsResourceDTO){
        UmsResourceDO umsResourceDO = new UmsResourceDO();
        org.springframework.beans.BeanUtils.copyProperties(umsResourceDTO, umsResourceDO);
        return umsResourceDO;
    }

    public static UmsResourceDTO toDataTransferObject(UmsResourceDO umsResourceDO){
        UmsResourceDTO umsResourceDTO = new UmsResourceDTO();
        org.springframework.beans.BeanUtils.copyProperties(umsResourceDO, umsResourceDTO);
        return umsResourceDTO;
    }

    public static List<UmsResourceDO> toDataObjects(List<UmsResourceDTO> umsResourceDTOList){
        return umsResourceDTOList.stream().map(umsResourceDTO -> {
            UmsResourceDO umsResourceDO = new UmsResourceDO();
            org.springframework.beans.BeanUtils.copyProperties(umsResourceDTO, umsResourceDO);
            return umsResourceDO;
        }).collect(Collectors.toList());
    }

    public static List<UmsResourceDTO> toDataTransferObjects(List<UmsResourceDO> umsResourceDOList){
        return umsResourceDOList.stream().map(umsResourceDO -> {
            UmsResourceDTO umsResourceDTO = new UmsResourceDTO();
            org.springframework.beans.BeanUtils.copyProperties(umsResourceDO, umsResourceDTO);
            return umsResourceDTO;
        }).collect(Collectors.toList());
    }
}
