package com.xuebusi.xssm.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 日志拦截器
 * @Author: syj
 * @CreateDate: 2018/1/20 16:48
 */
public class LogInterceptor implements HandlerInterceptor {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final ThreadLocal<Long> startTimeThreadLocal = new ThreadLocal<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        startTimeThreadLocal.set(System.currentTimeMillis());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) throws Exception {
        long startTime = startTimeThreadLocal.get();
        logger.info("请求URI:{}, 请求方式:{}, 耗时:{}", request.getRequestURI(), request.getMethod(), System.currentTimeMillis() - startTime);
        startTimeThreadLocal.remove();
    }
}
