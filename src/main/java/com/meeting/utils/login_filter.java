package com.meeting.utils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 过滤器
 * **/
public class login_filter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String path = ((HttpServletRequest) servletRequest).getRequestURI();
        HttpSession session = ((HttpServletRequest) servletRequest).getSession();
        if(path.equals("/")||path.contains("/checkUser")||path.contains("/static")
                ||path.contains("qrcode_success")||path.contains("updateMeetingSignin")
                ||path.contains(".do")) {
            filterChain.doFilter(request, response);
            return;
        }
        if (session.getAttribute("username")!=null){
            String username= (String) session.getAttribute("username");
            filterChain.doFilter(request, response);// 如果用户已经登录，就放行
            return;
        }else {
            // 没有登录，重定向到登录页面
            response.sendRedirect( request.getContextPath()+"/");
            return;
        }

    }

    @Override
    public void destroy() {

    }
}
