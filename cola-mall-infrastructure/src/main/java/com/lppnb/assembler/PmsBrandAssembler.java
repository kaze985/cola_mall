package com.lppnb.assembler;

import com.lppnb.brand.dto.data.PmsBrandDTO;
import com.lppnb.generator.dataobject.PmsBrandDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author kaze
 */
@Mapper
public interface PmsBrandAssembler {
    PmsBrandAssembler ASSEMBLER =  Mappers.getMapper(PmsBrandAssembler.class);

    PmsBrandDO toDataObject(PmsBrandDTO pmsBrandDTO);

    List<PmsBrandDO> toDataObjects(List<PmsBrandDTO> pmsBrandDTOList);

    PmsBrandDTO toDataTransferObject(PmsBrandDO pmsBrandDO);

    List<PmsBrandDTO> toDataTransferObjects(List<PmsBrandDO> pmsBrandDOList);
}
