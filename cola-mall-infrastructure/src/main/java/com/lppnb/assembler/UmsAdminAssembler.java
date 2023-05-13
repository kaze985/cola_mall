package com.lppnb.assembler;

import com.lppnb.admin.dto.UmsAdminDTO;
import com.lppnb.generator.dataobject.UmsAdminDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author kaze
 */
@Mapper
public interface UmsAdminAssembler {
    UmsAdminAssembler ASSEMBLER = Mappers.getMapper(UmsAdminAssembler.class);

    UmsAdminDO toDataObject(UmsAdminDTO umsAdminDTO);

    List<UmsAdminDO> toDataObjects(List<UmsAdminDTO> umsAdminDTOList);

    UmsAdminDTO toDataTransferObject(UmsAdminDO umsAdminDO);

    List<UmsAdminDTO> toDataTransferObjects(List<UmsAdminDO> umsAdminDOList);
}
