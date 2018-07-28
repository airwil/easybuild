package com.easybuild.cores.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 定义拦截器
 */
public class MyInterceptor implements HandlerInterceptor {
	private static final Logger log = LoggerFactory.getLogger(MyInterceptor.class);
	private static final String COOKIE_NAME="userid";
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String requestURI = request.getRequestURI();
		log.info(requestURI);
		Cookie[] cookies = request.getCookies();
		if(cookies!=null&&cookies.length>0){
			for (Cookie cookie : cookies) {
				if(cookie.getName().equals(COOKIE_NAME)){
					log.info("用户id："+cookie.getValue()+"，操作url："+requestURI);
					//放行
					return true;
				}
			}
		}
		//没有对应cookie则跳转到登录页面
		response.sendRedirect("/portal/login.html");
		return false;
	}
}
