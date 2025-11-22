package com.hyltest.filter;

import com.hyltest.utils.CurrentHolder;
import com.hyltest.utils.JWTUtils;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import java.io.IOException;
import java.util.Map;


@Slf4j
@WebFilter(urlPatterns = "/*")
public class TokenFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("过滤器初始化");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("过滤器工作");
        HttpServletRequest  request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        //获取请求的资源路径
        String requestURI = request.getRequestURI();

        //1、判断请求的资源路径是否是登录页面/login，是则直接放行
        if (requestURI.contains("login")){
            filterChain.doFilter(request, response);
            return;
        }

        //2、如果不是，则校验请求头中是否有令牌（token：“jwt令牌”），有则放行，无则返回401状态码
        //获取请求头中的token
        String token = request.getHeader("token");

        if (token == null || token.isEmpty()){
            //401状态码代表客户端错误，指的是由于缺乏目标资源要求的身份验证凭证，发送的请求未得到满足
            response.setStatus(401);
            return;
        }

        //3、有令牌，则解析令牌，如果令牌有效，则放行，否则返回401状态码
        try {
            //解析令牌：出现问题时直接进入catch块，返回401状态码结束方法
            Map<String, Object> map = JWTUtils.parseToken(token);
            //获取当前操作用户的id
            Integer id = Integer.parseInt(map.get("id").toString());
            //将id保存到当前线程的threadLocal中
            CurrentHolder.setCurrentId(id);
            log.info("当前操作用户的id为：{}", id);
        } catch (Exception e) {
            //令牌无效则直接抛出异常
            response.setStatus(401);
            return;
        }
        //放行去访问资源
        filterChain.doFilter(request, response);
        //放行到相应类处理完后返回回客户端前，删除当前线程的threadLocal，防止下次线程使用
        CurrentHolder.remove();
    }

    @Override
    public void destroy() {
        log.info("过滤器销毁");
    }
}
