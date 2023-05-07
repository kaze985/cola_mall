package com.lppnb.generator.mapper;
import java.util.List;

import com.lppnb.generator.dataobject.UmsResourceDO;
import org.apache.ibatis.annotations.Mapper;

/**
* @author one
* @description 针对表【ums_resource(后台资源表)】的数据库操作Mapper
* @createDate 2023-05-07 01:21:26
* @Entity com.lppnb.generator.dataobject.UmsResourceDO
*/
@Mapper
public interface UmsResourceMapper {

    int deleteByPrimaryKey(Long id);

    int insert(UmsResourceDO record);

    int insertSelective(UmsResourceDO record);

    UmsResourceDO selectByPrimaryKey(Long id);

    List<UmsResourceDO> selectAll();

    int updateByPrimaryKeySelective(UmsResourceDO record);

    int updateByPrimaryKey(UmsResourceDO record);

}
