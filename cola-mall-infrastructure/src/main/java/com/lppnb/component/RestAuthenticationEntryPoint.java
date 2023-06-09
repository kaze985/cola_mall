package com.lppnb.component;

import cn.hutool.json.JSONUtil;
import com.alibaba.cola.dto.Response;
import com.lppnb.common.ResultCode;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 当未登录或者token失效访问接口时，自定义的返回结果
 * @author kaze
 */
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().println(JSONUtil.parse(Response.buildFailure(ResultCode.UNAUTHORIZED.getCode(), ResultCode.UNAUTHORIZED.getMessage())));
        response.getWriter().flush();
    }
}
