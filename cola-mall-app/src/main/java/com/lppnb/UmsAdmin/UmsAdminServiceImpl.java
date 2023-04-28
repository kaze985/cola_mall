package com.lppnb.UmsAdmin;

import com.lppnb.UmsAdmin.api.UmsAdminService;
import com.lppnb.domain.UmsAdmin.AdminUserDetails;
import com.lppnb.UmsAdmin.dto.UmsAdminDTO;
import com.lppnb.UmsResource.dto.UmsResourceDTO;
import com.lppnb.dao.UmsAdminRoleRelationDao;
import com.lppnb.mbg.dataobject.UmsAdmin;
import com.lppnb.mbg.dataobject.UmsAdminExample;
import com.lppnb.mbg.dataobject.UmsResource;
import com.lppnb.mbg.mapper.UmsAdminMapper;
import com.lppnb.utils.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
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
import java.util.stream.Collectors;


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
        UmsAdminExample example = new UmsAdminExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<UmsAdmin> umsAdmins = adminMapper.selectByExample(example);
        List<UmsAdminDTO> adminList = umsAdmins.stream().map(umsAdmin -> {
            UmsAdminDTO umsAdminDTO = new UmsAdminDTO();
            BeanUtils.copyProperties(umsAdmin, umsAdminDTO);
            return umsAdminDTO;
        }).collect(Collectors.toList());
        return adminList.get(0);
    }

    @Override
    public String login(String username, String password) {
        String token = null;
        try {
            UserDetails userDetails = loadUserByUsername(username);
            if (userDetails == null) {
                return token;
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
            return new AdminUserDetails(admin,resourceList);
        }
        throw new UsernameNotFoundException("用户名或密码错误");
    }

    @Override
    public List<UmsResourceDTO> getResourceList(Long adminId) {
        List<UmsResource> resourceList = adminRoleRelationDao.getResourceList(adminId);
        return resourceList.stream().map(umsResource -> {
            UmsResourceDTO umsResourceDTO = new UmsResourceDTO();
            BeanUtils.copyProperties(umsResource, umsResourceDTO);
            return umsResourceDTO;
        }).collect(Collectors.toList());
    }
}
