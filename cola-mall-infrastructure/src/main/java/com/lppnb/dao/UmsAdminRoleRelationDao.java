package com.lppnb.dao;

import com.lppnb.generator.dataobject.UmsResourceDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 后台用户与角色关系管理自定义Dao
 * @author one
 */
@Mapper
public interface UmsAdminRoleRelationDao {

    /**
     * 获取用户所有可访问资源
     */
    List<UmsResourceDO> getResourceList(@Param("adminId") Long adminId);

}
