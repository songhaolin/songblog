package com.songblog.intercepter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

/**
 * @Description: 管理员权限校验拦截器，如果当前链接不是管理员，则重定向到登录界面
 * @Author: shl
 * @Date: 2022/4/7
 **/
@Slf4j
public class AdminInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object handler) throws IOException {
        HttpSession session = request.getSession();
        if (Objects.isNull(session.getAttribute("admin"))){
            response.sendRedirect("/songblog/login");
            return false;
        }
        return true;
    }
}
