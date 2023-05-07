package com.lppnb.resource;

import com.lppnb.convertor.UmsResourceConvertor;
import com.lppnb.generator.dataobject.UmsResourceDO;
import com.lppnb.generator.mapper.UmsResourceMapper;
import com.lppnb.resource.api.UmsResourceService;
import com.lppnb.resource.dto.UmsResourceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 后台资源管理Service实现类
 * @author kaze
 */
@Service
public class UmsResourceServiceImpl implements UmsResourceService {
    @Autowired
    private UmsResourceMapper resourceMapper;

    @Override
    public List<UmsResourceDTO> listAll() {
        List<UmsResourceDO> umsResources = resourceMapper.selectAll();
        return UmsResourceConvertor.toDataTransferObjects(umsResources);
    }
}
