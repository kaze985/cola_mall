package com.lppnb.admin;

import com.lppnb.admin.api.UmsAdminService;
import com.lppnb.admin.dto.UmsAdminDTO;
import com.lppnb.convertor.UmsAdminConvertor;
import com.lppnb.convertor.UmsResourceConvertor;
import com.lppnb.dao.UmsAdminRoleRelationDao;
import com.lppnb.domain.admin.AdminUserDetails;
import com.lppnb.generator.dataobject.UmsAdminDO;
import com.lppnb.generator.dataobject.UmsResourceDO;
import com.lppnb.generator.mapper.UmsAdminMapper;
import com.lppnb.resource.dto.UmsResourceDTO;
import com.lppnb.utils.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
public class UmsAdminServiceImpl implements UmsAdminService {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UmsAdminMapper adminMapper;
    @Autowired
    private UmsAdminRoleRelationDao adminRoleRelationDao;

    @Override
    public UmsAdminDTO getAdminByUsername(String username) {
        List<UmsAdminDO> admins = adminMapper.selectByUsername(username);
        if (admins != null && admins.size() > 0) {
            return UmsAdminConvertor.toDataTransferObject(admins.get(0));
        }
        return null;
    }

    @Override
    public String login(String username, String password) {
        String token = null;
        try {
            UserDetails userDetails = loadUserByUsername(username);
            if (userDetails == null) {
                return null;
            }
            if (!passwordEncoder.matches(password, userDetails.getPassword())) {
                throw new BadCredentialsException("密码不正确");
            }
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = jwtTokenUtil.generateToken(userDetails);
        } catch (AuthenticationException e) {
            log.warn("登录异常:{}", e.getMessage());
        }
        return token;
    }
    @Override
    public UserDetails loadUserByUsername(String username){
        //获取用户信息
        UmsAdminDTO admin = getAdminByUsername(username);
        if (admin != null) {
            List<UmsResourceDTO> resourceList = getResourceList(admin.getId());
            return new AdminUserDetails(admin, resourceList);
        }
        throw new UsernameNotFoundException("用户名或密码错误");
    }

    @Override
    public List<UmsResourceDTO> getResourceList(Long adminId) {
        List<UmsResourceDO> resourceList = adminRoleRelationDao.getResourceList(adminId);
        return UmsResourceConvertor.toDataTransferObjects(resourceList);
    }
}
