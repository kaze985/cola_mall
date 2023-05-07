package com.lppnb.resource.api;

import com.lppnb.resource.dto.UmsResourceDTO;

import java.util.List;

/**
 * 后台资源管理Service
 */
public interface UmsResourceService {

    /**
     * 查询全部资源
     */
    List<UmsResourceDTO> listAll();
}
