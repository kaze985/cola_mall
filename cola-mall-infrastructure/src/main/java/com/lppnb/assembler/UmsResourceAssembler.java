package com.lppnb.assembler;

import com.lppnb.generator.dataobject.UmsResourceDO;
import com.lppnb.resource.dto.UmsResourceDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author kaze
 */
@Mapper
public interface UmsResourceAssembler {
    UmsResourceAssembler ASSEMBLER = Mappers.getMapper(UmsResourceAssembler.class);

    UmsResourceDO toDataObject(UmsResourceDTO umsResourceDTO);
    List<UmsResourceDO> toDataObjects(List<UmsResourceDTO> umsResourceDTOList);
    UmsResourceDTO toDataTransferObject(UmsResourceDO umsResourceDO);
    List<UmsResourceDTO> toDataTransferObjects(List<UmsResourceDO> umsResourceDOList);
}
