package com.luzj.kpimanage.config;


import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author luzj
 * @description: 解决跨域问题
 * @date 2018/3/13
 */
@Component
public class CrossFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse rep = (HttpServletResponse) servletResponse;
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        String origin = req.getHeader("Origin");
       /* if ("http://localhost:63342".equals(origin) ||
                "http://133.195.235.53".equals(origin) ||
                "http://133.193.96.71:8000".equals(origin) ||
                "http://133.193.96.39:8000".equals(origin) ||
                "http://133.195.235.54:8084".equals(origin) ||
                "http://133.193.96.15:8000".equals(origin) ||
                "http://localhost:8090".equals(origin)) {
            rep.setHeader("Access-Control-Allow-Origin", origin);
        }*/
        rep.setHeader("Access-Control-Allow-Origin", origin);
        rep.setHeader("Access-Control-Allow-Methods", "POST, GET,PUT,DELETE,OPTIONS");
        rep.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
        rep.setHeader("Access-Control-Allow-Credentials", "true");
        //((HttpServletRequest) servletRequest).getSession().setAttribute(SessConstants.PRODUCT_TYPE,"grid");
        //((HttpServletRequest) servletRequest).getSession().setAttribute(SessConstants.OPERATION_CBSS_ID,"A0020980");

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
