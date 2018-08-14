package com.nancy.handler;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@Slf4j
@WebFilter(filterName="serviceFilter",urlPatterns="/*")
public class ServiceFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        long start = System.currentTimeMillis();
        filterChain.doFilter(servletRequest, servletResponse);
        long end = System.currentTimeMillis();
        long elapsedMilliseconds = end - start;
        //log4j2 MDC
        MDC.put("elapsedMilliseconds", String.valueOf(elapsedMilliseconds));
    }

    @Override
    public void destroy() {

    }
}
