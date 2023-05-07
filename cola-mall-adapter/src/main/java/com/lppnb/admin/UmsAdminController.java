package com.lppnb.admin;

import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import com.lppnb.common.ResultCode;
import com.lppnb.admin.api.UmsAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 后台用户管理
 */
@RestController
@Api(tags = "UmsAdminController")
@RequestMapping("/admin")
public class UmsAdminController {
    @Autowired
    private UmsAdminService adminService;

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;


    @ApiOperation(value = "登录以后返回token")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Response login(@RequestParam String username, @RequestParam String password) {
        String token = adminService.login(username, password);
        if (token == null) {
            return Response.buildFailure(ResultCode.LOGIN_FAILED.getCode(), ResultCode.LOGIN_FAILED.getMessage());
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return SingleResponse.of(tokenMap);
    }
}
