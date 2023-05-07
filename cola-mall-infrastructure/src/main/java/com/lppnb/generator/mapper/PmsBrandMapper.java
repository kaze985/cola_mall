package com.lppnb.generator.mapper;
import java.util.List;

import com.lppnb.generator.dataobject.PmsBrandDO;
import org.apache.ibatis.annotations.Mapper;

/**
* @author one
* @description 针对表【pms_brand(品牌表)】的数据库操作Mapper
* @createDate 2023-05-07 01:21:26
* @Entity com.lppnb.generator.dataobject.PmsBrandDO
*/
@Mapper
public interface PmsBrandMapper {

    int deleteByPrimaryKey(Long id);

    int insert(PmsBrandDO record);

    int insertSelective(PmsBrandDO record);

    PmsBrandDO selectByPrimaryKey(Long id);

    List<PmsBrandDO> selectAll();

    int count();

    int updateByPrimaryKeySelective(PmsBrandDO record);

    int updateByPrimaryKey(PmsBrandDO record);

}
