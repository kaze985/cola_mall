package com.lppnb.admin.api;

import com.lppnb.admin.dto.UmsAdminDTO;
import com.lppnb.resource.dto.UmsResourceDTO;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

/**
 * 后台用于管理Service
 */
public interface UmsAdminService {
    /**
     * 根据用户名获取用户信息
     */
    UmsAdminDTO getAdminByUsername(String username);

    /**
     * 用户名密码登录
     */
    String login(String username, String password);

    /**
     * 获取用户信息
     */
    UserDetails loadUserByUsername(String username);

    /**
     * 获取指定用户的可访问资源
     */
    List<UmsResourceDTO> getResourceList(Long adminId);
}
