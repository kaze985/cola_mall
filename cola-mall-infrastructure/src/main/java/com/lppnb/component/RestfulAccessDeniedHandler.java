package com.lppnb.component;

import cn.hutool.json.JSONUtil;
import com.alibaba.cola.dto.Response;
import com.lppnb.common.ResultCode;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 当访问接口没有权限时，自定义的返回结果
 * @author kaze
 */
public class RestfulAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException e) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().println(JSONUtil.parse(Response.buildFailure(ResultCode.FORBIDDEN.getCode(), ResultCode.FORBIDDEN.getMessage())));
        response.getWriter().flush();
    }
}
