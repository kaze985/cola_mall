package com.lppnb.generator.mapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.lppnb.generator.dataobject.UmsAdminDO;
import org.apache.ibatis.annotations.Mapper;

/**
* @author one
* @description 针对表【ums_admin(后台用户表)】的数据库操作Mapper
* @createDate 2023-05-07 01:21:26
* @Entity com.lppnb.generator.dataobject.UmsAdminDO
*/
@Mapper
public interface UmsAdminMapper {

    int deleteByPrimaryKey(Long id);

    int insert(UmsAdminDO record);

    int insertSelective(UmsAdminDO record);

    UmsAdminDO selectByPrimaryKey(Long id);

    List<UmsAdminDO> selectByUsername(@Param("username") String username);

    int updateByPrimaryKeySelective(UmsAdminDO record);

    int updateByPrimaryKey(UmsAdminDO record);

}
