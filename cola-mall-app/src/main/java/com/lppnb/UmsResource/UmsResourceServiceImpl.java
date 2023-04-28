package com.lppnb.UmsResource;

import com.lppnb.UmsResource.api.UmsResourceService;
import com.lppnb.UmsResource.dto.UmsResourceDTO;
import com.lppnb.mbg.dataobject.UmsResource;
import com.lppnb.mbg.dataobject.UmsResourceExample;
import com.lppnb.mbg.mapper.UmsResourceMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 后台资源管理Service实现类
 */
@Service
public class UmsResourceServiceImpl implements UmsResourceService {
    @Autowired
    private UmsResourceMapper resourceMapper;

    @Override
    public List<UmsResourceDTO> listAll() {
        List<UmsResource> umsResources = resourceMapper.selectByExample(new UmsResourceExample());
        return umsResources.stream().map(umsResource -> {
            UmsResourceDTO umsResourceDTO = new UmsResourceDTO();
            BeanUtils.copyProperties(umsResource, umsResourceDTO);
            return umsResourceDTO;
        }).collect(Collectors.toList());
    }
}
