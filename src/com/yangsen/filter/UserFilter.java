package com.yangsen.filter;

import com.yangsen.bean.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class UserFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        User user = (User) httpServletRequest.getSession().getAttribute("user");
        servletRequest.setAttribute("user", user);
        filterChain.doFilter(servletRequest, servletResponse);

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("UserFilter过滤器启动》》");
    }

    @Override
    public void destroy() {
        System.out.println("UserFilter过滤器释放》》");
    }
}
