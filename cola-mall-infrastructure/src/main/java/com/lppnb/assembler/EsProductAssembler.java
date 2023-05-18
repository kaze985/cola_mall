package com.lppnb.assembler;

import com.lppnb.search.document.EsProduct;
import com.lppnb.product.dto.EsProductDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author kaze
 * @date 2023/5/17 23:20
 */
@Mapper
public interface EsProductAssembler {
    EsProductAssembler ASSEMBLER =  Mappers.getMapper(EsProductAssembler.class);

    EsProductDTO toDataTransferObject(EsProduct esProduct);
    List<EsProductDTO> toDataTransferObjects(List<EsProduct> esProductList);

}
