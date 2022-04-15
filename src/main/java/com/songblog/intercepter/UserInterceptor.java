package com.songblog.intercepter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

/**
 * @Description: 用户属性拦截器，如果用户属性为空，则重定向到登录页面
 * @Author: shl
 * @Date: 2022/4/7
 **/
@Slf4j
public class UserInterceptor implements HandlerInterceptor   {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object handler) throws IOException {
        HttpSession session = request.getSession();
        if (Objects.isNull(session.getAttribute("user"))){
            response.sendRedirect("/login");
            return false;
        }
        return true;
    }
}
