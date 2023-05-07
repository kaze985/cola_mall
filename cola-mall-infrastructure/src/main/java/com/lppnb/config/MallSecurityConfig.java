package com.lppnb.config;

import com.lppnb.admin.api.UmsAdminService;
import com.lppnb.resource.api.UmsResourceService;
import com.lppnb.resource.dto.UmsResourceDTO;
import com.lppnb.component.DynamicSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * mall-security模块相关配置
 * 自定义配置，用于配置如何获取用户信息及动态权限
 */
@Configuration
public class MallSecurityConfig {

    @Autowired
    private UmsAdminService umsAdminService;
    @Autowired
    private UmsResourceService umsResourceService;

    @Bean
    public UserDetailsService userDetailsService() {
        //获取登录用户信息
        return username -> umsAdminService.loadUserByUsername(username);
    }

    @Bean
    public DynamicSecurityService dynamicSecurityService() {
        return new DynamicSecurityService() {
            @Override
            public Map<String, ConfigAttribute> loadDataSource() {
                List<UmsResourceDTO> resourceList = umsResourceService.listAll();
                Map<String, ConfigAttribute> map = new ConcurrentHashMap<>(resourceList.size());
                for (UmsResourceDTO resource : resourceList) {
                    map.put(resource.getUrl(), new org.springframework.security.access.SecurityConfig(resource.getId() + ":" + resource.getName()));
                }
                return map;
            }
        };
    }

}
