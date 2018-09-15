package com.dev.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import com.dev.web.util.GlobalStatus;

public class WebInterceptor implements HandlerInterceptor{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String referer = request.getHeader("referer");
		String contextPath = request.getContextPath();
		if(GlobalStatus.isEmpty(referer)) {
			response.sendRedirect(contextPath + "/index");
			return false;
		}
		return true;
	}
}
