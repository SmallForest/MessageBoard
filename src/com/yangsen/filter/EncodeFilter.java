package com.yangsen.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * 字符过滤器
 */
public class EncodeFilter implements Filter {
    private String encoding;
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //把请求的修改成为utf-8
        servletRequest.setCharacterEncoding(this.encoding);
        servletResponse.setCharacterEncoding(this.encoding);
        //告诉其他过滤器本过滤器已经完成过滤工作
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.encoding = filterConfig.getInitParameter("encode");
        System.out.println("字符过滤器启动》》》");
    }

    @Override
    public void destroy() {
        System.out.println("字符过滤器销毁》》》");
    }
}
